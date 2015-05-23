package com.verykit.common;

import java.nio.charset.Charset;

public class Encoding {
	public static Charset UTF_8(){
//		CharSetTest.main(null);
		Charset charset = Charset.forName("UTF-8"); //$NON-NLS-1$
		return charset;
	}
}

//class CharSetTest {
//
//    public static void main(String[] args) {
//    	System.out.println("Default Charset=" + Charset.defaultCharset());
//    	System.out.println("file.encoding=" + System.getProperty("file.encoding"));
//    	System.out.println("Default Charset in Use=" + getDefaultCharSet());
//    }
//
//    private static String getDefaultCharSet() {
//    	OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
//    	String enc = writer.getEncoding();
//    	return enc;
//    }
//}
