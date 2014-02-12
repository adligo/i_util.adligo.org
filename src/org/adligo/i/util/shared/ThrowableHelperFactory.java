package org.adligo.i.util.shared;

/**
 * this class provides method not available in jme 
 * for java.lang.Exception like fill in stacktrace and getStackTrace
 * 
 * @author scott
 *
 */
public class ThrowableHelperFactory {
	protected static I_ThrowableHelper helper;
	
	public static void fillInStackTrace(Throwable p) {
		helper.fillInStackTrace(p);
	}

	/**
	 * 
	 * @param preText allows for tabs (\t) before each line for indenting
	 * @param p
	 * @param lineFeed the characters to add at the end of each line 
	 * 				(allows for dos or unix line feeds)
	 * @return
	 */
	public static void appendStackTracesString(String preText, Throwable p, String lineFeed, I_Appender appender) {
		helper.appendStackTracesString(preText, p, lineFeed, appender);
	}
	
	protected synchronized static void init(I_ThrowableHelper p) throws Exception {
		if (p == null) {
			throw new  Exception("" + ClassUtils.getClassName(ThrowableHelperFactory.class) +
			" can't accept a null in parameter.");
		}
		helper = p;
	}
	
	public static boolean isInit() {
		if (helper == null) {
			return false;
		}
		return true;
	}
}
