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
public class Event  {
	/**
	 * this represents the GUI (view) component that
	 * created the event
	 */
	private Object source = null;
	private Throwable exception = null;
	private Object  value = null;
	
	public Object clone() {
		Event e = new Event();
		e.source = source;
		e.exception = exception;
		e. value = value;
		return e;
	}
	
	public Event copy() {
		return (Event) this.clone();
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

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}
	
}
