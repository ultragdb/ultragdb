package org.eclipse.cdt.utils.pty;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.cdt.internal.core.natives.CNativePlugin;
import org.eclipse.cdt.utils.spawner.ProcessFactory;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class PTY2 {
	private Process terminalEmulator;
	private String slaveName;

	public PTY2() throws IOException {
		/*
		 * launch the patched terminal emulator process, and get the pty's slave name from stdout of the
		 * terminal emulator process
		 */
		String command = null;
		InputStream in = null;
		Bundle bundle = Platform.getBundle(CNativePlugin.PLUGIN_ID);

		try {
			command = PTY2Util.getTerminalEmulatorCommand();

			terminalEmulator = ProcessFactory.getFactory().exec(
					new String[] { command, "--openpty", "--title", "Terminal Emulator" }); //$NON-NLS-1$
			in = terminalEmulator.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);

			String line = br.readLine();
			if (line != null) {
				slaveName = line;
			}

		} catch (IOException e) {

		} finally {
			//if (in != null)
			//	in.close();
		}
		if (slaveName == null) {
			throw new IOException("can not start terminal emulator and get pty's slave name"); //$NON-NLS-1$
		}
	}

	public Process getTerminalEmulator() {
		return terminalEmulator;
	}

	public String getSlaveName() {
		return slaveName;
	}
}
