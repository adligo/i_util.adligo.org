package org.adligo.i.util.client;

public interface I_Wrapper {
	/**
	 * since most of the interfaces in this package
	 * wrap stuff that part of java so that 
	 * GWT and J2ME code can co-exist
	 * this will give you the underlying 
	 * platform implementation
	 * 
	 * ie from I_Collection it would return a Collection on
	 * GWT and J2SE, and a Vector on J2ME
	 * 
	 * @return
	 */
	public Object getWrapped();
}
