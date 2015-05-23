/*******************************************************************************
 * Copyright (c) 2000, 2010 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *     Markus Schorn (Wind River Systems)
 *******************************************************************************/
package org.eclipse.cdt.utils;

import java.io.IOException;

import com.verykit.common.Cygwin;
import org.eclipse.core.runtime.Platform;

/**
 * @noextend This class is not intended to be subclassed by clients.
 */
public class CygPath {

	public CygPath(String command) throws IOException {
		if (!Platform.getOS().equals(Platform.OS_WIN32)) {
			throw new IOException("Not Windows"); //$NON-NLS-1$
		}
	}

	public CygPath() throws IOException {
		this("cygpath"); //$NON-NLS-1$
	}

	/**
	 * Use this method for series of translations of paths.
	 */
	public String getFileName(String name) throws IOException {
		String fileName = Cygwin.cygwinToWindowsPath(name);
		return fileName;
	}

	public void dispose() {
	}

}
