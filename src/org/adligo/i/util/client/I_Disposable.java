package org.adligo.i.util.client;

public interface I_Disposable {
	/**
	 * remove unused references
	 * so that garbage collection will take place
	 * 
	 */
	public void dispose();
}
