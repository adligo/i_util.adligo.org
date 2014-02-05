package org.adligo.i.util.client;

/**
 * NOT THREAD SAFE you must wrap with a Synchronizer
 * This is a collection
 * which starts out fairly simple
 * 		Objects go in and are tracked by hashToLocations
 *      they are referenced from objects 
 *      so the HashLocation's location attribute pertains to the index in the object ArrayCollection
 *     
 *     After you add enough objects to this class (more than chunkSize)
 *     it mutates it self so that object are now referenced from  
 *     splits so by default there will be 16 HashCollections children of this.
 *       These child HashCollections are sometimes referred to as buckets in the code
 *     
 *     
 *     Then when a object is looked up 
 *     the following operation occurs;
 *     hashCode is used to determine which bucket
 *     		and get recurses into the child HashCollection repeating the logic.
 *     
 * @author scott
 *
 */
public class HashCollection implements I_Collection, I_HashCollection {
	
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
	 * if true the object ArrayCollection contains objects
	 * if false the objects are in the splits (HashContainers)
	 */
	private boolean containsObjects = true;
	
	/**
	 * A collection of HashLocation's
	 */
	private ArrayCollection hashToLocations;
	/**
	 * This may contain the objects directly
	 * @see containsObjects
	 */
	private ArrayCollection objects;
	private boolean objects_contains_hash_dupe = false;
	/**
	 * HashContainers
	 * This may contain the objects 
	 * @see containsObjects
	 */
	private HashCollection[] splits;
	
	public  boolean add(Object p) {
		//System.out.println(this.getClass().getName() + " adding" + p);
		return putOrAdd(p);
	}
	public  boolean put(Object p) {
		return putOrAdd(p);
	}
	
