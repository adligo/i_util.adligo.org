package org.adligo.i.util.shared;

public interface I_ImmutableMap {
	I_Iterator getKeysIterator();
	I_Iterator getValuesIterator();
	int size();
	boolean containsKey(Object key); 
	boolean containsValue(Object value);
	boolean equals(Object o);
	Object get(Object key);
	boolean isEmpty(); 
}
