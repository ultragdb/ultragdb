package org.eclipse.cdt.utils.pty;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.cdt.internal.core.natives.CNativePlugin;
import org.eclipse.cdt.utils.spawner.ProcessFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class PTY2Util {

	public static String getTerminalEmulatorCommand() throws IOException {
		String command = null;
		Bundle bundle = Platform.getBundle(CNativePlugin.PLUGIN_ID);

		URL url = FileLocator.find(bundle, new Path("$os$/mintty.exe"), null); //$NON-NLS-1$
		if (url != null) {
			url = FileLocator.resolve(url);
			String path = url.getFile();
			File file = new File(path);
			if (file.exists()) {
				command = file.getCanonicalPath();
			}
		}

		if (command == null) {
			throw new IOException("can not find terminal emulator executable"); //$NON-NLS-1$
		}

		return command;
	}

	public static String[] getTerminalEmulatorCommandArray(String[] commandArray) {
		String command = null;
		try {
			command = PTY2Util.getTerminalEmulatorCommand();
		} catch (IOException e) {
		}
		String[] terminalEmulatorCommand = { command, "--hold=always", "--exec" }; //$NON-NLS-1$  //$NON-NLS-2$ 

		String[] result = new String[terminalEmulatorCommand.length + commandArray.length];
		System.arraycopy(terminalEmulatorCommand, 0, result, 0, terminalEmulatorCommand.length);
		System.arraycopy(commandArray, 0, result, terminalEmulatorCommand.length,
				commandArray.length);

		return result;
	}

}
