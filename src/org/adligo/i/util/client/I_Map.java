package org.adligo.i.util.client;

/**
 * this is a dumb wrapper to cross compile gwt and j2me code
 * to replace java.util.Hashtable
 * GWT will back by a Map
 * J2me will back by a Hashtable
 * 
 * @author scott
 *
 */
public interface I_Map extends I_Wrapper {
	I_Iterator getIterator();
	I_Iterator keys();
	
	void clear();
	boolean containsKey(Object key); 
	boolean containsValue(Object value);
	boolean equals(Object o);
	Object get(Object key);
	boolean isEmpty(); 
	
    Object put(Object key, Object value);
    Object remove(Object key);
    int size();
}
