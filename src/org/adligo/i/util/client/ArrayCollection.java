package org.adligo.i.util.client;

import java.util.Arrays;

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
	private static int hashCode(Object[] array) {
		int prime = 31;
		if (array == null)
			return 0;
		int result = 1;
		for (int index = 0; index < array.length; index++) {
			result = prime * result
					+ (array[index] == null ? 0 : array[index].hashCode());
		}
		return result;
	}
	private static final int DIM_2 = 100;
	
	private int secondArraySize = 100;
	/**
	 * a 2 dimension array
	 * the second dimension is always 100 elements
	 */
	private Object[][] elementData = new Object[1][secondArraySize];
	private int size = 0;
	
	public ArrayCollection() {
		
	}
	/**
	 * sets the size of the second internal array
	 * defaluts to 100 in the regular ArrayCollection
	 * @param chunkSize
	 */
	public ArrayCollection(int chunkSize) {
		if (chunkSize < 2) {
			throw new RuntimeException("ChunkSize must be at least 2");
		}
		secondArraySize = chunkSize;
	}
  
	public synchronized boolean add(Object o) {
		if (o == null) {
			return false;
		}
		return addInternal(o);
	}

	private boolean addInternal(Object o) {
		Object [] secondDim = elementData[elementData.length -1 ];
		
		
		if (secondDim[secondArraySize -1 ] != null) {
			Object [][] newElementData = new Object[elementData.length + 1][secondArraySize];
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
		elementData = new Object[1][secondArraySize];
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

	/**
	 * if the Object passed in .equals any 
	 * of the objects in the Collection
	 * return the Object from the Collection
	 * 
	 * This is used for Named Singletons
	 * or a set of singletons that have been given names 
	 * where there should be only one Singleton per Name
	 * 
	 * More specifically it is used in ADI for CACHE_READER WRITER
	 * so that If 3 clients (java classes) call for the 
	 * CACHE_READER they don't overwrite each others proxyies
	 * 
	 * This is probably also a bug in i_log, however since each log
	 * should be specific to a particular class there is a one to one
	 * relation already
	 * 
	 * @param p
	 * @return
	 */
	public Object get(Object p) {
		//System.out.println("Entering ArrayCollection.get(" + p + ")");
		if (p!= null) {
			I_Iterator it = this.getIterator();
			while (it.hasNext()) {
				Object item = it.next();
				//System.out.println("Checking vs " + item);
				if (p.equals(item)) {
					//System.out.println("Returning " + item);
					return item;
				}
			}
		}
		return null;
	}
	/**
	 * just another way to iterate internally
	 * mostly added to port the params project
	 * to GWT
	 * 
	 * @param p
	 * @return
	 */
	public Object get(int p) {
		if (p < 0) {
			return null;
		}
		if (p > secondArraySize -1) {
			int x = p/secondArraySize;
			int y = p-x*secondArraySize;
			return elementData[x][y];
		}
		return elementData[0][p];
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ArrayCollection.hashCode(elementData);
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayCollection other = (ArrayCollection) obj;
		if (!Arrays.equals(elementData, other.elementData))
			return false;
		return true;
	}
}
