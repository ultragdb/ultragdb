/*******************************************************************************
 * Copyright (c) 2004, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM - Initial API and implementation
 *     Anton Leherbauer (Wind River Systems)
 *     Hans-Erik Floryd (hef-cdt@rt-labs.com)  - http://bugs.eclipse.org/245692
 *******************************************************************************/
package org.eclipse.cdt.make.internal.core.scannerconfig.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.verykit.common.Cygwin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Platform;

/**
 * translate cygpaths to absolute paths.
 * Note that this class does not support build configurations.
 * 
 * @author vhirsl
 */
public class CygpathTranslator {

	public CygpathTranslator(IProject project) {
	}

	public static List<String> translateIncludePaths(IProject project, List<String> sumIncludes) {
		// first check if cygpath translation is needed at all
		boolean translationNeeded = false;
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			for (Iterator<String> i = sumIncludes.iterator(); i.hasNext(); ) {
				String include = i.next();
				if (include.startsWith("/")) { //$NON-NLS-1$
					translationNeeded = true;
					break;
				}
			}
		}
		if (!translationNeeded) {
			return sumIncludes;
		}
		
		List<String> translatedIncludePaths = new ArrayList<String>();
		for (Iterator<String> i = sumIncludes.iterator(); i.hasNext(); ) {
			String includePath = i.next();
			String translatedPath = Cygwin.cygwinToWindowsPath(includePath);
			translatedIncludePaths.add(translatedPath);
		}
		
		return translatedIncludePaths;
	}

}
