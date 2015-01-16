package org.eclipse.cdt.managedbuilder.gnu.cygwin;

import org.eclipse.cdt.managedbuilder.core.IConfiguration;
import org.eclipse.cdt.managedbuilder.envvar.IBuildEnvironmentVariable;
import org.eclipse.cdt.managedbuilder.envvar.IEnvironmentVariableProvider;

public class GnuMinGW64ConfigurationEnvironmentSupplier extends
		GnuCygwinConfigurationEnvironmentSupplier {

	@Override
	public IBuildEnvironmentVariable getVariable(String variableName, IConfiguration configuration,
			IEnvironmentVariableProvider provider) {
		return super.getVariable(variableName, configuration, provider);
	}

	@Override
	public IBuildEnvironmentVariable[] getVariables(IConfiguration configuration,
			IEnvironmentVariableProvider provider) {
		return super.getVariables(configuration, provider);
	}

}
