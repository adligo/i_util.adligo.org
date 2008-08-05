package org.adligo.i.util.client;
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
	 * @param name
	 * @return
	 */
	public static I_Map get(String name) {
		return (I_Map) me.createNew(name);
	}	
	
}
