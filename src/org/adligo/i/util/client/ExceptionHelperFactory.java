package org.adligo.i.util.client;

/**
 * this class provides method not available in jme 
 * for java.lang.Exception like fill in stacktrace and getStackTrace
 * 
 * @author scott
 *
 */
public class ExceptionHelperFactory {
	protected static I_ExceptionHelper helper;
	
	public void fillInStackTrace(Throwable p) {
		helper.fillInStackTrace(p);
	}

	public String getStackTraceAsString(Throwable p) {
		return helper.getStackTraceAsString(p);
	}

	protected synchronized static void init(I_ExceptionHelper p) throws Exception {
		if (p == null) {
			throw new  Exception("" + ClassUtils.getClassName(ExceptionHelperFactory.class) +
			" can't accept a null in parameter.");
		}
		helper = p;
	}
	
}
