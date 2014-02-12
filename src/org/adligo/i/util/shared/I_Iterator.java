package org.adligo.i.util.shared;

/**
 * so i used enumerations in 
 * J2me and now need to do this to rectify with GWT
 * 
 * should always remain up to date with the standard one
 * 
 * @author scott
 *
 */
public interface I_Iterator extends I_Wrapper {

	public boolean hasNext();
	public Object next();
}
