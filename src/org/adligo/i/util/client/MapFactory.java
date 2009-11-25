package org.adligo.i.util.client;


/**
 * actually a Map or Hashtable factory
 * depending on which platform your using
 * 
 * @author scott
 *
 */
public class MapFactory  {
	protected static I_Factory me;
	protected static I_Factory imute_me;
	
	protected synchronized static void init(I_Factory in, I_Factory imutable_fact) throws Exception {
		if (in == null) {
			throw new  Exception("" + ClassUtils.getClassName(MapFactory.class) +
			" can't accept a null in parameter.");
		}
		if (imutable_fact == null) {
			throw new  Exception("" + ClassUtils.getClassName(MapFactory.class) +
			" can't accept a null imutable_fact parameter.");
		}
		
		if (me == null) {
			me = in;
			imute_me = imutable_fact;
		} else  {
			throw new Exception("" + ClassUtils.getClassName(MapFactory.class) +
					" has already been initalized.");
		}
		
		
	}
	
	/**
	 * 
	 * @param The class you want to wrap (a Hashtable for J2ME or a Map for 
	 * j2se and GWT)
	 * 
	 * @return
	 */
	public static I_Map get(Object p) {
		if (me == null) {
			printImNull();
		}
		return (I_Map) me.createNew(p);
	}
	
	
	public static I_ImmutableMap create(I_Map p) {
		if (imute_me == null) {
			printImNull();
		}
		return (I_ImmutableMap) imute_me.createNew(p);
	}
	
	/**
	 * create a default map implementation
	 * @return
	 */
	public static I_Map create() {
		if (me == null) {
			printImNull();
		}
		return (I_Map) me.createNew(null);
	}

	private static void printImNull() {
		try {
			throw new NullPointerException("Please initalize your Platform first see J2SEPlatform," +
					" GwtPlatform or J2MEPlatform");
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public static boolean isInit() {
		if (me == null) {
			return false;
		}
		return true;
	}
}
