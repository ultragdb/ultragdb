package org.eclipse.cdt.dsf.gdb.launching;

import java.util.Map;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.debug.core.model.RuntimeProcess;

/**
 * Run mode RuntimeProcess. No ProcessConsole will be created for this runtime
 * process. Because in run mode, the underlying system process is actually the
 * terminal emulator process, we don't need a ProcessConsole for terminal
 * emulator.
 */
public class TerminalEmulatorRuntimeProcess extends RuntimeProcess {

	public TerminalEmulatorRuntimeProcess(ILaunch launch, Process process, String name,
			Map<String, String> attributes) {
		super(launch, process, name, attributes);

	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		/*
		 * Chiheng Xu : let getStreamsProxy() to return null, so no
		 * ProcessConsole will be created for this process.
		 * 
		 * @see
		 * org.eclipse.debug.internal.ui.views.console.ProcessConsoleManager
		 * #launchChanged
		 */
		return null;
	}

	@Override
	protected IStreamsProxy createStreamsProxy() {
		/*
		 * Chiheng Xu : does create a IStreamsProxy, to eat the stdout/stderr
		 * outputs of the process.
		 */
		return super.createStreamsProxy();
	}

}
