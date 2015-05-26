package com.verykit.license.startup;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;

import com.verykit.license.License;
import com.verykit.license.LicenseManager;
import com.verykit.license.handlers.InstallLicenseHandler;

public class LicenseStartup implements IStartup {

	@Override
	public void earlyStartup() {
		LicenseManager.getUGDir();

		// check license
		File licenseFile = LicenseManager.getLicenseFileLocation();

		License license = LicenseManager.load(licenseFile);
		if (license.isValid) {
			LicenseManager.license = license;
			return;
		}

		// no valid license installed

		long remainingDaysOfTrial = LicenseManager.getRemainingDaysOfTrial();
		if (remainingDaysOfTrial > 0) {
			// continue trial
			return;
		}
		// 30 days of trial period expires
		// Eclipse Jobs and Background Processing - Tutorial
		// http://www.vogella.com/tutorials/EclipseJobs/article.html
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				boolean yes = MessageDialog.openQuestion(Display.getDefault()
						.getActiveShell(), "Trial period expires",
						"Trial period has expired, you must install a license");
				if (yes) {
					// install license
					boolean installed = InstallLicenseHandler.run(Display
							.getDefault().getActiveShell());
					if (installed) {
						return;
					}
				}
				// exit
				LicenseManager.exit();
			}
		});
	}
}
