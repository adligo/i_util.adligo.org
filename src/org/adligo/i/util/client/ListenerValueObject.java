package org.adligo.i.util.client;

public class ListenerValueObject implements I_Disposable {
	private I_Listener listener = null;
	private Object value = null;
	
	
	public void dispose() {
		listener = null;
		value = null;
	}
	
	public I_Listener getListener() {
		return listener;
	}
	public Object getValue() {
		return value;
	}
	public void setListener(I_Listener listener) {
		this.listener = listener;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}