	private boolean putOrAdd(Object p) {
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
				return forceAdd(hash, p);
			} else {
				if (depth <  max_depth) {
					split(p);
					return true;
				} else {
					return forceAdd(hash, p);
				}
			}
		} else {
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(p.hashCode(), min, max, chunckSpan);
			if (bucket == null) {
				printNullBucketError(chunckSpan, p.hashCode());
				return false;
			} else {
				HashCollection hc = (HashCollection) splits[bucket.intValue()];
				//System.out.println(this.getClass().getName() + " adding" + p + " to " + hc);
				return hc.add(p);
			}
		}
	}
	
	private boolean forceAdd(int hash, Object p) {
		try {
			HashDupeCollection internalCollection = (HashDupeCollection) p;
			Object objectWithHash = internalCollection.get(0);
			if (objectWithHash == null) {
				//objectWithHash should never be null but just incase
				return false;
			}
			int newHash = objectWithHash.hashCode();
			Integer location = objects.addInternal(internalCollection);
			hashToLocations.add(new HashLocation(newHash, location.intValue()));
			objects_contains_hash_dupe = true;
			return true;
		} catch (ClassCastException x) {
			//do nothing;
		}
		HashLocation hl = getHashLocation(hash);
		
		if (hl != null) {
			int location = hl.getLocation();
			Object obj = objects.get(location);
			
			try {
				HashDupeCollection ac = (HashDupeCollection) obj;
				if (ac.contains(p)) {
					return false;
				}
				ac.add(p);
			} catch (ClassCastException x) {
				if (obj.equals(p)) {
					return false;
				}
				HashDupeCollection newAc = new HashDupeCollection();
				newAc.add(p);
				newAc.add(obj);
				objects.set(location, newAc);
				objects_contains_hash_dupe = true;
			}
			return true;
		}
		Integer loc = objects.addInternal(p);
		hashToLocations.add(new HashLocation(hash, loc.intValue()));
		return true;
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
	private void split(Object p) {
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
				hc.min = (int) next;
				long nextMax = new Long(hc.min).longValue() + new Long(chunckSpan).longValue();
				if (nextMax >= Integer.MAX_VALUE) {
					hc.max = Integer.MAX_VALUE;
				} else  {
					hc.max = (int) nextMax;
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
			putObjectInternal(chunckSpan, obj, objHash.getHash());
			counter++;
		}
		
		//add the new one
		try {
			HashDupeCollection hac = (HashDupeCollection) p;
			Object internalHashObj = hac.get(0);
			if (internalHashObj != null) {
				putObjectInternal(chunckSpan, p, internalHashObj.hashCode());
			}
		} catch (ClassCastException x) {
			putObjectInternal(chunckSpan, p, p.hashCode());
		}
	}

	private void putObjectInternal(int chunckSpan, Object obj, int hash) {
		try {
			HashDupeCollection dupe = (HashDupeCollection) obj;
		} catch (ClassCastException x) {
			hash = obj.hashCode();
		}
		
		Integer bucket = getBucket(hash, min, max, chunckSpan);
		if (bucket == null) {
			printNullBucketError(chunckSpan, hash);
		} else {
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			if (hc == null) {
				throw new NullPointerException("No HashContainer found for bucket " + bucket);
			}
			hc.add(obj);
		}
	}
	private void printNullBucketError(int chunckSpan, int objHash) {
		System.out.println("org.adligo.i.util.HashCollection null bucket for parameters objHash=" + 
				objHash + ", min=" +min+", max=" +max+ ", chunckSpan=" + chunckSpan);
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
	
	/**
	 * may return a ArrayCollection of objects
	 * if more than one object added had the same hash code
	 * 
	 * @param hash
	 * @return
	 */
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
	
	public Object get(Object obj) {
		int hash = obj.hashCode();
		
		Object toRet = null;
		if (containsObjects) {
			if (hashToLocations == null) {
				return null;
			}
			I_Iterator it = hashToLocations.getIterator();
			while (it.hasNext()) {
				HashLocation hl = (HashLocation) it.next();
				if (hash == hl.getHash()) {
					toRet = objects.get(hl.getLocation());
					break;
				}
			}
		} else {
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(hash, min, max, chunckSpan);
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			toRet = hc.get(obj);
		}
		try {
			if (toRet != null) {
				return ((ArrayCollection) toRet).get(obj);
			}
		} catch (ClassCastException x) {
			//do nothing;
		}
		return toRet;
	}
	
	public  boolean remove(int hash) {
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
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(hash, min, max, chunckSpan);
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			return hc.remove(hash);
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

	public I_Iterator getIterator() {
		Object[] objs = getObjects();
		return new ArrayIterator(objs);
	}

	private Object[] getObjects() {
		Object[] objs = new Object[size()];
		
		if (containsObjects) {
			if (objects == null) {
				return objs;
			}
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
	
	public boolean remove(Object obj) {
		int hash = obj.hashCode();
		
		if (containsObjects) {
			if (hashToLocations == null) {
				return false;
			}
			I_Iterator it = hashToLocations.getIterator();
			while (it.hasNext()) {
				HashLocation hl = (HashLocation) it.next();
				if (hash == hl.getHash()) {
					Object other = objects.get(hl.getLocation());
					try {
						return ((HashDupeCollection) other).remove(obj);
					} catch (ClassCastException x) {
						return objects.remove(hl.getLocation());
					}
				}
			}
		} else {
			int chunckSpan = getSpan(span, bucketsFromSplit);
			Integer bucket = getBucket(hash, min, max, chunckSpan);
			HashCollection hc = (HashCollection) splits[bucket.intValue()];
			return hc.remove(obj);
		}
		return false;
	}

	public int size() {
		if (containsObjects) {
			if (objects == null) {
				return 0;
			} 
			if (objects_contains_hash_dupe) {
				int total = 0;
				for (int i = 0; i < objects.size(); i++) {
					Object o = objects.get(i);
					try {
						total = total + ((ArrayCollection) o).size();
					} catch (ClassCastException x) {
						total++;
					}
				}
				return total;
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
