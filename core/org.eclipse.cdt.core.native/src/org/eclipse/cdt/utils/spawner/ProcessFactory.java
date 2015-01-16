/*******************************************************************************
 * Copyright (c) 2000, 2014 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *     Martin Oberhuber (Wind River) - [303083] Split out the Spawner
 *******************************************************************************/
package org.eclipse.cdt.utils.spawner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.cdt.internal.core.natives.Messages;
import org.eclipse.cdt.utils.pty.PTY;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */

/**
 * 
 * use ProcessBuilder to launch process
 * 
 * @see "http://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html"
 *
 */

public class ProcessFactory {
	static private ProcessFactory instance;

	private static Map<String, String> envpToEnvMap(String[] envp) {
		TreeMap<String, String> environment = new TreeMap<String, String>();
		if (envp != null) {
			for (String envstring : envp) {
				int eqlsign = envstring.indexOf('=');
				// Silently ignore envstrings lacking the required `='.
				if (eqlsign != -1)
					environment.put(envstring.substring(0, eqlsign),
							envstring.substring(eqlsign + 1));
			}
		}
		return environment;
	}

	private ProcessFactory() {
	}

	public static ProcessFactory getFactory() {
		if (instance == null)
			instance = new ProcessFactory();
		return instance;
	}

	public Process exec(String cmd) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, null, null);
		return p;
	}

	public Process exec(String[] cmdarray) throws IOException {
		Process p = exec(cmdarray, null, null);
		return p;
	}

	public Process exec(String[] cmdarray, String[] envp) throws IOException {
		Process p = exec(cmdarray, envp, null);
		return p;
	}

	public Process exec(String cmd, String[] envp) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, envp, null);
		return p;
	}

	public Process exec(String cmd, String[] envp, File dir) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, envp, dir);
		return p;
	}

	public Process exec(String cmdarray[], String[] envp, File dir) throws IOException {
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			cmdarray[0] = Path.fromOSString(cmdarray[0]).toPortableString();
		}

		List<String> cmdList = Arrays.asList(cmdarray);
		ProcessBuilder pb = new ProcessBuilder(cmdList);
		Map<String, String> env = pb.environment();
		if (envp != null) {
			Map<String, String> environment = envpToEnvMap(envp);
			env.clear();
			env.putAll(environment);
		}

		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			/*
			 * The CYGWIN environment variable is used to configure many global settings for the Cygwin
			 * runtime system. It contains the options listed below, separated by blank characters.
			 * 
			 * @see https://cygwin.com/cygwin-ug-net/using-cygwinenv.html
			 */
			String nodosfilewarnings = "nodosfilewarning"; //$NON-NLS-1$
			String cygwinEnvVarValue = env.get("CYGWIN"); //$NON-NLS-1$
			if (cygwinEnvVarValue == null) {
				env.put("CYGWIN", nodosfilewarnings); //$NON-NLS-1$
			} else if (cygwinEnvVarValue.indexOf(nodosfilewarnings) == -1) {
				cygwinEnvVarValue += " " + nodosfilewarnings; //$NON-NLS-1$
				env.put("CYGWIN", cygwinEnvVarValue); //$NON-NLS-1$
			}
		}

		{
			boolean setLangEnvVartoDefault = false;
			String langEnvValue = env.get("LANG"); //$NON-NLS-1$
			if (langEnvValue == null) {
				setLangEnvVartoDefault = true;
			} else {
				String[] array = langEnvValue.split("\\."); //$NON-NLS-1$
				if (array == null || array.length != 2) {
					setLangEnvVartoDefault = true;
				} else {
					String country = array[0];
					String charset = array[1];
					if (country.equals("C")) { //$NON-NLS-1$
						country = "en_US"; //$NON-NLS-1$
					}
					if (!charset.equals("UTF-8")) { //$NON-NLS-1$
						charset = "UTF-8"; //$NON-NLS-1$
					}
					langEnvValue = country + '.' + charset;
					env.put("LANG", langEnvValue); //$NON-NLS-1$
					env.put("LC_ALL", langEnvValue); //$NON-NLS-1$
				}
			}
			if (setLangEnvVartoDefault) {
				env.put("LANG", "en_US.UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
				env.put("LC_ALL", "en_US.UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		if (dir != null) {
			pb.directory(dir);
		}

		Process p = pb.start();
		return p;
	}

	@Deprecated
	public Process exec(String cmdarray[], String[] envp, File dir, PTY pty) throws IOException {
		throw new UnsupportedOperationException(Messages.Util_exception_cannotCreatePty);
	}
}
