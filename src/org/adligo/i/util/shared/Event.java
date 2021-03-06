package org.adligo.i.util.shared;

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
public class Event implements I_Event  {
	public static final String FINISHED_VALUE = "EventFinished";
	/**
	 * this represents the GUI (view) component that
	 * created the event
	 */
	private Object source = null;
	private Throwable exception = null;
	private Object  value = null;
	private I_Event original;
	
	public Event() {}
	public Event(Object p_source) {
		source = p_source;
	}
	
	public Event(Object p_source, Object p_value) {
		source = p_source;
		value = p_value;
	}	
	
	public Event(Object p_source, Object p_value, Throwable p_throw) {
		source = p_source;
		value = p_value;
		exception = p_throw;
	}		
	
	public Event(I_Event p) {
		original = p;
		source = original.getSource();
		value = original.getValue();
		exception = original.getException();
	}
	
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
	
	public String toString() {
		//jme uses StringBuffer
		StringBuffer sb = new StringBuffer();
		sb.append("Event [source=");
		if (source == null) {
			sb.append("null");
		} else {
			String asString = source.toString();
			if (asString.length() <= 100) {
				sb.append(asString);
			} else {
				try {
					sb.append(ClassUtils.getClassShortName((Class) source));
				} catch (ClassCastException x) {
					sb.append(" instanceof" + ClassUtils.getClassShortName(source.getClass()));
				}
				sb.append("-HASH-");
				sb.append(source.hashCode());
			}
		}
		sb.append(",value=");
		sb.append(value);
		sb.append(",exception=");
		sb.append(exception);
		sb.append("]");
		return sb.toString();
	}
	/* (non-Javadoc)
	 * @see org.adligo.i.util.client.I_Event#getOriginal()
	 */
	public I_Event getOriginal() {
		if (original == null) {
			return this;
		}
		return original;
	}
}
