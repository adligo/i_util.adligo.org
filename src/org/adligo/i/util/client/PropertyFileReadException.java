package org.adligo.i.util.client;

public class PropertyFileReadException extends Exception {
	private String fileName;
	private String attemptedSystemFileName;
	
	public PropertyFileReadException() {}
	
	public PropertyFileReadException(String message) {
		super(message);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttemptedSystemFileName() {
		return attemptedSystemFileName;
	}

	public void setAttemptedSystemFileName(String attemptedSystemFileName) {
		this.attemptedSystemFileName = attemptedSystemFileName;
	}
}
