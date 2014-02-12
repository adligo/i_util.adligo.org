package org.adligo.i.util.shared;

public class DoNothingListener implements I_Listener {
	public static final DoNothingListener INSTANCE = new DoNothingListener();
	
	private DoNothingListener() {}
	
	public void onEvent(I_Event p) {
		//do nothing per class name
	}

}
