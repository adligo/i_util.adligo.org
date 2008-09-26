package org.adligo.i.util.client;

import java.util.Map;
import java.util.HashMap;

/**
 * actually a I_Hashtable factory
 * use your own caching just a wrapper
 * @author scott
 *
 */
public class MapFactory  {
	protected static I_Factory me;
	
	protected static void init(I_Factory in) throws Exception {
		me = in;
	}
	
	/**
	 * 
	 * @param The class you want to wrap (a Hashtable for J2ME or a Map for 
	 * j2se and GWT)
	 * 
	 * @return
	 */
	public static I_Map get(Object p) {
		return (I_Map) me.createNew(p);
	}
	
	
	public static I_ImutableMap create(I_Map p) {
		return (I_ImutableMap) me.createNew(p);
	}
	
	/**
	 * create a default map implementation
	 * @return
	 */
	public static I_Map create() {
		return (I_Map) me.createNew(null);
	}
}
