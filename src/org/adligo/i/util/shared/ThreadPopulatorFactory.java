package org.adligo.i.util.shared;

public class ThreadPopulatorFactory {
	public static final String THREAD_POPULATOR_FACTORY_WAS_ALREADY_INITIALIZED = "ThreadPopulatorFactory was already initialized.";
	public static final String THREAD_POPULATOR_FACTORY_NEEDS_A_NON_NULL_I_THREAD_POPULATOR_ARGUMENT = "ThreadPopulatorFactory needs a non null I_ThreadPopulator argument.";
	protected static I_ThreadPopulator pop;
	
	protected synchronized static void init(I_ThreadPopulator tp) throws Exception {
		if (tp == null) {
			throw new Exception(THREAD_POPULATOR_FACTORY_NEEDS_A_NON_NULL_I_THREAD_POPULATOR_ARGUMENT);
		}
		if (pop == null) {
			pop = tp;
		} else {
			throw new Exception(THREAD_POPULATOR_FACTORY_WAS_ALREADY_INITIALIZED);
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
