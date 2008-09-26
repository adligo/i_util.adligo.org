package org.adligo.i.util.client;

public interface I_ImmutableMap {
	I_Iterator getIterator();
	I_Iterator keys();
	int size();
	boolean containsKey(Object key); 
	boolean containsValue(Object value);
	boolean equals(Object o);
	Object get(Object key);
	boolean isEmpty(); 
}
