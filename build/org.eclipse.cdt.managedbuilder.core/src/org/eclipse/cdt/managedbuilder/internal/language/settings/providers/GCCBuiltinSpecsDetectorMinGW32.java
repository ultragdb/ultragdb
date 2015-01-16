package org.eclipse.cdt.managedbuilder.internal.language.settings.providers;

public class GCCBuiltinSpecsDetectorMinGW32 extends GCCBuiltinSpecsDetectorCygwin {
	// ID must match the tool-chain definition in org.eclipse.cdt.managedbuilder.core.buildDefinitions extension point
	private static final String GCC_TOOLCHAIN_ID_MINGW32 = "cdt.managedbuild.toolchain.gnu.mingw_x32.base";  //$NON-NLS-1$

	@Override
	public String getToolchainId() {
		return GCC_TOOLCHAIN_ID_MINGW32;
	}

	@Override
	public GCCBuiltinSpecsDetectorMinGW32 cloneShallow() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorMinGW32) super.cloneShallow();
	}

	@Override
	public GCCBuiltinSpecsDetectorMinGW32 clone() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorMinGW32) super.clone();
	}

}
