package org.adligo.i.util.client;

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
}
