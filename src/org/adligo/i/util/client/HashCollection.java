package org.adligo.i.util.client;

public class HashCollection implements I_Collection {
	
	//Math.abs(Integer.MIN_VALUE) = 2147483648
	// but java wouln't compute it :(
	public static final long INT_SPAN =  (long) (21474836.48 * 100) + 1 + Integer.MAX_VALUE;
	
	private int max = Integer.MAX_VALUE;
	private int min = Integer.MIN_VALUE;
	/**
	 * this turns into the maximum number of b tree lookups
	 */
	private short max_depth = 25;
	/**
	 * this is the depth of the current HashCollection
	 * depth starts at 0 and moves tword max_depth
	 */
	private short depth = 0;
	
	private long span = INT_SPAN;
	
	/**
	 * this is the limit of objects in non max_depth HashCollection.splits
	 */
	private int chunkSize = 100;
	
	/**
	 * the number of buckets that are created from a split
	 */
	private int bucketsFromSplit = 16;
	
	/**
	 * if true the object array collection contains objects
	 * if false the objects are in the splits (HashContainers)
	 */
	private boolean containsObjects = true;
	
	/**
	 * HashLocation's
	 */
	private ArrayCollection hashToLocations;
	private ArrayCollection objects;
	/**
	 * HashContainers
	 */
	private HashCollection[] splits;
	
	public synchronized boolean add(Object p) {
		if (p == null) {
			return false;
		}
		//System.out.println(this.getClass().getName() + " adding" + p);
		return putOrAdd(p, false);
	}
	public boolean put(Object p) {
		return putOrAdd(p, true);
	}
	
	private boolean putOrAdd(Object p, boolean put) {
		if (p == null) {
			return false;
		}
		if (hashToLocations == null) {
			hashToLocations = new ArrayCollection();
			objects = new ArrayCollection();
		}
		int hash = p.hashCode();
		
		if (containsObjects) {
			if (objects.size() < chunkSize) {
				return forceAddOrPut(hash, p, put);
			} else {
				if (depth <  max_depth) {
					split(hash, p, put);
					return true;
				} else {
					return forceAddOrPut(hash, p, put);
				}
			}
		} else {
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(p.hashCode(), min, max, chunckSpan);
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			//System.out.println(this.getClass().getName() + " adding" + p + " to " + hc);
			if (put) {
				return hc.put(p);
			} else {
				return hc.add(p);
			}
		}
	}
	private boolean forceAddOrPut(int hash, Object p, boolean put) {
		HashLocation hl = getHashLocation(hash);
		if (put) {
			if (hl != null) {
				hashToLocations.remove(hl);
				objects.remove(hl.getLocation());
			}
			Integer loc = objects.addInternal(p);
			hashToLocations.add(new HashLocation(hash, loc.intValue()));
			return true;
		} else {
			if (hl != null) {
				return false;
			}
			Integer loc = objects.addInternal(p);
			hashToLocations.add(new HashLocation(hash, loc.intValue()));
			return true;
		}
	}
	
	private HashLocation getHashLocation(int hash) {
		I_Iterator it = hashToLocations.getIterator();
		while (it.hasNext()) {
			HashLocation hl = (HashLocation) it.next();
			if (hl.getHash() == hash) {
				return hl;
			}
		}
		return null;
	}
	private void split(int hash, Object p, boolean put) {
		//System.out.println(this.getClass().getName() + " splitting " + this);
		containsObjects = false;
		int chunckSpan = getSpan(span, bucketsFromSplit);
		
		/**
		 * the child hash containers will be in order
		 */
		int bucket = 0;
		long next = min;
		splits = new HashCollection[bucketsFromSplit];
		
		for (int i = 0; i < bucketsFromSplit; i++) {
			if (next <= Integer.MAX_VALUE) {
				HashCollection hc = new HashCollection();
				hc.min = new Long(next).intValue();
				long nextMax = new Long(hc.min).longValue() + new Long(chunckSpan).longValue();
				if (nextMax >= Integer.MAX_VALUE) {
					hc.max = Integer.MAX_VALUE;
				} else  {
					hc.max = new Long(nextMax).intValue();
				}
				hc.depth = (short) (depth + 1);
				hc.max_depth = max_depth;
				hc.span = chunckSpan;
				/*
				System.out.println(this.getClass().getName() + " in split adding " + hc + 
					"  nextMax was " + nextMax);
					*/
				//System.out.println(this.getClass().getName() + " in split adding bucket " + hc);
				splits[bucket] = hc;
				bucket++;
				next = hc.max + 1;
			}
		}
		
		I_Iterator it = objects.getIterator();
		int counter = 0;
		while (it.hasNext()) {
			Object obj = it.next();
			HashLocation objHash = (HashLocation) hashToLocations.get(counter);
			putObjectInternal(chunckSpan, obj, objHash.getHash(), put);
			counter++;
		}
		
		//add the new one
		putObjectInternal(chunckSpan, p, hash, put);
	}

