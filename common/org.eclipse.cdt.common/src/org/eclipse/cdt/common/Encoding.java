package org.eclipse.cdt.common;

import java.nio.charset.Charset;

public class Encoding {
	public static Charset UTF_8(){
		Charset charset = Charset.forName("UTF-8"); //$NON-NLS-1$
		return charset;
	}
}
