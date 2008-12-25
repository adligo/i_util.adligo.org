package org.adligo.i.util.client;

/**
 * a simple collection implementaion that will 
 * work on GWT, J2ME and J2SE
 * 
 * does not allow null items!
 * 
 * @author scott
 *
 */
public class ArrayCollection implements I_Collection {
	private static final int DIM_2 = 100;
	
	/**
	 * a 2 dimension array
	 * the second dimension is always 100 elements
	 */
	private Object[][] elementData = new Object[1][100];
	private int size = 0;
  
	public synchronized boolean add(Object o) {
		if (o == null) {
			return false;
		}
		return addInternal(o);
	}

	private boolean addInternal(Object o) {
		Object [] secondDim = elementData[elementData.length -1 ];
		if (secondDim[99] != null) {
			Object [][] newElementData = new Object[elementData.length + 1][100];
			for (int i = 0; i < elementData.length; i++) {
				newElementData[i] = elementData[i]; 
			}
			secondDim = newElementData[newElementData.length -1];
			elementData = newElementData;
		} 
		for (int i = 0; i < secondDim.length; i++) {
			if (secondDim[i] == null) {
				secondDim[i] = o;
				break;
			}
		}
		size++;
		return true;
	}

	public synchronized void clear() {
		clearInternal();
	}

	private void clearInternal() {
		elementData = new Object[1][100];
		size = 0;
	}
	
	public I_Iterator getIterator() {
		// TODO Auto-generated method stub
		return new ArrayIterator(toArray());
	}

	public synchronized boolean remove(Object o) {
		if (o == null) {
			return false;
		}
		Object [] old = this.toArray();
		clearInternal();
		for (int i = 0; i < old.length; i++) {
			Object item = old[i];
			/*
			System.out.println("Object " + o + " equals " + item + " ? " + o.equals(item) + 
					" current size is " + size);
					*/
			if (o.equals(item)) {
				//don't add it 
			} else {
				this.addInternal(item);
			}
		}
		return true;
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public Object getWrapped() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public Object [] toArray() {
		Object [] toRet = new Object[size];
		
		int counter = 0;
		for (int i = 0; i < elementData.length; i++) {
			for (int j = 0; j < DIM_2; j++) {
				Object item = elementData[i][j];
				if (item == null) {
					i =  elementData.length;
					break;
				}
				//System.out.println("toArray item is " + item);
				toRet[counter] = item;
				counter++;
			}
		}
		return toRet;
	}

}