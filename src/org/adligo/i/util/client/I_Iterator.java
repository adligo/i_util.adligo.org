package org.adligo.i.util.client;

/**
 * so i used enumerations in 
 * J2me and now need to do this to rectify with GWT
 * 
 * should always remain up to date with the standard one
 * 
 * @author scott
 *
 */
public interface I_Iterator {

	public boolean hasNext();
	public Object next();
	public void remove();
}
