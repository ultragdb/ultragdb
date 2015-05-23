package com.verykit.common;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class ProcessFactory {

	private static TreeMap<String, String> envpToEnvMap(String[] envp) {
		TreeMap<String, String> environment = new TreeMap<String, String>(
				String.CASE_INSENSITIVE_ORDER);
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
		TreeMap<String, String> environment = new TreeMap<String, String>(
				String.CASE_INSENSITIVE_ORDER);
		Map<String, String> env = pb.environment();
		environment.putAll(env);
		env.clear();
		if (envp != null) {
			TreeMap<String, String> environment1 = envpToEnvMap(envp);
			environment.putAll(environment1);
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
			String cygwinEnvVarValue = environment.get("CYGWIN"); //$NON-NLS-1$
			if (cygwinEnvVarValue == null) {
				environment.put("CYGWIN", nodosfilewarnings); //$NON-NLS-1$
			} else if (cygwinEnvVarValue.indexOf(nodosfilewarnings) == -1) {
				cygwinEnvVarValue += " " + nodosfilewarnings; //$NON-NLS-1$
				environment.put("CYGWIN", cygwinEnvVarValue); //$NON-NLS-1$
			}
			String mingwSysroot = null;
			if (WindowsGCC.isCygwin32()) {
				mingwSysroot = WindowsGCC.getMingw32CrossGccSysroot();
			} else if (WindowsGCC.isCygwin64()) {
				mingwSysroot = WindowsGCC.getMingw64CrossGccSysroot();
			}
			if (mingwSysroot != null) {
				String pathEnvVarValue = environment.get("PATH"); //$NON-NLS-1$
				pathEnvVarValue += ";" + mingwSysroot + "/mingw/bin";
				environment.put("PATH", pathEnvVarValue); //$NON-NLS-1$
			}
		}
		{
			boolean setLangEnvVartoDefault = false;
			String langEnvValue = environment.get("LANG"); //$NON-NLS-1$
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
					environment.put("LANG", langEnvValue); //$NON-NLS-1$
					environment.put("LC_ALL", langEnvValue); //$NON-NLS-1$
				}
			}
			if (setLangEnvVartoDefault) {
				environment.put("LANG", "en_US.UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
				environment.put("LC_ALL", "en_US.UTF-8"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}

		env.putAll(environment);
		if (dir != null) {
			pb.directory(dir);
		}
		/*
		 * for debug purpose
		 */
		StringBuilder sb = new StringBuilder();
		{
			for (int i = 0; i < cmdarray.length; i++) {
				if (i != 0) {
					sb.append(' ');
				}
				sb.append('\'');
				sb.append(cmdarray[i]);
				sb.append('\'');
			}
			sb.append("\n\n");

			Iterator<Entry<String, String>> iterator = environment.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator
						.next();
				sb.append(entry.getKey());
				sb.append('=');
				sb.append(entry.getValue());
				sb.append('\n');
			}

			sb.append("\n");
			if (dir != null) {
				sb.append(dir.toString());
			} else {
				sb.append("no directory specified");
			}
			sb.append("\n");
		}
		/*
		 * set breakpoint on next line to inspect sb when debugging, to see the
		 * ultimate parameters of ProcessBuilder
		 */
		Process p = pb.start();
		return p;
	}

}
