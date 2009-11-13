package org.adligo.i.util.client;


/**
 * actually a I_Hashtable factory
 * use your own caching just a wrapper
 * @author scott
 *
 */
public class CollectionFactory  {
	protected static I_Factory me;
	
	protected static synchronized void init(I_Factory in) throws Exception {
		if (me == null) {
			me = in;
		} else {
			throw new Exception("" + ClassUtils.getClassName(CollectionFactory.class) +
					" has already been initalized.");
		}
		if (me == null) {
			throw new Exception("" + ClassUtils.getClassName(CollectionFactory.class) +
				" the me variable is null?");
		}
	}
	
	/**
	 * 
	 * @param The class you want to wrap 
	 * (a Vector for J2ME or a Collection (List) for 
	 * j2se and GWT)
	 * 
	 * backed by a Vector on J2ME
	 * backed by a ArrayList on J2SE and GWT
	 * 
	 * @return
	 */
	public static I_Collection get(Object p) {
		return (I_Collection) me.createNew(p);
	}	
	
	/**
	 * create a default map implementation
	 * backed by a Vector on J2ME
	 * backed by a ArrayList on J2SE and GWT
	 * @return
	 */
	public static I_Collection create() {
		return (I_Collection) me.createNew(null);
	}
	
	public static boolean isInit() {
		if (me == null) {
			return false;
		}
		return true;
	}
}
