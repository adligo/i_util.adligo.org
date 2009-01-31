package org.adligo.i.util.client;

/**
 * this interface represents a way to bind data back to the view
 * (I did this in the view on my GWT demo (MVC impl)
 * 
 * Also I found this a nice way to implement the Event Handlers
 * in the GWT demo (MVC impl) initally I used I_Invoker from the 
 * ADI module but this always seems to return nothing
 * 
 * @author scott
 *
 */
public interface I_DataAdaptor {
	public void adapt(Object p);
}
