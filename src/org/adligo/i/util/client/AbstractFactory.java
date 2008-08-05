package org.adligo.i.util.client;

public abstract class AbstractFactory {
	protected static I_Factory me;
	
	protected static void init(I_Factory in) throws Exception {
		me = in;
	}
}
