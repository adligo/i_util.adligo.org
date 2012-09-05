package org.adligo.i.util.client;

/**
 * this is simply a marker interface which shows the xml_io_generator
 * project how to write immutable objects to and from xml.
 * Classes which implement this interface should have a default no arg constructor
 * and a constructor which has a argument with a single argument that can 
 * be used as a constructor parameter to the type of the field with the name
 * returned by getImmutableFieldName();
 * 
 * @author scott
 *
 */
public interface I_Immutable  {
	/**
	 * the new standard to return from the getImmutableFieldName method
	 */
	public static final String MUTANT = "mutant";
	/**
	 * the java field name which should be used to serialize the object.
	 * NOTE if this field is a String or other primitive wrapper (Integer, Long exc)
	 * the toString method will be used when writing the xml.
	 * @return
	 */
	public String getImmutableFieldName();
}
