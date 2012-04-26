package org.adligo.i.util.client;

public interface I_ThrowableHelper {
	public void fillInStackTrace(Throwable p);

	/**
	 * Get all the stack trace items and chained init causes statck traces back as a string.
	 * 
	 * @param preText allows for tabs (\t) do indent text
	 * @param p
	 * @param lineFeed allows for swapping out of unix or dos line feeds
	 * @return
	 */
	public void appendStackTracesString(String preText, Throwable p, String lineFeed, I_Appender appender);
}
