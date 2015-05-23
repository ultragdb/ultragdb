/**********************************************************************
 * Copyright (c) 2007, 2013 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Doug Schaefer (QNX Software Systems) - Initial API and implementation
 *     Andrew Gvozdev                       - Ability to use different MinGW versions in different cfg
 **********************************************************************/

package org.eclipse.cdt.managedbuilder.gnu.mingw;

import com.verykit.common.WindowsGCC;
import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

/**
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MingwIsToolChainSupported implements IManagedIsToolChainSupported {
	private static final String ENV_PATH = "PATH"; //$NON-NLS-1$

	@Override
	public boolean isSupported(IToolChain toolChain, Version version, String instance) {
		return WindowsGCC.isMinGW32() || WindowsGCC.isMinGW64();
	}

}
