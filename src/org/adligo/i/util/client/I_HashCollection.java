package org.adligo.i.util.client;

public interface I_HashCollection {
	public int size();
	public boolean add(Object o);
	public boolean put(Object o);
	public boolean remove(Object o);
	public void clear();
	public I_Iterator getIterator();
	public boolean contains(Object key);
	public Object get(int i);
	public Object get(Object obj);
}
