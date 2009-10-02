package org.adligo.i.util.client;

public interface I_Event {

	public Object getValue();
	public Throwable getException();
	public Object getSource();
	public I_Event getOriginal();

	public void setValue(Object value);
	public void setException(Throwable exception);
}