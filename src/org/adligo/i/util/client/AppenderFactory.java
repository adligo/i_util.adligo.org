package org.adligo.i.util.client;


/**
 * actually a Map or Hashtable factory
 * depending on which platform your using
 * 
 * @author scott
 *
 */
public class AppenderFactory  {
	protected static I_Factory me;
	
	protected synchronized static void init(I_Factory in) throws Exception {
		if (in == null) {
			throw new  Exception("" + ClassUtils.getClassName(AppenderFactory.class) +
			" can't accept a null in parameter.");
		}

		if (me == null) {
			me = in;
		} else  {
			throw new Exception("" + ClassUtils.getClassName(AppenderFactory.class) +
					" has already been initalized.");
		}
		
		
	}
	

	
	/**
	 * create a new string appender 
	 * for use on a single thread
	 * @return
	 */
	public static I_Appender create() {
		if (me == null) {
			printImNull();
		}
		return (I_Appender) me.createNew(null);
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
