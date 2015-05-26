package com.verykit.license.handlers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.verykit.license.License;
import com.verykit.license.LicenseManager;

public class InstallLicenseHandler extends AbstractHandler {

	/**
	 * run the handler proper
	 * 
	 * @param shell
	 * @return <code>true</code> if successfully installed a license,
	 *         <code>false</code> otherwise.
	 */
	public static boolean run(Shell shell) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		fileDialog.setFilterNames(new String[] { "License File" });
		fileDialog.setFilterExtensions(new String[] { "license" });
		// fileDialog.setFilterPath(System.getProperty("user.dir"));
		fileDialog.setText("Choose License File");
		String srcLicenseFilePath = fileDialog.open();
		if (srcLicenseFilePath == null) {
			MessageDialog.openError(shell, "No License File Selected",
					"No license file is selected\n");
			return false;
		}

		File srcLicenseFile = new File(srcLicenseFilePath);

		License license = LicenseManager.load(srcLicenseFile);

		if (!license.isValid) {
			MessageDialog.openError(shell, "License Error",
					"The content of this license file is invalid:\n"
							+ srcLicenseFilePath);
			return false;
		}

		File licenseFile = LicenseManager.getLicenseFileLocation();

		try {
			Files.copy(srcLicenseFile.toPath(), licenseFile.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// e.printStackTrace();
			MessageDialog.openError(shell, "Error",
					"Error in installing license file.\n");
			return false;
		}

		LicenseManager.license = license;

		MessageDialog.openInformation(shell, "Success",
				"Success in installing license file.\n");
		return true;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);
		Shell shell = window.getShell();
		run(shell);

		return null;
	}
}
