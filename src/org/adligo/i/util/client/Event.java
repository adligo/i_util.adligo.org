package org.adligo.i.util.client;

/**
 * 
 * Note Hash Code and Equals not implemented 
 * on purpose in this class
 * 
 * it is used in the GWT SystemEventController
 * class and the HashMap would fail the I_Invoker
 * lookups if HashCode and Equals were implemened
 * 
 * @author scott
 *
 */
public class Event implements I_Disposable {
	
	private Throwable exception = null;
	private Object  value = null;
	
	public void dispose() {
		exception = null;
		value = null;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean threwException() {
		if (exception == null) {
			return false;
		}
		return true;
	}
	
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	
}
