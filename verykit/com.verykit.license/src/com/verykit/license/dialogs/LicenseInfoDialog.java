package com.verykit.license.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.verykit.license.LicenseManager;

public class LicenseInfoDialog extends Dialog {
	private Text text;

	public LicenseInfoDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());

		text = new Text(container, SWT.BORDER | SWT.MULTI | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 10);
		fd_text.left = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(100, -10);
		fd_text.bottom = new FormAttachment(100, -10);
		text.setLayoutData(fd_text);

		if (LicenseManager.license != null) {
			text.setText(LicenseManager.license.licenseFileContentDecoded);
		} else {

			long remainingDaysOfTrial = LicenseManager
					.getRemainingDaysOfTrial();
			if (remainingDaysOfTrial < 0) {
				remainingDaysOfTrial = 0;
			}
			String string = "No license installed.\nTrial period has "
					+ remainingDaysOfTrial + " days left.";
			text.setText(string);
		}

		return container;
	}

	// overriding this methods allows you to set the
	// title of the custom dialog
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("License Info");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(432, 496);
	}
}
