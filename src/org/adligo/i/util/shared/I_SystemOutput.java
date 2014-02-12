package org.adligo.i.util.shared;

/**
 * solely here to make 
 * System.out.println
 * System.err.println
 * and Exception x.printStackTrace more testable
 * 
 * @author scott
 *
 */
public interface I_SystemOutput {
	/**
	 * calls a System.out.println
	 * @param p
	 */
	public void out(String p);
	
	/**
	 * calls a System.err.println
	 * @param p
	 */
	public void err(String p);
	
	/**
	 * calls a x.printStackTrace
	 * @param x
	 */
	public void exception(Throwable x);
}
