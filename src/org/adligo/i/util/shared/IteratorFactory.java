package org.adligo.i.util.shared;



public class IteratorFactory  {
	protected static I_Factory me;
	
	protected synchronized static void init(I_Factory in) throws Exception {
		if (in == null) {
			throw new Exception("IteratorFactory needs a non null factory argument.");
		}
		if (me == null) {
			me = in;
		} else {
			throw new Exception("IteratorFactory was already initialized.");
		}
		
	}
	/**
	 * this should be some sort of Collection (Hashtable, Vector) exc
	 * @param p
	 * @return
	 */
	public static I_Iterator create(Object p) {
		return (I_Iterator) me.createNew(p);
	}
	
	public static boolean isInit() {
		if (me == null) {
			return false;
		}
		return true;
	}
}
