package org.eclipse.cdt.dsf.gdb.launching;

import java.util.Map;

import org.eclipse.cdt.common.WindowsGCC;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugException;
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
	@Override
	public synchronized boolean isTerminated() {
		if (fTerminated) {
			return true;
		}
		return super.isTerminated();
	}

	private boolean fTerminated = false;

	@Override
	public void terminate() throws DebugException {
		if (Platform.getOS().equals(Platform.OS_WIN32) && (WindowsGCC.isMinGW32() || WindowsGCC.isMinGW64() )) {
			try {
				super.terminate();
			} catch (DebugException e) {
				// ignore all errors
			}
			terminated();
			//always success
			fTerminated = true;
			return;
		}
		super.terminate();
	}

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
