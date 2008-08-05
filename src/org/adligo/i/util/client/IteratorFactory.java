package org.adligo.i.util.client;


public class IteratorFactory extends AbstractFactory {
	
	/**
	 * this should be some sort of Collection (Hashtable, Vector) exc
	 * @param p
	 * @return
	 */
	public static I_Iterator create(Object p) {
		return (I_Iterator) me.createNew(p);
	}
	
}
