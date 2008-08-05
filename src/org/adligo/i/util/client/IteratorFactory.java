package org.adligo.i.util.client;


public class IteratorFactory  {
	private static I_Factory me;
	
	protected static void init(I_Factory in) throws Exception {
		me = in;
	}
	/**
	 * this should be some sort of Collection (Hashtable, Vector) exc
	 * @param p
	 * @return
	 */
	public static I_Iterator create(Object p) {
		return (I_Iterator) me.createNew(p);
	}
	
}
