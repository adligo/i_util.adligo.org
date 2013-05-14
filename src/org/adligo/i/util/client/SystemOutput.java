package org.adligo.i.util.client;

public class SystemOutput implements I_SystemOutput {
	public static final SystemOutput INSTANCE = new SystemOutput();
	
	private SystemOutput() {}
	
	public void err(String p) {
		System.err.print(p);
	}

	public void exception(Throwable x) {
		x.printStackTrace();
	}

	public void out(String p) {
		System.out.print(p);
	}

}
