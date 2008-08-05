package org.adligo.i.util.client;

import junit.framework.TestCase;

public class StringUtilsTest extends TestCase {
	public static final String TEST_ONE = "foo=bar\n" + 
										  "2=3\r" +
										  "me=you\r\n" +
										  "he=she\r";
	
	public void testParse() throws Exception {
		GwtMapFactory.init();
		I_Map result = StringUtils.parse(TEST_ONE);
		
		assertEquals("result.get(\"foo\") should equal bar! ", result.get("foo"), "bar");
		assertEquals("result.get(\"2\") should equal 3! ", result.get("2"), "3");
		assertEquals("result.get(\"me\") should equal you! ", result.get("me"), "you");
		assertEquals("result.get(\"he\") should equal she! ", result.get("he"), "she");
	}
	
	
}
