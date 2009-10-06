package org.adligo.i.util.client;

public class ThreadPopulatorFactory {
	private static I_ThreadPopulator pop;
	
	protected synchronized static void init(I_ThreadPopulator tp) {
		pop = tp;
	}
	
	public synchronized static I_ThreadPopulator getThreadPopulator() {
		return pop;
	}
}
