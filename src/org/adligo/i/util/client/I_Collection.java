package org.adligo.i.util.client;

/**
 * yes similar to java.util.Collection
 * for bootstrap code GWT and J2ME
 * 
 * @author scott
 *
 */
public interface I_Collection {
	public boolean add(Object o);
	public boolean remove(Object o);
	public void clear();
	public I_Iterator getIterator();
}
