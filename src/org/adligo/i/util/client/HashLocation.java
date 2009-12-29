package org.adligo.i.util.client;

public class HashLocation {
	private int hash;
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
