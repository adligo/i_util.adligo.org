package org.adligo.i.util.client;

/**
 * this class is simply to allow use of StringBuilder (which is not synchronized)
 * on platforms that have it (ie jse and gwt)
 * older platforms (jme) use StringBuffer by default 
 * @author scott
 *
 */
public interface I_StringAppender {
	public void append(String p);
}
