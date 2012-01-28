package org.adligo.i.util.client;

public interface I_ThrowableHelper {
	public void fillInStackTrace(Throwable p);
	/**
	 * @deprecated
	 * use the getStackTraceAsString and pass in your own appender
	 *    (jse and gwt have a StringBuilderAppender impl)
	 *    (i_log has a StringBufferAppender for jme backward compatibility)
	 * @param p
	 * @return
	 */
	public String getStackTraceAsString(Throwable p);
	/**
	 * @param preText allows for tabs (\t) do indent text
	 * @param p
	 * @param lineFeed allows for swapping out of unix or dos line feeds
	 * @return
	 */
	public String getStackTraceAsString(String preText, Throwable p, String lineFeed, I_Appender appender);
}
