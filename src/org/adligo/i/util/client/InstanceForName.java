package org.adligo.i.util.client;

/**
 * a wrapper for Class.forName which is only available on jse
 * (not GWT or J2ME)
 * 
 * 
 * @author scott
 *
 */
public class InstanceForName {
	protected static I_Factory me;
	
	protected static synchronized void init(I_Factory in) throws Exception {
		if (me == null) {
			me = in;
		} else {
			throw new Exception("" + ClassUtils.getClassName(InstanceForName.class) +
					" has already been initalized.");
		}
		if (me == null) {
			throw new Exception("" + ClassUtils.getClassName(InstanceForName.class) +
				" the me variable is null?");
		}
	}
	
	public static Object create(String clazzName) {
		return me.createNew(clazzName);
	}
	
	
	public static boolean isInit() {
		if (me == null) {
			return false;
		}
		return true;
	}
}
