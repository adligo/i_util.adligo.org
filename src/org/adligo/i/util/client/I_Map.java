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
public interface I_Map extends I_ImmutableMap, I_Wrapper {
	void clear();
    Object put(Object key, Object value);
    Object remove(Object key);
    
}
