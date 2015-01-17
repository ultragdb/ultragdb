package org.eclipse.cdt.dsf.mi.service.command.commands;

import org.eclipse.cdt.dsf.mi.service.IMIContainerDMContext;

public class MIInferiorNewConsoleSet extends MIGDBSet {
	public MIInferiorNewConsoleSet(IMIContainerDMContext dmc, boolean isSet) {
		super(dmc, new String[] { "new-console", isSet ? "on" : "off" });//$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}
}
