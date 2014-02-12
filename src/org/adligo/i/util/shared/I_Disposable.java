package org.adligo.i.util.shared;

public interface I_Disposable {
	/**
	 * remove unused references
	 * so that garbage collection will take place
	 * 
	 */
	public void dispose();
}
