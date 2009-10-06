package org.adligo.i.util.client;

/**
 * should be thread safe 
 * populate the message with the threads name
 * 
 * Use Case
 *    No threads on gwt :)
 *    
 * @author scott
 *
 */
public interface I_ThreadPopulator {
	
	public void populateThread(I_ThreadContainer threadHolder);
}
