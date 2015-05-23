/*******************************************************************************
 * Copyright (c) 2007 Nokia and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nokia - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.debug.ui.importexecutable;

import java.io.File;

import com.verykit.common.WindowsGCC;
import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.debug.core.ICDTLaunchConfigurationConstants;
import org.eclipse.cdt.ui.CUIPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.progress.UIJob;

public abstract class AbstractImportExecutableWizard extends Wizard implements INewWizard {

	// The ImportExecutableWizard lets you select one or more executables and
	// import them into the workspace. You can bring the executables into an
	// existing project or have the wizard create a new project that
	// will contains the executables and allow you to debug them. The wizard can
	// also create a default launch configuration for you that's pre configured
	// to debug the executables.

	public static final String DEBUG_PROJECT_ID = "org.eclipse.cdt.debug"; //$NON-NLS-1$

	protected ImportExecutablePageOne pageOne;

	protected ImportExecutablePageTwo pageTwo;

	public void addBinaryParsers(IProject newProject) throws CoreException {
		ICProjectDescription pd = CCorePlugin.getDefault().getProjectDescription(newProject);
		String[] parserIDs = pageOne.getSupportedBinaryParserIds();
		for (int i = 0; i < parserIDs.length; i++) {
			pd.getDefaultSettingConfiguration().create(CCorePlugin.BINARY_PARSER_UNIQ_ID, parserIDs[i]);
		}
		CCorePlugin.getDefault().setProjectDescription(newProject, pd, true, new NullProgressMonitor());
	}
	
	/**
	 * Adds the executables to a new or existing project. The executables are
	 * added as external links.
	 * If an executable of the same name already exists then the existing linked
	 * resource's location is replaced by the local location's value.
	 * 
	 * @param project -
	 *            project receiving the executables
	 * @throws CoreException
	 */
	private void addExecutables(ICProject cproject) {
		IProject project = cproject.getProject();
		String[] executables = pageOne.getSelectedExecutables();

		if (pageOne.isSelectSingleFile()) {
			String executable = executables[0];
			IPath locationAbsolute = Path.fromOSString(executable);
			IPath directory = locationAbsolute.removeLastSegments(1);
			try {
				IFile exeFile = project.getFile(locationAbsolute.lastSegment());
				exeFile.createLink(locationAbsolute, IResource.REPLACE, null);
			} catch (Exception e) {
				this.getImportExecutablePage2().setErrorMessage("Error importing: " + executable); //$NON-NLS-1$
			}
		} else {
			IPath directory = Path.fromOSString(pageOne.getPreviouslySearchedDirectory());

			for (int i = 0; i < executables.length; i++) {
				try {
					IPath locationAbsolute = Path.fromOSString(executables[i]);
					IPath locationRelative = locationAbsolute.makeRelativeTo(directory);
					IFile exeFile = null;
					IFolder exeFolder = null;
					int segmentCount = locationRelative.segmentCount();
					if (segmentCount >= 2) {
						String segment = locationRelative.segment(0);
						//Eclipse hate directory name like ".libs" on Windows. So replace "." with "_"
						segment = segment.replace('.', '_');
						exeFolder = project.getFolder(segment);
						if (!exeFolder.exists()) {
							exeFolder.create(IResource.VIRTUAL, false, null);
						}
						for (int j = 1; j < segmentCount - 1; j++) {
							segment = locationRelative.segment(j);
							//Eclipse hate directory name like ".libs" on Windows. So replace "." with "_"
							segment = segment.replace('.', '_');
							exeFolder = exeFolder.getFolder(segment);
							if (!exeFolder.exists()) {
								exeFolder.create(IResource.VIRTUAL, false, null);
							}
						}

						exeFile = exeFolder.getFile(locationRelative.lastSegment());
						exeFile.createLink(locationAbsolute, IResource.REPLACE, null);
					} else if (locationRelative.segmentCount() == 1) {
						exeFile = project.getFile(locationRelative);
						exeFile.createLink(locationAbsolute, IResource.REPLACE, null);
					}

				} catch (Exception e) {
					this.getImportExecutablePage2().setErrorMessage("Error importing: " + executables[i]); //$NON-NLS-1$
				}
			}
		}
	}

	@Override
	public void addPages() {
		super.addPages();
		pageOne = new ImportExecutablePageOne(this);
		addPage(pageOne);
		pageTwo = new ImportExecutablePageTwo(this);
		addPage(pageTwo);
	}

	public IProject createCProjectForExecutable(String projectName) throws OperationCanceledException, CoreException {

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject newProjectHandle = workspace.getRoot().getProject(projectName);

		IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
		description.setLocation(null);

		IProject newProject = CCorePlugin.getDefault().createCProject(description, newProjectHandle, null,
				DEBUG_PROJECT_ID);

		return newProject;
	}

	public void createLaunchConfiguration(ICProject targetProject) throws CoreException {
		
		ILaunchConfigurationWorkingCopy wc = this.getSelectedLaunchConfigurationType().newInstance(null,
				this.getImportExecutablePage2().getNewConfigurationName());

		setConfigurationDefaults(wc, targetProject);

		final IStructuredSelection selection = new StructuredSelection(wc.doSave());
		final String identifier = "org.eclipse.debug.ui.launchGroup.debug"; //$NON-NLS-1$

		UIJob openLaunchConfigJob = new UIJob(Messages.AbstractImportExecutableWizard_CreateLaunchConfiguration) {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				DebugUITools.openLaunchConfigurationDialogOnGroup(CUIPlugin.getActiveWorkbenchShell(), selection, identifier);
				return Status.OK_STATUS;
			}};
		openLaunchConfigJob.schedule();

	}

	public abstract String getExecutableListLabel();

	public ImportExecutablePageOne getImportExecutablePage() {
		return pageOne;
	}

	public ImportExecutablePageTwo getImportExecutablePage2() {
		return pageTwo;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page == pageOne) {
			pageTwo.checkExecutableSettings();
		}
		return super.getNextPage(page);
	}

	public abstract String getPageOneDescription();

	public abstract String getPageOneTitle();

	public ILaunchConfigurationType getSelectedLaunchConfigurationType() {
		return pageTwo.getSelectedLaunchConfigurationType();
	}

	public String getDefaultWindowTitle() {
		return Messages.AbstractImportExecutableWizard_windowTitle;
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(getDefaultWindowTitle());
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public boolean performFinish() {

		ICProject targetProject = null;
		try {
			if (pageTwo.isCreateNewProjectSelected()) {
				IProject newProject = createCProjectForExecutable(pageTwo
						.getNewProjectName());
				setupProject(newProject);
				targetProject = CCorePlugin.getDefault().getCoreModel().create(
						newProject);
			} else {
				targetProject = pageTwo.getExistingCProject();
			}
			addBinaryParsers(targetProject.getProject());
			addExecutables(targetProject);
			if (pageTwo.isCreateLaunchConfigurationSelected()) {
				createLaunchConfiguration(targetProject);
			}
		} catch (OperationCanceledException e) {
		} catch (CoreException e) {
		}
		return true;
	}
	
	/**
	 * Subclasses should override this method to modify the launch configuration
	 * created by the wizard. The default implementation sets up the project
	 * and program names.
	 * @param config the launch configuration created by the wizard
	 * @param targetProject 
	 */
	public void setConfigurationDefaults(ILaunchConfigurationWorkingCopy config, ICProject project) {

		config.setMappedResources(new IResource[] {project.getProject()});
		config.setAttribute(ICDTLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getProject().getName());
		config.setAttribute(ICDTLaunchConfigurationConstants.ATTR_PROGRAM_NAME, new File(getImportExecutablePage()
				.getSelectedExecutables()[0]).getName());

	}

	public abstract void setupFileDialog(FileDialog dialog);

	public void setupProject(IProject newProject) throws CoreException {
	}

	/**
	 * The wizard will only display launch configuration types that you support.
	 * This method will be called for each available type.
	 * 
	 * @param type -
	 *            the type of launch configuration
	 * @return - if the wizard supports this launch configuration type
	 */
	public abstract boolean supportsConfigurationType(
			ILaunchConfigurationType type);

	/**
	 * Return true if you want the wizard to ask the user to select
	 * the binary parser. Otherwise it will only use the default one.
	 * A subclass can specify the default parser by overriding 
	 * getDefaultBinaryParserID.
	 * @return - If the binary parser selection combo should be displayed.
	 */
	public boolean userSelectsBinaryParser() {
		return true;
	}

	/** Get the default binary parser the wizard will use to determine if
	 * single file selections are valid and to filter the list for multi
	 * file selection.
	 * @return
	 */
	public String[] getDefaultBinaryParserIDs() {
		String defaultBinaryParserId = CCorePlugin.getDefault().getPluginPreferences().getDefaultString(CCorePlugin.PREF_BINARY_PARSER);
		if (defaultBinaryParserId == null || defaultBinaryParserId.length() == 0) {
			if (Platform.getOS().equals(Platform.OS_WIN32)) {
				if (WindowsGCC.isCygwin32() || WindowsGCC.isCygwin64()) {
					defaultBinaryParserId = "org.eclipse.cdt.core.Cygwin_PE"; //$NON-NLS-1$
				} else {
					//if (WindowsGCC.isMinGW32() || WindowsGCC.isMinGW64()) 
					defaultBinaryParserId = "org.eclipse.cdt.core.PE"; //$NON-NLS-1$
				}
			} else if (Platform.getOS().equals(Platform.OS_LINUX)) {
				defaultBinaryParserId = "org.eclipse.cdt.core.GNU_ELF"; //$NON-NLS-1$
			} else {
				defaultBinaryParserId = CCorePlugin.DEFAULT_BINARY_PARSER_UNIQ_ID;
			}
		}
		return new String[] { defaultBinaryParserId };
	}

	public String getDefaultProjectName() {
		String defaultName = ""; //$NON-NLS-1$
		String[] executables = getImportExecutablePage()
				.getSelectedExecutables();
		boolean isSelectSingleFile = getImportExecutablePage().isSelectSingleFile();
		if (isSelectSingleFile) {
			if (executables.length > 0) {
				String fileName = new File(executables[0]).getName();
				defaultName = Messages.ImportExecutablePageTwo_DefaultProjectPrefix + fileName;
			}
		} else {
			String searchedDirectory = getImportExecutablePage().getPreviouslySearchedDirectory();
			String searchedDirectoryName = Path.fromOSString(searchedDirectory).lastSegment();
			defaultName = "Debug_executables_in_" + searchedDirectoryName; //$NON-NLS-1$
		}
		return defaultName;
	}

}
