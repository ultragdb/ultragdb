package org.eclipse.cdt.managedbuilder.gnu.cygwin;

import org.eclipse.cdt.managedbuilder.core.IToolChain;
import org.osgi.framework.Version;

/**
 * This class implements the IManagedIsToolChainSupported for the Gnu Cygwin2MinGW64 tool-chain
 *
 * @noextend This class is not intended to be subclassed by clients.
 */
public class IsGnuCygwin2MinGW64ToolChainSupported extends IsGnuCygwinToolChainSupported {
	@Override
	public boolean isSupported(IToolChain toolChain, Version version, String instance) {
		return super.isSupported(toolChain, version, instance);
	}
}
