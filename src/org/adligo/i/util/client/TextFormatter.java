package org.adligo.i.util.client;

public class TextFormatter implements I_TextFormatter {
	private static I_TextFormatter delegate;

	public String format(String format, double value) {
		return delegate.format(format, value);
	}

	public String format(String format, float value) {
		return delegate.format(format, value);
	}

	public String format(String format, int value) {
		return delegate.format(format, value);
	}

	public String format(String format, long value) {
		return delegate.format(format, value);
	}

	public String format(String format, short value) {
		return delegate.format(format, value);
	}

	public String formatDate(String format, long value) {
		return delegate.formatDate(format, value);
	}
	
	protected static void setDelegate(I_TextFormatter itf) {
		delegate = itf;
	}
	
	public static final I_TextFormatter getInstance() {
		return delegate;
	}
}
