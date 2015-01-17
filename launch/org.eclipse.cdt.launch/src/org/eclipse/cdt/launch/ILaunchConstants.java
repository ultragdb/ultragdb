package org.eclipse.cdt.launch;

import org.eclipse.cdt.launch.internal.ui.LaunchUIPlugin;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @since 4.0
 */
public interface ILaunchConstants {
	public static final String PREFIX = LaunchUIPlugin.PLUGIN_ID + "."; //$NON-NLS-1$
	/**
	 * Attribute key to be passed to DebugPlugin.newProcess to specify the type
	 * of process that should be created by our IProcessFactory.
	 * 
	 * @since 4.1
	 */
	public static final String PROCESS_TYPE_CREATION_ATTR = PREFIX
			+ "createProcessType"; //$NON-NLS-1$
	/**
	 * Attribute value of PROCESS_TYPE_CREATION_ATTR to be passed to
	 * DebugPlugin.newProcess to require the creation of an TerminalEmulatorRuntimeProcess
	 * instead of a RuntimeProcess (which is used by default).
	 * 
	 * @since 4.1
	 */
	public static final String TERMINAL_EMULATOR_PROCESS_CREATION_VALUE = PREFIX
			+ "terminalEmulatorProcess"; //$NON-NLS-1$
	/**
	 * Attribute value of PROCESS_TYPE_CREATION_ATTR to be passed to
	 * DebugPlugin.newProcess to require the creation of an MingwFakeTerminalEmulatorRuntimeProcess
	 * instead of a RuntimeProcess (which is used by default).
	 * 
	 * @since 4.1
	 */
	public static final String MINGW_FAKE_TERMINAL_EMULATOR_PROCESS_CREATION_VALUE = PREFIX
			+ "mingwFakeTerminalEmulatorProcess"; //$NON-NLS-1$

}
