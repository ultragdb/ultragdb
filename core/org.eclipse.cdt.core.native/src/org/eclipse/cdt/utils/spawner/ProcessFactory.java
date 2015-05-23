/*******************************************************************************
 * Copyright (c) 2000, 2014 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     QNX Software Systems - Initial API and implementation
 *     Martin Oberhuber (Wind River) - [303083] Split out the Spawner
 *******************************************************************************/
package org.eclipse.cdt.utils.spawner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.cdt.internal.core.natives.Messages;
import org.eclipse.cdt.utils.pty.PTY;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */

/**
 * 
 * use ProcessBuilder to launch process
 * 
 * @see "http://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html"
 *
 */

public class ProcessFactory {
	static private ProcessFactory instance;

	private ProcessFactory() {
	}

	public static ProcessFactory getFactory() {
		if (instance == null)
			instance = new ProcessFactory();
		return instance;
	}

	public Process exec(String cmd) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, null, null);
		return p;
	}

	public Process exec(String[] cmdarray) throws IOException {
		Process p = exec(cmdarray, null, null);
		return p;
	}

	public Process exec(String[] cmdarray, String[] envp) throws IOException {
		Process p = exec(cmdarray, envp, null);
		return p;
	}

	public Process exec(String cmd, String[] envp) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, envp, null);
		return p;
	}

	public Process exec(String cmd, String[] envp, File dir) throws IOException {
		String[] cmdarray = new String[] { cmd };
		Process p = exec(cmdarray, envp, dir);
		return p;
	}

	public Process exec(String cmdarray[], String[] envp, File dir) throws IOException {
		return com.verykit.common.ProcessFactory.exec(cmdarray, envp, dir);
	}

	@Deprecated
	public Process exec(String cmdarray[], String[] envp, File dir, PTY pty) throws IOException {
		throw new UnsupportedOperationException(Messages.Util_exception_cannotCreatePty);
	}
}
