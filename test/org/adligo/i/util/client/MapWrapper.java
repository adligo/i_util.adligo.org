package org.adligo.i.util.client;

import java.util.Map;

import org.adligo.i.util.client.I_Map;

public class MapWrapper implements I_Map {
	public void putAll(Map m) {
		me.putAll(m);
	}

	public void clear() {
		me.clear();
	}

	public boolean containsKey(Object key) {
		return me.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return me.containsValue(value);
	}

	/**
	 * will cast to Set
	 */
	public Object entrySet() {
		return me.entrySet();
	}

	public Object get(Object key) {
		return me.get(key);
	}

	
	public boolean isEmpty() {
		return me.isEmpty();
	}
	/**
	 * will cast to Set
	 */
	public Object keySet() {
		return me.keySet();
	}

	public Object put(Object key, Object value) {
		return me.put(key, value);
	}

	public void putAll(I_Map t) {
		me.putAll( ((MapWrapper) t).me);
	}

	public Object remove(Object key) {
		return me.remove(key);
	}

	public int size() {
		return me.size();
	}

	/**
	 * will cast to Collection
	 */
	public Object values() {
		return me.values();
	}

	private Map  me = null;
	
	public MapWrapper(Map p) {
		me = p;
	}
	
	
	
}
