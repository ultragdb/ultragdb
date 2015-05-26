package com.verykit.common;

import org.eclipse.core.runtime.Platform;

public class VersionInfo {
	public static String product;
	public static String platform;
	public static String version;

	static {
		product = "UltraGDB";
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			platform = "Windows 64-bit";
		} else if (Platform.getOS().equals(Platform.OS_LINUX)) {
			platform = "Linux 64-bit";
		}
		version = "1.0";
	}

}
