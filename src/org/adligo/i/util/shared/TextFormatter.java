package org.adligo.i.util.shared;

public class TextFormatter implements I_TextFormatter {
	protected static I_TextFormatter delegate;

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
	
	protected static synchronized void setDelegate(I_TextFormatter itf) throws Exception {
		if (itf == null) {
			throw new Exception("TextFormatter needs a non null I_TextFormater argument.");
		}
		if (delegate == null) {
			delegate = itf;
		} else {
			throw new Exception("TextFormatter has already been initialized.");
		}
	}
	
	public static final I_TextFormatter getInstance() {
		return delegate;
	}
	
	public static boolean isInit() {
		if (delegate == null) {
			return false;
		}
		return true;
	}
}
