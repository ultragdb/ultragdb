/*******************************************************************************
 *  Copyright (c) 2002, 2009 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *  IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.debug.mi.core.command.factories.win32;

import com.verykit.common.Cygwin;
import org.eclipse.cdt.debug.mi.core.command.MIEnvironmentDirectory;

/**
 * Cygwin implementation of the MIEnvironmentDirectory command. In the Cygwin
 * environment, the paths are Windows paths and need to be converted to Cygwin
 * style paths before passing them to gdb.
 */
public class CygwinMIEnvironmentDirectory extends MIEnvironmentDirectory {

	CygwinMIEnvironmentDirectory(String miVersion, boolean reset, String[] paths) {
		super(miVersion, reset, paths);

		String[] newPaths = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			String newPath = Cygwin.windowsToCygwinPath(path);
			newPaths[i] = newPath;
		}

		setParameters(newPaths);
	}

}
