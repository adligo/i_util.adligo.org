package org.adligo.i.util.shared;

public interface I_AppenderFactory extends I_Factory {
	/**
	 * note gwt doesn't support System.getProperty
	 * so this is my solution to this problem
	 * @return
	 */
	public char getLineSeperator();
}
