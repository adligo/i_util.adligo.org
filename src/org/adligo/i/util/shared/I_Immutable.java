package org.adligo.i.util.shared;

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
	 * the java field name which should be used to serialize the object.
	 * NOTE if this field is a String or other primitive wrapper (Integer, Long exc)
	 * the toString method will be used when writing the xml.
	 * 
	 * Note the Adligo xml_io code generation has issues if parent/child classes 
	 * have a variable with the same name.  Also I believe GWT RPC seriliation does
	 * so try to make this unique to your class.
	 * 
	 * @return
	 */
	public String getImmutableFieldName();
}
