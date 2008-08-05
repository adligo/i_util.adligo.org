package org.adligo.i.util.client;

public class PropertyFactory extends AbstractFactory  {
	
	/**
	 * 
	 * 
	 * @param The name of the property file you 
	 * want to get
	 * 
	 * GWT relative to the html file that was loaded
	 * 
	 * j2me and j2se relative to classpath
	 * 
	 * @return
	 */
	public static I_Map get(Object p) {
		return (I_Map) me.createNew(p);
	}	
	
}
