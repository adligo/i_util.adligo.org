package org.adligo.i.util.client;

import java.util.Map;
import java.util.HashMap;

/**
 * actually a I_Hashtable factory
 * use your own caching just a wrapper
 * @author scott
 *
 */
public class CollectionFactory  {
	protected static I_Factory me;
	
	protected static void init(I_Factory in) throws Exception {
		me = in;
	}
	
	/**
	 * 
	 * @param The class you want to wrap (a Hashtable for J2ME or a Map for 
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
}
