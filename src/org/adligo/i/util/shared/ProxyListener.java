package org.adligo.i.util.shared;

public class ProxyListener implements I_Listener {
	private I_Listener delegate;

	public I_Listener getDelegate() {
		return delegate;
	}

	public void setDelegate(I_Listener delegate) {
		this.delegate = delegate;
	}

	public void onEvent(I_Event p) {
		delegate.onEvent(p);
	}
	
}
