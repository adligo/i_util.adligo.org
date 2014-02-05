package org.adligo.i.util.client;

/**
 * This is a helper class to HashCollection
 * 
 * @author scott
 *
 */
public class HashLocation {
	/**
	 * the hash code of the Object HashCollection is dealing with
	 */
	private int hash;
	/**
	 * the index to find the Object in in the HashCollection
	 */
	private int location;
	
	public HashLocation(int p_hash, int p_location) {
		hash = p_hash;
		location = p_location;
	}
	
	public int getHash() {
		return hash;
	}
	public int getLocation() {
		return location;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append (" [hash=");
		sb.append (hash);
		sb.append (",location=");
		sb.append (location);
		sb.append ("]");
		return sb.toString();
	}
}
