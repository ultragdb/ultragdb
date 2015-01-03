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
			/*
			 * TODO : use the statement in comment below to replace the next statement. this will require
			 * plugin org.eclipse.cdt.core, which also require plugin org.eclipse.cdt.core.native, this is
			 * cause a cycle in dependency graph, which is not allowed. The solution is to move
			 * ProcessFactory.java, PTY2.java, PTY2Utils.java to plugin org.eclipse.cdt.core, and make plugin
			 * org.eclipse.cdt.core.native empty.
			 */
			// String cygwinDir = Cygwin.getCygwinDir();
			String cygwinDir = System.getenv("CYGWIN_DIR"); //$NON-NLS-1$
			if (cygwinDir != null) {
				// In Cygwin, bash --login option will change the current directory to HOME directory.
				String cygwinBashBinPath = Path.fromOSString(cygwinDir).toPortableString()
						+ "/bin/bash.exe"; //$NON-NLS-1$
				String[] newCmdArray = new String[4];
				newCmdArray[0] = cygwinBashBinPath;
				newCmdArray[1] = "--login"; //$NON-NLS-1$
				newCmdArray[2] = "-c"; //$NON-NLS-1$

				StringBuilder builder = new StringBuilder();

				String directory;
				if (dir == null) {
					directory = System.getProperty("user.dir"); //$NON-NLS-1$
				} else {
					directory = dir.getAbsolutePath();
				}
				directory = Path.fromOSString(directory).toPortableString();
				builder.append("cd \'"); //$NON-NLS-1$
				builder.append(directory);
				builder.append("\'; "); //$NON-NLS-1$

				for (int i = 0; i < cmdarray.length; i++) {
					String arg = cmdarray[i];
					if (i != 0) {
						builder.append(' ');
					}
					builder.append('\'');
					builder.append(arg);
					builder.append('\'');
				}
				newCmdArray[3] = builder.toString();

				cmdarray = newCmdArray;
			}
		} else {
			String bashPath = "bash"; //$NON-NLS-1$
			if (Platform.getOS().equals(Platform.OS_LINUX)) {
				bashPath = "/bin/bash"; //$NON-NLS-1$
			} else {
				// expect bash in PATH
			}
			String[] newCmdArray = new String[4];
			newCmdArray[0] = bashPath;
			newCmdArray[1] = "--login"; //$NON-NLS-1$
			newCmdArray[2] = "-c"; //$NON-NLS-1$

			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < cmdarray.length; i++) {
				String arg = cmdarray[i];
				if (i != 0) {
					builder.append(' ');
				}
				builder.append('\'');
				builder.append(arg);
				builder.append('\'');
			}
			newCmdArray[3] = builder.toString();

			cmdarray = newCmdArray;

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
			String pathEnvVarValue = env.get("PATH"); //$NON-NLS-1$
			pathEnvVarValue = "/usr/local/bin;/usr/bin;/bin;" + pathEnvVarValue; //$NON-NLS-1$
			env.put("PATH", pathEnvVarValue); //$NON-NLS-1$
			/*
			 * The CYGWIN environment variable is used to configure many global settings for the Cygwin
			 * runtime system. It contains the options listed below, separated by blank characters.
			 * 
			 * @see https://cygwin.com/cygwin-ug-net/using-cygwinenv.html
			 */
			String cygwinEnvVarValue = env.get("CYGWIN"); //$NON-NLS-1$
			if (cygwinEnvVarValue == null) {
				env.put("CYGWIN", "nodosfilewarning"); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (cygwinEnvVarValue.indexOf("nodosfilewarning") == -1) { //$NON-NLS-1$
				cygwinEnvVarValue += " nodosfilewarning"; //$NON-NLS-1$
				env.put("CYGWIN", cygwinEnvVarValue); //$NON-NLS-1$
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
