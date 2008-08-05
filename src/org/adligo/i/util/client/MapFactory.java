package org.adligo.i.util.client;

import java.util.Map;
import java.util.HashMap;

/**
 * actually a I_Hashtable factory
 * use your own caching just a wrapper
 * @author scott
 *
 */
public class MapFactory extends AbstractFactory {

	/**
	 * see impl package to determine what name means!
	 * ie gwt_util, j2me_util
	 * 
	 * @param The class you want to wrap (a Hashtable for J2ME or a Map for 
	 * j2se and GWT)
	 * 
	 * @return
	 */
	public static I_Map get(Object p) {
		return (I_Map) me.createNew(p);
	}	
	
}
