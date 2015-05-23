/*******************************************************************************
 * Copyright (c) 2005, 2013 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Intel Corporation - Initial API and implementation
 *     Enrico Ehrich - http://bugs.eclipse.org/233866
 *     Marc-Andre Laperle - fix for Cygwin GCC is Not detected (bug 303900)
 *     Andrew Gvozdev - changes to recognize $CYGWIN_HOME
 *******************************************************************************/
package org.eclipse.cdt.managedbuilder.gnu.cygwin;

import com.verykit.common.Cygwin;
import org.eclipse.cdt.managedbuilder.core.IBuildPathResolver;
import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.core.runtime.Platform;

/**
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CygwinPathResolver implements IBuildPathResolver {
//	private static final String ENV_PATH = "PATH"; //$NON-NLS-1$
	private static final String DELIMITER_UNIX = ":";    //$NON-NLS-1$
	private static final String DELIMITER_WIN  = ";";    //$NON-NLS-1$

//	private static final String GCC_VERSION_CMD  = "gcc --version";    //$NON-NLS-1$
//	private static final String MINGW_SPECIAL = "mingw ";    //$NON-NLS-1$
//	private static final String CYGWIN_SPECIAL = "cygwin ";    //$NON-NLS-1$

	@Override
	public String[] resolveBuildPaths(int pathType, String variableName, String variableValue, IConfiguration configuration) {
		if(!isWindows()) {
			return variableValue.split(DELIMITER_UNIX);
		} else if(isMinGW(configuration)) {
			return variableValue.split(DELIMITER_WIN);
		}

//		String[] lines = executeInConfigurationContext(CYGPATH_PATH_LIST_TO_WINDOWS + variableValue, configuration);
//		if (lines != null && lines.length > 0) {
//			String pathList = lines[0].replace(BACKSLASH, SLASH);
//			return pathList.split(DELIMITER_WIN);
//		}
//		
//		return variableValue.split(DELIMITER_UNIX);
		String[] paths = variableValue.split(DELIMITER_UNIX);
		String[] newPaths = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			String newPath = Cygwin.cygwinToWindowsPath(path);
			newPaths[i] = newPath;
		}
		return newPaths;
	}


	public static boolean isWindows() {
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			return true;
		} else {
			return false;
		}
	}

//	/**
//	 * Reads required value from registry. Looks in both
//	 * HKEY_CURRENT_USER and HKEY_LOCAL_MACHINE
//	 *
//	 * @param key Registry key
//	 * @param name Registry value to read
//	 * @return corresponding string value or null if nothing found
//	 */
//	private static String readValueFromRegistry(String key, String name) {
//		WindowsRegistry registry = WindowsRegistry.getRegistry();
//		if (registry != null) {
//			String value = registry.getCurrentUserValue(key, name);
//			if (value == null) {
//				value = registry.getLocalMachineValue(key, name);
//			}
//			if (value != null) {
//				return value.replace(BACKSLASH, SLASH);
//			}
//		}
//		return null;
//	}

//	/**
//	 * Resolve and return full path to program in context of configuration.
//	 *
//	 * @param program - program to resolve.
//	 * @param cfg - configuration context.
//	 * @return absolute path to program.
//	 */
//	private static String resolveProgram(String program, IConfiguration cfg) {
//		String envPathValue = null;
//		try {
//			IEnvironmentVariable envPathVar = ManagedBuildManager.getEnvironmentVariableProvider().getVariable(ENV_PATH, cfg, true);
//			if (envPathVar != null) {
//				envPathValue = envPathVar.getValue();
//				IPath progPath = PathUtil.findProgramLocation(program, envPathValue);
//				if (progPath != null) {
//					program = progPath.toPortableString();
//				}
//				// this resolves cygwin symbolic links
//				program = Cygwin.cygwinToWindowsPath(program);
//			}
//		} catch (Exception e) {
//			GnuUIPlugin.getDefault().log(new Status(IStatus.WARNING, GnuUIPlugin.PLUGIN_ID, "Problem trying to find program [" + program + "] in $PATH=[" + envPathValue + "]", e));
//		}
//		return program;
//	}
//
//	/**
//	 * Return environment in envp format, see {@link Runtime#exec(String, String[])}.
//	 *
//	 * @param cfg - configuration.
//	 * @return environment as array of strings in format name=value.
//	 */
//	private static String[] getEnvp(IConfiguration cfg) {
//		IEnvironmentVariable vars[] = ManagedBuildManager.getEnvironmentVariableProvider().getVariables(cfg,true);
//		String envp[] = new String[vars.length];
//		for(int i = 0; i < envp.length; i++) {
//			envp[i] = vars[i].getName() +'=';
//			String value = vars[i].getValue();
//			if(value != null)
//				envp[i] += value;
//		}
//		return envp;
//	}
//
//	/**
//	 * Execute command taking in account configuration environment.
//	 *
//	 * @param cmd - command to execute.
//	 * @param cfg - configuration context.
//	 * @return command output as string array.
//	 */
//	private static String[] executeInConfigurationContext(String cmd, IConfiguration cfg) {
//		String[] args = cmd.split(" "); //$NON-NLS-1$
//		args[0] = resolveProgram(args[0], cfg);
//
//		String[] result = null;
//		try {
//			String[] envp = getEnvp(cfg);
//			Process proc = ProcessFactory.getFactory().exec(args, envp);
//			if (proc != null) {
//				InputStream ein = proc.getInputStream();
//				try {
//					BufferedReader d1 = new BufferedReader(new InputStreamReader(ein));
//					ArrayList<String> ls = new ArrayList<String>(10);
//					String s;
//					while ((s = d1.readLine()) != null ) {
//						ls.add(s);
//					}
//					result = ls.toArray(new String[0]);
//				} finally {
//					ein.close();
//				}
//			}
//		} catch (IOException e) {
//			GnuUIPlugin.getDefault().log(new Status(IStatus.ERROR, GnuUIPlugin.PLUGIN_ID, "Error executing program [" +cmd + "]", e));
//		}
//		return result;
//	}

	public static boolean isMinGW(IConfiguration cfg) {
		return false;
//		String versionInfo[] = executeInConfigurationContext(GCC_VERSION_CMD, cfg);
//		if(versionInfo != null) {
//			for(int i = 0; i < versionInfo.length; i++) {
//				if(versionInfo[i].indexOf(MINGW_SPECIAL) != -1)
//					return true;
//				else if(versionInfo[i].indexOf(CYGWIN_SPECIAL) != -1)
//					return false;
//			}
//		}
//		return false;
	}
}
