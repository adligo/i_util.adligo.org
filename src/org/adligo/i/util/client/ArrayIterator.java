package org.adligo.i.util.client;

public class ArrayIterator implements I_Iterator {
	Object [] items = new Object[0];
	int location = 0;
	
	public ArrayIterator(Object [] p) {
		if (p != null) {
			items = p;
		}
	}
	public boolean hasNext() {
		if (location < items.length) {
			return true;
		}
		return false;
	}

	public Object next() {
		Object toRet = items[location];
		location++;
		return toRet;
	}

	public Object getWrapped() {
		// TODO Auto-generated method stub
		return this;
	}

}
