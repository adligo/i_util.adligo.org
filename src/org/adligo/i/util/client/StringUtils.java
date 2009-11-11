package org.adligo.i.util.client;


public class StringUtils {
	public static final char EQUALS = '=';
	public static final char UNIX_LINE_FEED = (char) "\n".toCharArray()[0];
	public static final char DOS_LINE_FEED = (char) "\r".toCharArray()[0];
	private static final boolean SELF_LOG = false;
	
	/**
	 * creates a map out of a string
	 * (loaded from a property file)
	 * 
	 * @param p
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
		String key_string = key.toString(); 
		String value = val.toString();
		if (SELF_LOG) {
			System.out.println("putting '" + key_string + "','" +
					value + "'");
		}
		map.put(key_string, value);
	}
	
	public static  boolean isEmpty(String p) {
		if (p == null) {
			return true;
		}
		if (p.trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * apparently not in some versions of CLDC
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equalsIgnoreCase(String a, String b) {
		if (a == null &&  b == null) {
			return true;
		}
		if (a == null || b == null) {
			return false;
		}
		String a1 = a.toLowerCase();
		String b1 = b.toLowerCase();
		//System.out.println(" compairing " + a1 + " and " +b1);
		return a1.equals(b1);
	}
	
	/**
	 * GWT didn't implement
	 * @param p
	 * @param i
	 * @return
	 */
	public static int lastIndexOf(String p, char i) {
		if (p == null) {
			return -1;
		}
		int lastIndex = -1;
		char [] chars = p.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			if (chars[j] == i) {
				lastIndex = j;
			}
		}
		return lastIndex;
	}
}
