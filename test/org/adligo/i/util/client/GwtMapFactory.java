package org.adligo.i.util.client;

import java.util.Map;

import org.adligo.i.util.client.I_Factory;
import org.adligo.i.util.client.MapFactory;

public class GwtMapFactory extends MapFactory implements I_Factory {

	
	public Object createNew(Object p) {
		return new MapWrapper((Map) p);
	}

	protected static void init() throws Exception {
		init(new GwtMapFactory());
	}
}
