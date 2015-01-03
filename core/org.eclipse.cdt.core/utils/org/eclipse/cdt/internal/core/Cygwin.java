/*******************************************************************************
 * Copyright (c) 2012, 2013 Andrew Gvozdev and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrew Gvozdev - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.core;

import org.eclipse.cdt.utils.Cygwin1;

/**
 * A collection of cygwin-related utilities.
 */
public class Cygwin {
	/**
	 * Check if cygwin path conversion utilities are available in the path. Tells whether cygwin is installed
	 * in the path.
	 *
	 * @param envPath
	 *            - list of directories to search for cygwin utilities separated by path separator (format of
	 *            environment variable $PATH) or {@code null} to use current $PATH.
	 * @return {@code true} if cygwin is available, {@code false} otherwise.
	 */
	public static boolean isAvailable(String envPath/* not used */) {
		return isAvailable();
	}

	/**
	 * Check if cygwin path conversion utilities are available in $PATH. Tells whether cygwin is installed in
	 * the path.
	 *
	 * @return {@code true} if cygwin is available, {@code false} otherwise.
	 */
	public static boolean isAvailable() {
		return Cygwin1.isAvailable();
	}

	/**
	 * Conversion from Cygwin path to Windows path. Note that there is no need to cache results, they are
	 * already cached internally.
	 *
	 * @param cygwinPath
	 *            - cygwin path.
	 * @param envPath
	 *            - list of directories to search for cygwin utilities separated by path separator (format of
	 *            environment variable $PATH).
	 * @return Windows style converted path. Note that that also converts cygwin links to their targets.
	 *
	 * @throws UnsupportedOperationException
	 *             if Cygwin is unavailable.
	 */
	public static String cygwinToWindowsPath(String cygwinPath, String envPath/* unused */)
			throws UnsupportedOperationException {
		return cygwinToWindowsPath(cygwinPath);
	}

	/**
	 * Conversion from Cygwin path to Windows path. Note that there is no need to cache results, they are
	 * already cached internally.
	 *
	 * @param cygwinPath
	 *            - cygwin path.
	 * @return Windows style converted path. Note that that also converts cygwin links to their targets.
	 *
	 * @throws UnsupportedOperationException
	 *             if Cygwin is unavailable.
	 */
	public static String cygwinToWindowsPath(String cygwinPath)
			throws UnsupportedOperationException {
		return Cygwin1.cygwinToWindowsPath(cygwinPath);
	}

	/**
	 * Conversion from Windows path to Cygwin path. Note that there is no need to cache results, they are
	 * already cached internally.
	 *
	 * @param windowsPath
	 *            - Windows path.
	 * @param envPath
	 *            - list of directories to search for cygwin utilities (value of environment variable $PATH).
	 * @return Cygwin style converted path.
	 *
	 * @throws UnsupportedOperationException
	 *             if Cygwin is unavailable.
	 */
	public static String windowsToCygwinPath(String windowsPath, String envPath/* unused */)
			throws UnsupportedOperationException {
		return windowsToCygwinPath(windowsPath);
	}

	/**
	 * Conversion from Windows path to Cygwin path. Note that there is no need to cache results, they are
	 * already cached internally.
	 *
	 * @param windowsPath
	 *            - Windows path.
	 * @return Cygwin style converted path.
	 *
	 * @throws UnsupportedOperationException
	 *             if Cygwin is unavailable.
	 */
	public static String windowsToCygwinPath(String windowsPath)
			throws UnsupportedOperationException {
		return Cygwin1.windowsToCygwinPath(windowsPath);
	}

	/**
	 * @return Location of Cygwin root folder "/" on file system in Windows format.
	 */
	public static String getCygwinDir() {
		return Cygwin1.getCygwinDir();
	}

}
