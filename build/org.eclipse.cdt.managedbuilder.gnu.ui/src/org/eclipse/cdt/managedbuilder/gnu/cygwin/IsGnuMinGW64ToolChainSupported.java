package org.eclipse.cdt.managedbuilder.gnu.cygwin;

import com.verykit.common.WindowsGCC;
import org.eclipse.cdt.managedbuilder.core.IManagedIsToolChainSupported;
import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

/**
 * This class implements the IManagedIsToolChainSupported for the Gnu Cygwin2MinGW64 tool-chain
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class IsGnuMinGW64ToolChainSupported implements IManagedIsToolChainSupported {
	@Override
	public boolean isSupported(IToolChain toolChain, Version version, String instance) {
		return WindowsGCC.isCygwin64();
	}
}
