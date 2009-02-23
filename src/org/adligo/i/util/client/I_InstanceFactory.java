package org.adligo.i.util.client;

public interface I_InstanceFactory {
  /**
   * This method is a wrapper (adaptor) around creating a instance of a 
   * class like this;
   * 
   * Class c = Class.forName(sClass);
   * Constructor ct = c.getConstructor(new Class[] {});
   * 
   * Which can't be done on GWT but you can just have code like
   * this do it for you
   * 
   * if (ClassUtils.getClassName(MyClass.class).equals(clazz)) {
   *    return new MyClass();
   * }
   * 
   * @return
   */
   public Object createInstance(String clazz);
}
