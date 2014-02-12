package org.adligo.i.util.shared;

/**
 * this class is simply to allow use of StringBuilder (which is not synchronized)
 * on platforms that have it (ie jse and gwt)
 * older platforms (jme) use StringBuffer by default 
 * @author scott
 *
 */
public interface I_Appender {
	public void append(Object p);
	public void append(int p);
	public void append(boolean p);
	public void append(short p);
	public void append(byte p);
	public void append(long p);
	public void append(double p);
	public void append(float p);
	public void append(char p);
	
	public void append(String p);
	/**
	 * note this shoud do something simmilar to StringBuilder or StringBuffer 
	 * @return
	 */
	public String toString();
	
	
}
