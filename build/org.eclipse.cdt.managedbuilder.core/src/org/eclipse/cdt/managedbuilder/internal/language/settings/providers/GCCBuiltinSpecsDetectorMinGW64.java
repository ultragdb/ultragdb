package org.eclipse.cdt.managedbuilder.internal.language.settings.providers;

public class GCCBuiltinSpecsDetectorMinGW64 extends GCCBuiltinSpecsDetectorCygwin {
	// ID must match the tool-chain definition in org.eclipse.cdt.managedbuilder.core.buildDefinitions extension point
	private static final String GCC_TOOLCHAIN_ID_MINGW64 = "cdt.managedbuild.toolchain.gnu.mingw_x64.base";  //$NON-NLS-1$

	@Override
	public String getToolchainId() {
		return GCC_TOOLCHAIN_ID_MINGW64;
	}

	@Override
	public GCCBuiltinSpecsDetectorMinGW64 cloneShallow() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorMinGW64) super.cloneShallow();
	}

	@Override
	public GCCBuiltinSpecsDetectorMinGW64 clone() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorMinGW64) super.clone();
	}

}
