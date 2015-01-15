package org.eclipse.cdt.managedbuilder.internal.language.settings.providers;

public class GCCBuiltinSpecsDetectorCygwin2MinGW64 extends GCCBuiltinSpecsDetectorCygwin {
	// ID must match the tool-chain definition in org.eclipse.cdt.managedbuilder.core.buildDefinitions extension point
	private static final String GCC_TOOLCHAIN_ID_CYGWIN2MINGW64 = "cdt.managedbuild.toolchain.gnu.cygwin2mingw64.base";  //$NON-NLS-1$

	@Override
	public String getToolchainId() {
		return GCC_TOOLCHAIN_ID_CYGWIN2MINGW64;
	}

	@Override
	public GCCBuiltinSpecsDetectorCygwin2MinGW64 cloneShallow() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorCygwin2MinGW64) super.cloneShallow();
	}

	@Override
	public GCCBuiltinSpecsDetectorCygwin2MinGW64 clone() throws CloneNotSupportedException {
		return (GCCBuiltinSpecsDetectorCygwin2MinGW64) super.clone();
	}

}
