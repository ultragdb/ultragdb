package com.verykit.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class WindowsGCC {
	private static boolean isCygwin32;
	private static boolean isCygwin64;
	private static boolean isMinGW32;
	private static boolean isMinGW64;

	private static String mingw32CrossGccSysroot;
	private static String mingw64CrossGccSysroot;

	static {
		initialize();
		initializeMingwCrossGccSysroot();
	}

	private static String[] execAndGetLines(String[] cmdarray) {
		String[] lines = null;
		List<String> list = new LinkedList<String>();
		try {
			Process process = ProcessFactory.exec(cmdarray, null, null);
			InputStream in = process.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			lines = (String[]) list.toArray(new String[list.size()]);
		} catch (IOException e) {
			return null;
		}
		return lines;
	}

	private static void initialize() {
		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			return;
		}
		String[] lines = execAndGetLines(new String[] { "gcc", "-dumpmachine" });
		if (lines == null || lines.length != 1) {
			return;
		}
		String line = lines[0];
		if (line.equals("x86_64-pc-cygwin")) {
			isCygwin64 = true;
		} else if (line.equals("i686-pc-cygwin")) {
			isCygwin32 = true;
		} else if (line.equals("x86_64-w64-mingw32")) {
			isMinGW64 = true;
		} else if (line.equals("i686-w64-mingw32")) {
			isMinGW32 = true;
		} else {
			// unsupported gcc
			return;
		}
		return;
	}

	private static void initializeMingwCrossGccSysroot() {
		if (isCygwin32) {
			String[] lines = execAndGetLines(new String[] {
					"i686-w64-mingw32-gcc", "-print-sysroot" });
			if (lines == null || lines.length != 1) {
				return;
			}
			String line = lines[0];
			if (line.length() > 0) {
				mingw32CrossGccSysroot = Cygwin.cygwinToWindowsPath(Path
						.fromOSString(line).toPortableString());
			}
		} else if (isCygwin64) {
			String[] lines = execAndGetLines(new String[] {
					"x86_64-w64-mingw32-gcc", "-print-sysroot" });
			if (lines == null || lines.length != 1) {
				return;
			}
			String line = lines[0];
			if (line.length() > 0) {
				mingw64CrossGccSysroot = Cygwin.cygwinToWindowsPath(Path
						.fromOSString(line).toPortableString());
			}
		}
	}

	public static boolean isCygwin32() {
		return isCygwin32;
	}

	public static boolean isCygwin64() {
		return isCygwin64;
	}

	public static boolean isMinGW32() {
		return isMinGW32;
	}

	public static boolean isMinGW64() {
		return isMinGW64;
	}

	public static String getMingw32CrossGccSysroot() {
		return mingw32CrossGccSysroot;
	}

	public static String getMingw64CrossGccSysroot() {
		return mingw64CrossGccSysroot;
	}
}
