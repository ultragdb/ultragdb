


When launch product in Eclipse on 32 bit Linux
core/org.eclipse.cdt.core.linux.x86_64/os/linux/          core/org.eclipse.cdt.core.linux.x86/os/linux/x86/



all files in this directory should also be put to
core/org.eclipse.cdt.core.linux.x86_64/os/linux/



On 32-bit Linux, when debugging the following code, it let path to be "core/org.eclipse.cdt.core.linux.x86_64/os/linux/", instead of "core/org.eclipse.cdt.core.linux.x86/os/linux/x86/"
but, when on normal run(when the product is exported), it return the right path.

			URL url = FileLocator.find(bundle, new Path("$os$/"), null);
			if (url != null) {
				url = FileLocator.resolve(url);
				String path = url.getFile();
			}


This may be a bug of JRE on 32-bit Linux.

see  org.eclipse.cdt.utils.pty.PTY2Util.getTerminalEmulatorCommand()
