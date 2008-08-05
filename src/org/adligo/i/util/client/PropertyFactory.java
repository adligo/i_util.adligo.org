package org.adligo.i.util.client;

public class PropertyFactory  {
	protected static I_Factory me;
	
	protected static void init(I_Factory in) throws Exception {
		me = in;
	}
	/**
	 * 
	 * 
	 * @param The name of the property file you 
	 * want to get
	 * 
	 * GWT relative to the html file that was loaded
	 * 
	 * @param listener a async callback that is available on both j2me and GWT
	 * 
	 * j2me and j2se relative to classpath
	 * 
	 * @return
	 */
	public static void get(Object p, I_Listener listener) {
		ListenerValueObject params = new ListenerValueObject();
		params.setValue(p);
		params.setListener(listener);
		me.createNew(params);
	}	
	
}
