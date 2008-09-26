package org.adligo.i.util.client;

public class ClassUtils {

	/**
	 * normally this would have been c.getName()
	 * but not in GWT
	 * 
	 * @param c
	 * @return
	 */
	public static String getClassName(Class c) {
		String asStr = c.toString();
		return asStr.substring(6, asStr.length());
	}

}