	private void putObjectInternal(int chunckSpan, Object obj,
			int objHash, boolean put) {
		Integer bucket = getBucket(objHash, min, max, chunckSpan);
		HashCollection hc = (HashCollection) splits[bucket.intValue()];
		if (hc == null) {
			throw new NullPointerException("No HashContainer found for bucket " + bucket);
		}
		if (put) {
			hc.put(obj);
		} else {
			hc.add(obj);
		}
	}
	
	
	public static int getSpan(long p_totalSpan, long p_chunckSize) {
		double d = p_totalSpan / p_chunckSize;
		/*
		System.out.println(HashContainer.class.getClass().getName() + " " + p_totalSpan + "/" + 
				p_chunckSize + " is " + d);
		*/
		return new Double(d).intValue();
	}
	
	
	/**
	 * this should return the hash bucket
	 * 
	 * @param hashCode the hash code
	 * @param min the min hash code for the container
	 * @param max the max hash code for the container
	 * @param span the numbers(size) of a hashBucket
	 * @return
	 */
	public static Integer getBucket(int hashCode, int min, int max, int span) {
		if (hashCode < min) {
			return null;
		}
		if (hashCode > max) {
			return null;
		}
		
		int currentBucket = 0;
		long currentLong = min;
		while (currentLong < max) {
			long next = currentLong + span;
			/*
			System.out.println(HashContainer.class + " in getBucket hashCode = " + 
					hashCode + " min = " + min + " next = " + next );
			*/
			if (hashCode <= next) {
				return new Integer(currentBucket);
			}
			currentLong = currentLong + span + 1;
			currentBucket++;
			/*
			System.out.println(HashContainer.class + " in getBucket currentBucket = " + 
					currentBucket + " currentLong = " + currentLong);
					*/
		}
		return null;
	}
	
	public Object get(int hash) {
		if (containsObjects) {
			if (hashToLocations == null) {
				return null;
			}
			I_Iterator it = hashToLocations.getIterator();
			while (it.hasNext()) {
				HashLocation hl = (HashLocation) it.next();
				if (hash == hl.getHash()) {
					return objects.get(hl.getLocation());
				}
			}
		} else {
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(hash, min, max, chunckSpan);
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			return hc.get(hash);
		}
		return null;
	}
	
	public boolean remove(int hash) {
		if (containsObjects) {
			I_Iterator it = hashToLocations.getIterator();
			while (it.hasNext()) {
				HashLocation hl = (HashLocation) it.next();
				if (hash == hl.getHash()) {
					Object obj = objects.get(hl.getLocation());
					objects.remove(obj);
					hashToLocations.clear();
					//rehash
					I_Iterator itRH = objects.getIterator();
					int locationCounter = 0;
					while (itRH.hasNext()) {
						Object o = itRH.next();
						hashToLocations.add(new HashLocation(o.hashCode(), locationCounter));
						locationCounter++;
					}
					return true;
				}
			}
		} else {
			for (int i = 0; i < splits.length; i++) {
				int chunckSpan = getSpan(span, bucketsFromSplit);
				Integer bucket = getBucket(hash, min, max, chunckSpan);
				HashCollection hc = (HashCollection) splits[bucket.intValue()];
				return hc.remove(hash);
			}
		}
		return false;
	}
	
	public void clear() {
		containsObjects = true;
		hashToLocations = new ArrayCollection();
		objects = new ArrayCollection();
		splits = null;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < depth; i++) {
			sb.append("\t");
		}
		sb.append(super.toString());
		sb.append("[min=");
		sb.append(min);
		sb.append(",max=");
		sb.append(max);
		if (containsObjects) {
			sb.append(",objects=");
			sb.append(objects);
			sb.append(",hashToLocations=");
			sb.append(hashToLocations);
		} else {
			sb.append(",splits;\n");
			for (int i = 0; i < splits.length; i++) {
				//null should only happen during debugging
				if (splits[i] != null) {
					if (splits[i].size() > 0) {
						sb.append(splits[i]);
						sb.append("\n");
					}
				}
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

	public boolean contains(Object other) {
		if (other == null) {
			return false;
		}
		Object mine = get(other.hashCode());
		if (other.equals(mine)) {
			return true;
		}
		return false;
	}

	public synchronized I_Iterator getIterator() {
		Object[] objs = getObjects();
		return new ArrayIterator(objs);
	}

	private Object[] getObjects() {
		Object[] objs = new Object[size()];
		if (containsObjects) {
			objs = objects.toArray();
		} else {
			int counter = 0;
			for (int i = 0; i < splits.length; i++) {
				Object[] child = splits[i].getObjects();
				for (int j = 0; j < child.length; j++) {
					objs[counter] = child[j];
					counter++;
				}
			}
		}
		return objs;
	}
	
	public boolean remove(Object o) {
		return remove(((Integer) o).intValue());
	}

	public int size() {
		if (containsObjects) {
			if (objects == null) {
				return 0;
			}
			return objects.size();
		} else  {
			int total = 0;
			for (int i = 0; i < splits.length; i++) {
				total = total + splits[i].size();
			}
			return total;
		}
	}

	public Object getWrapped() {
		return this;
	}
}
