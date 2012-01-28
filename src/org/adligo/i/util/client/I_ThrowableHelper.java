package org.adligo.i.util.client;

public interface I_ThrowableHelper {
	public void fillInStackTrace(Throwable p);
	public String getStackTraceAsString(Throwable p);
	/**
	 * @param preText allows for tabs (\t) do indent text
	 * @param p
	 * @param lineFeed allows for swapping out of unix or dos line feeds
	 * @return
	 */
	public String getStackTraceAsString(String preText, Throwable p, String lineFeed, I_StringAppender appender);
}
