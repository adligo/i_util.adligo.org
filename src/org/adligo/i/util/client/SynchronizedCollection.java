package org.adligo.i.util.client;

public class SynchronizedCollection implements I_Collection {
	I_Collection other;
	
	public SynchronizedCollection(I_Collection p) {
		other = p;
	}

	public synchronized boolean add(Object p) {
		return other.add(p);
	}

	public synchronized int hashCode() {
		return other.hashCode();
	}

	public synchronized boolean equals(Object obj) {
		return other.equals(obj);
	}

	public synchronized void clear() {
		other.clear();
	}

	public synchronized String toString() {
		return other.toString();
	}

	public synchronized I_Iterator getIterator() {
		return other.getIterator();
	}

	public synchronized boolean remove(Object obj) {
		return other.remove(obj);
	}

	public synchronized int size() {
		return other.size();
	}

	public Object getWrapped() {
		throw new RuntimeException("Method intentionally not implemented for encapsulation.");
	}

	public synchronized boolean contains(Object p) {
		// TODO Auto-generated method stub
		return other.contains(p);
	}
	
}
