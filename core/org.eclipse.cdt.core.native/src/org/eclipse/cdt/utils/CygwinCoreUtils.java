package org.eclipse.cdt.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.cdt.internal.core.natives.CNativePlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class CygwinCoreUtils {
	private static String path;

	public static String getPath() {
		return path;
	}

	static {
		initialize();
	}

	static void initialize() {
		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			return;
		}
		Bundle bundle = Platform.getBundle(CNativePlugin.PLUGIN_ID);
		try {
			String cygwinDir;
			if (Platform.getOSArch().equals(Platform.ARCH_X86_64)) {
				cygwinDir = "cygwin64"; //$NON-NLS-1$
			} else {
				cygwinDir = "cygwin"; //$NON-NLS-1$
			}
			URL url = FileLocator.find(bundle, new Path("$os$/" + cygwinDir + "/bin"), null); //$NON-NLS-1$ //$NON-NLS-2$
			if (url != null) {
				url = FileLocator.resolve(url);
				String path = url.getFile();
				File file = new File(path);
				if (file.exists()) {
					CygwinCoreUtils.path = Path.fromOSString(file.getCanonicalPath())
							.toPortableString();
				}
			}
		} catch (IOException e) {
		}
	}
}
