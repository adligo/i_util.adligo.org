package org.adligo.i.util.shared;

public class Platform {
	public static final short GWT = 1;
	public static final short JME = 2;
	public static final short JSE = 3;
	private static short running = JSE;
	
	public synchronized static short getPlatform() {
		return running;
	}
	
	protected synchronized static void setPlatformGWT() {
		running = GWT;
	}
	
	protected synchronized static void setPlatformJME() {
		running = JME;
	}
	
	/**
	 * for a nicer error checking in the LogPlatform class
	 * @return
	 */
	public static boolean isInit() {
		if (!CollectionFactory.isInit()) {
			return false;
		}
		if (!MapFactory.isInit()) {
			return false;
		}
		if (!IteratorFactory.isInit()) {
			return false;
		}
		if (!PropertyFactory.isInit()) {
			return false;
		}
		if (!ThreadPopulatorFactory.isInit()) {
			return false;
		}
		if (!TextFormatter.isInit()) {
			return false;
		}
		return true;
	}
}
