package org.eclipse.cdt.dsf.gdb.launching;

import java.util.Map;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;

public class MingwFakeTerminalEmulatorRuntimeProcess extends
		TerminalEmulatorRuntimeProcess {
	public MingwFakeTerminalEmulatorRuntimeProcess(ILaunch launch,
			Process process, String name, Map<String, String> attributes) {
		super(launch, process, name, attributes);

	}

	@Override
	public synchronized boolean isTerminated() {
		return true;
	}

	@Override
	public void terminate() throws DebugException {
	}

}