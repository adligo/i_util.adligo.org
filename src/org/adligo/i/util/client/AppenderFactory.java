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
	protected static I_Factory imute_me;
	protected static I_Factory sync_me;
	
	protected synchronized static void init(I_Factory in, I_Factory imutable_in, I_Factory sync_in) throws Exception {
		if (in == null) {
			throw new  Exception("" + ClassUtils.getClassName(AppenderFactory.class) +
			" can't accept a null in parameter.");
		}
		if (imutable_in == null) {
			throw new  Exception("" + ClassUtils.getClassName(AppenderFactory.class) +
			" can't accept a null imutable_in parameter.");
		}
		if (sync_in == null) {
			throw new  Exception("" + ClassUtils.getClassName(AppenderFactory.class) +
			" can't accept a null sync_in parameter.");
		}
		if (me == null) {
			me = in;
			imute_me = imutable_in;
			sync_me = sync_in;
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
	public static I_StringAppender create() {
		if (me == null) {
			printImNull();
		}
		return (I_StringAppender) me.createNew(null);
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
