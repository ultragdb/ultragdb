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

import java.io.File;

import org.eclipse.cdt.utils.PathUtil;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * A collection of cygwin-related utilities.
 */
public class Cygwin {
	private static final String ENV_CYGWIN_DIR = "CYGWIN_DIR"; //$NON-NLS-1$

	private static String cygwinDir = null;

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
		if (Platform.getOS().equals(Platform.OS_WIN32) && getCygwinDir() != null) {
			return true;
		} else {
			return false;
		}
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
		if (cygwinPath == null || cygwinPath.trim().length() == 0)
			return cygwinPath;

		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			throw new UnsupportedOperationException("Not a Windows system, Cygwin is unavailable."); //$NON-NLS-1$
		}
		String windowsPath;
		IPath path = Path.fromOSString(cygwinPath);
		if (path.getDevice() != null) {
			// already a windows path
			windowsPath = path.toPortableString();
			return windowsPath;
		}
		String[] segments = path.segments();
		String[] newSegments;

		String cygwinDir = getCygwinDir();
		if (cygwinDir == null) {
			throw new UnsupportedOperationException("can not get Cygwin installation directory"); //$NON-NLS-1$
		}
		if (cygwinPath.startsWith("/")) { //$NON-NLS-1$
			// absolute path
			if (segments.length < 0) {
				throw new UnsupportedOperationException("invalid Cygwin Path"); //$NON-NLS-1$
			} else if (segments.length >= 2) {
				if (segments[0].equals("cygdrive")) { //$NON-NLS-1$
					String device = segments[1].toUpperCase();

					newSegments = new String[segments.length - 2];
					System.arraycopy(segments, 2, newSegments, 0, segments.length - 2);

					StringBuilder builder = new StringBuilder();
					builder.append(device);
					builder.append(':');
					for (String s : newSegments) {
						builder.append('/');
						builder.append(s);
					}
					windowsPath = builder.toString();
					return windowsPath;
				}
				if (segments[0].equals("usr") && (segments[1].equals("bin") || segments[1].equals("lib"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					/*
					 * /usr/lib --> /lib ; /usr/bin --> /bin ; /usr/include unchanged ¡£
					 */
					newSegments = new String[segments.length - 1];
					System.arraycopy(segments, 1, newSegments, 0, segments.length - 1);
					segments = newSegments;
				}
			}
			// cygwinPath.startsWith("/") && segments.length >= 0
			StringBuilder builder = new StringBuilder();
			builder.append(cygwinDir);
			for (String s : segments) {
				builder.append('/');
				builder.append(s);
			}
			windowsPath = builder.toString();

		} else {
			// relative path
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < segments.length; i++) {
				String s = segments[i];
				if (i != 0) {
					builder.append('/');
				}
				builder.append(s);
			}
			windowsPath = builder.toString();
		}

		return windowsPath;
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
		if (windowsPath == null || windowsPath.trim().length() == 0)
			return windowsPath;

		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			throw new UnsupportedOperationException("Not a Windows system, Cygwin is unavailable."); //$NON-NLS-1$
		}
		String cygwinDir = getCygwinDir();
		if (cygwinDir == null) {
			throw new UnsupportedOperationException("can not get Cygwin installation directory"); //$NON-NLS-1$
		}

		IPath cygwinDirPath = Path.fromOSString(cygwinDir);
		IPath path = Path.fromOSString(windowsPath);
		String cygwinPath;
		if (cygwinDirPath.isPrefixOf(path)) {
			int matchingFirstSegments = cygwinDirPath.matchingFirstSegments(path);
			String[] segments = path.segments();
			String[] newSegments = new String[segments.length - matchingFirstSegments];
			System.arraycopy(segments, matchingFirstSegments, newSegments, 0, segments.length
					- matchingFirstSegments);

			StringBuilder builder = new StringBuilder();
			for (String s : newSegments) {
				builder.append('/');
				builder.append(s);
			}
			cygwinPath = builder.toString();
		} else {
			String device = path.getDevice().replace(':', ' ').trim();
			String[] segments = path.segments();
			String[] newSegments = new String[segments.length + 2];
			newSegments[0] = "cygdrive"; //$NON-NLS-1$
			newSegments[1] = device.toLowerCase();
			System.arraycopy(segments, 0, newSegments, 2, segments.length);

			StringBuilder builder = new StringBuilder();
			for (String s : newSegments) {
				builder.append('/');
				builder.append(s);
			}
			cygwinPath = builder.toString();
		}

		return cygwinPath;
	}

	/**
	 * initialize static data field cygwinDir.
	 */
	private static void initializeCygwinDir() {
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			IPath cygwin1DllPath = PathUtil.findProgramLocation("cygwin1.dll"); //$NON-NLS-1$
			if (cygwin1DllPath != null && cygwin1DllPath.segmentCount() >= 2
					&& cygwin1DllPath.segments()[cygwin1DllPath.segmentCount() - 2].equals("bin")) { //$NON-NLS-1$
				IPath cygwinDirPath = cygwin1DllPath.removeLastSegments(2);
				cygwinDir = cygwinDirPath.toPortableString();
				return;
			} else {
				String cygwinDirEnvVarValue = System.getenv(ENV_CYGWIN_DIR);
				if (cygwinDirEnvVarValue != null) {
					cygwinDirEnvVarValue = Path.fromOSString(cygwinDirEnvVarValue)
							.toPortableString();
					IPath dirLocation = new Path(cygwinDirEnvVarValue);
					File file = null;
					file = dirLocation.append("/bin/cygwin1.dll").toFile(); //$NON-NLS-1$
					if (file != null && file.isFile() && file.canRead()) {
						cygwinDir = cygwinDirEnvVarValue;
						return;
					}
				}
			}
			// Cygwin not found, set cygwinDir to default
			cygwinDir = "C:/cygwin64"; //$NON-NLS-1$
		}
	}

	/**
	 * @return Location of Cygwin root folder "/" on file system in Windows format.
	 */
	public static String getCygwinDir() {
		if (cygwinDir == null) {
			initializeCygwinDir();
		}
		return cygwinDir;

	}

}
