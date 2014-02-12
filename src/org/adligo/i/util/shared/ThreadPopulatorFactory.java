package org.adligo.i.util.shared;

public class ThreadPopulatorFactory {
	protected static I_ThreadPopulator pop;
	
	protected synchronized static void init(I_ThreadPopulator tp) throws Exception {
		if (tp == null) {
			throw new Exception("ThreadPopulatorFactory needs a non null I_ThreadPopulator argument.");
		}
		if (pop == null) {
			pop = tp;
		} else {
			throw new Exception("ThreadPopulatorFactory was already initialized.");
		}
	}
	
	public synchronized static I_ThreadPopulator getThreadPopulator() {
		return pop;
	}
	
	public static boolean isInit() {
		if (pop == null) {
			return false;
		}
		return true;
	}
}
