/*******************************************************************************
 * Copyright (c) 2005, 2013 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Intel Corporation - Initial API and implementation
 *     Andrew Gvozdev    - Ability to use different Cygwin versions in different cfg
 *******************************************************************************/
package org.eclipse.cdt.managedbuilder.gnu.cygwin;

import com.verykit.common.Cygwin;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.envvar.IBuildEnvironmentVariable;
import org.eclipse.cdt.managedbuilder.envvar.IConfigurationEnvironmentVariableSupplier;
import org.eclipse.cdt.managedbuilder.envvar.IEnvironmentVariableProvider;
import org.eclipse.cdt.managedbuilder.internal.envvar.BuildEnvVar;
import org.eclipse.core.runtime.Platform;

/**
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GnuCygwinConfigurationEnvironmentSupplier implements IConfigurationEnvironmentVariableSupplier {
	private static final String ENV_PATH = "PATH"; //$NON-NLS-1$
	private static final String ENV_LANG = "LANG"; //$NON-NLS-1$
	private static final String ENV_LC_ALL = "LC_ALL"; //$NON-NLS-1$
	private static final String ENV_LC_MESSAGES = "LC_MESSAGES"; //$NON-NLS-1$

	private static String langValue = null;

	private static String getLangValue() {
		if (langValue == null) {
			langValue = System.getenv(ENV_LANG);
			if (langValue == null || langValue.length() == 0) {
				langValue = System.getenv(ENV_LC_ALL);
			}
			if (langValue == null || langValue.length() == 0) {
				langValue = System.getenv(ENV_LC_MESSAGES);
			}
			if (langValue != null && langValue.length() > 0) {
				// langValue is [language[_territory][.codeset][@modifier]], i.e. "en_US.UTF-8@dict"
				// ignore modifier which is not used by LANG
				langValue = langValue.replaceFirst("([^.@]*)(\\..*)?(@.*)?", "$1.UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				langValue = "en_US.UTF-8"; //$NON-NLS-1$
			}
		}
		return langValue;
	}

	@Override
	public IBuildEnvironmentVariable getVariable(String variableName, IConfiguration configuration, IEnvironmentVariableProvider provider) {
		if (variableName == null) {
			return null;
		}
		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			return null;
		}
		if (variableName.equalsIgnoreCase(ENV_PATH)) {
			String path = Cygwin.cygwinToWindowsPath("/bin"); //$NON-NLS-1$
			return new BuildEnvVar(ENV_PATH, path, IBuildEnvironmentVariable.ENVVAR_PREPEND);
		} else if (variableName.equalsIgnoreCase(ENV_LANG)) {
			return new BuildEnvVar(ENV_LANG, getLangValue());
		} else if (variableName.equalsIgnoreCase(ENV_LC_ALL)) {
			return new BuildEnvVar(ENV_LC_ALL, getLangValue());
		}
		return null;
	}

	@Override
	public IBuildEnvironmentVariable[] getVariables(IConfiguration configuration, IEnvironmentVariableProvider provider) {
		IBuildEnvironmentVariable varPath = getVariable(ENV_PATH, configuration, provider);
		IBuildEnvironmentVariable varLang = getVariable(ENV_LANG, configuration, provider);
		IBuildEnvironmentVariable varLcAll = getVariable(ENV_LC_ALL, configuration, provider);

		return new IBuildEnvironmentVariable[] {varPath, varLang, varLcAll};
	}
}
