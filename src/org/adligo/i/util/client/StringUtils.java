package org.adligo.i.util.client;

import java.util.HashMap;

public class StringUtils {
	public static final char EQUALS = '=';
	public static final char UNIX_LINE_FEED = (char) "\n".toCharArray()[0];
	public static final char DOS_LINE_FEED = (char) "\r".toCharArray()[0];
	private static final boolean SELF_LOG = false;
	
	/**
	 * creates a map out of a string
	 * 
	 * @param p
	 * @param delimiter
	 * @param newLine
	 * 
	 * @return
	 */
	public static void parse(String p, I_Map map) {
		StringBuffer key = new StringBuffer();
    	StringBuffer val = new StringBuffer();
		char [] chars = p.toCharArray();
		boolean inKey = true;
		
		for (int i = 0; i < chars.length; i++) {
		
    		char c  = chars[i];
    		if (SELF_LOG) {
    			System.out.println("ck char '" + c + "'");
    		}
    		if (EQUALS == c) {
    			inKey = false;
    		} else {
        		if (UNIX_LINE_FEED == c || DOS_LINE_FEED == c) {
        			addValue(map, key, val);
        			inKey = true;
        			key = new StringBuffer();
                	val = new StringBuffer();
        		} else {
        			if (inKey) {
        				key.append(c);
        			} else {
        				val.append(c);
        			}
        		}
    		}
    	}
		addValue(map, key, val);
	}

	private static void addValue(I_Map map, StringBuffer key, StringBuffer val) {
		if (SELF_LOG) {
			System.out.println("putting '" + key.toString() + "','" +
					val.toString() + "'");
		}
		map.put(key.toString(), val.toString());
	}
	
}
