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

	public static String getClassShortName(Class c) {
		String asStr = c.toString();
		int index = asStr.indexOf(".");
		if ( index != -1 && index < asStr.length() - 1) {
			String toRet = asStr.substring(asStr.lastIndexOf(".") + 1, asStr.length());
			return toRet;
		}
		return asStr;
	}
	
	/**
	 * simmilar to javas regular instanceOf
	 * but doesn't go up the chain of Interfaces and BaseClasses
	 * only looks at the Leaf most Class
	 * @return
	 */
	public static boolean typeOf(Object p, Class c) {
		String className = ClassUtils.getClassName(c);
		String otherClassName = ClassUtils.getClassName(p.getClass());
		if (className.equals(otherClassName)) {
			return true;
		}
		return false;
	}
}
