package org.eclipse.cdt.common;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class ProcessFactory {

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

	public static Process exec(String cmdarray[], String[] envp, File dir)
			throws IOException {
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
			 * The CYGWIN environment variable is used to configure many global
			 * settings for the Cygwin runtime system. It contains the options
			 * listed below, separated by blank characters.
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

}
