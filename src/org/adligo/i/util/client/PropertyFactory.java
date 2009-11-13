package org.adligo.i.util.client;

public class PropertyFactory  {
	protected static I_Factory me;
	
	protected static synchronized void init(I_Factory in) throws Exception {
		if (in == null) {
			throw new Exception("PropertyFactory needs a non null factory argument.");
		}
		if (me == null) {
			me = in;
		} else {
			throw new Exception("PropertyFactory was already initialized.");
		}
	}
	/**
	 * 
	 * 
	 * @param The name of the property file you 
	 * want to get
	 * 
	 * GWT relative to the html file that was loaded
	 * 
	 * @param listener a callback that is available on j2se, j2me and GWT
	 * 		will recieve either a I_Map with the properties
	 * 	    or a PropertyFileReadException
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
	
	public static boolean isInit() {
		if (me == null) {
			return false;
		}
		return true;
	}
}
