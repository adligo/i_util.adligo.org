package org.adligo.i.util.shared;


/**
 * format GWT, J2SE, J2ME in a common way for portability
 * in Model's toString methods
 * 
 * impl should be Thread Safe!
 * 
 * @author scott
 *
 */
public interface I_TextFormatter {
	public String formatDate(String format, long value);
	public String format(String format, long value);
	public String format(String format, int value);
	public String format(String format, short value);
	public String format(String format, float value);
	public String format(String format, double value);
}
