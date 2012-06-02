package org.adligo.i.util.client;


public interface I_DateTimeParser {

	/**
	 * should work with the DateTime format consants
	 * @param format
	 * @param value
	 * @return
	 */
	public long parse(String format, String value) throws IllegalArgumentException;
}
