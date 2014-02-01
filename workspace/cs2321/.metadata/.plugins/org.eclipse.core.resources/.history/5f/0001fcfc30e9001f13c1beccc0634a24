package cs2321;

import net.datastructures.*;

/**
 * An Unordered Logfile implemented using an Linked series of Entries.
 * 
 * @author Brent Nix
 *
 * SCJ: because there are "n" elements in the Linked series its 
 * time complexity is linearly proportional to the amount 
 * of elements in the array
 *
 * @param <K>
 * @param <V>
 */

public class UnOrderedMap <K, V> implements Map<K,V> {

	




	@SpaceComplexity("O(1)")
	private static class MyEntry<K,V> implements Entry<K,V> {
		//proclaim variables
		private K key;
		private V value;

		//**constructor**
		public MyEntry(K k,V v){
			key = k;
			value = v;
		}

		
		public K getKey() { return key; }

		
		public V getValue() { return value; }

	}

	//proclaim variable
	private LinkedSequence<Entry<K,V>> data;
	public UnOrderedMap() {
		//**constructor**
		 data = new LinkedSequence<Entry<K,V>>(); 
	}
	

	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entries() {
		//TCJ: the time varies linearly with the size of list
		LinkedSequence<Entry<K, V>> iter = new LinkedSequence<Entry<K, V>>();//create iterable list
		for (Entry<K,V> f: data)//for each entry in data,
			iter.addLast(f);//add the entry to the list
		return iter;
	}

	@TimeComplexity("O(n)")
	public Entry<K, V> find(K key) throws InvalidKeyException {
		//TCJ: the time varies linearly with the size of list
		if ( key == null ) { throw new InvalidKeyException("Invalid Key"); }//if key is null, throw exception.
		for (Entry<K,V> f: data)//for each entry in java
			if (f.getKey().equals(key))//compare "key" to the key at the current element 
				return f;//if the Entry has been found, this will return the Entry,	

		return null;// other wise it will return null

	}

	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		//TCJ: the time varies linearly with the size of list
		if ( key == null) { throw new InvalidKeyException("Invalid Key"); }//if key is null throw exception

		LinkedSequence<Entry<K, V>> iter = new LinkedSequence<Entry<K, V>>();//create iterable list
		for (Entry<K,V> f: data)//for each entry in data,
		{
			if (f.getKey().equals(key))//compare "key" to the key at "n1" 
				iter.addLast(f);//if the Entry has been found, this will return the Entry,	
		}
		return iter;
	}

	@TimeComplexity("O(1)")
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		//TCJ: time does not vary with list size
		if (key == null || value == null)// if key or value are null, throw exception
			throw new InvalidKeyException("Invalid Key");

		Entry<K, V> e = new MyEntry<K, V>(key, value);
		data.addLast(e);
		return e;
	}

	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		//TCJ: time does not vary with list size
		return data.isEmpty();
	}

	@TimeComplexity("O(n)")
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		//TCJ: time varies linearly with the size of the array
		Position<Entry<K, V>> n1 = null;// create pointer node for traversing list
		for (Position<Entry<K,V>> f: data.positions())
		{			
			if (f.element().equals(e))//compare "key" to the key at "n1" 
				n1 = f; //Save the found element	
		}
		if (n1 == null)//if the entry is not found, throw exception
			throw new InvalidEntryException("Invalid Entry");
		return data.remove(n1);
	}

	@TimeComplexity("O(1)")
	public int size() {
		//TCJ: time does not vary with list size
		return data.size();
	}
	public Position<Entry<K, V>> last()
	{
		return data.last();
	}
	public String toString(){
		String gum = " ";
		for (Entry<K,V> f: data)
		{
			gum = gum + f.getKey() + " , " + f.getValue() + " \n ";
		}
		return gum;
	}


	

	/* (non-Javadoc)
	 * @see net.datastructures.Map#get(java.lang.Object)
	 */
	public V get(K key) throws InvalidKeyException
	{
	    Entry<K, V> lEntry = find( key );
	    if ( lEntry != null )
	    {
	        return lEntry.getValue();
	    }
	    return null;
	}

	

	/* (non-Javadoc)
	 * @see net.datastructures.Map#keys()
	 */
	public Iterable<K> keys() {
		Sequence<K> ret = new LinkedSequence<K>();
		for(Entry<K,V> e: data){
			ret.addLast(e.getKey());
		}
		return ret;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Map#put(java.lang.Object, java.lang.Object)
	 */
	public V put(K key, V value) throws InvalidKeyException {
		Entry<K,V> e = find(key);
		if(e != null){
			remove(e);
			insert(key, value);
			return e.getValue(); 
		} else {
			insert(key, value);
			return null; 
		}
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Map#remove(java.lang.Object)
	 */
	public V remove(K key) throws InvalidKeyException {
		Entry<K,V> e = find(key);
		if(e != null){
			remove(e);
			return e.getValue(); 
		} else {
			return null; 
		}
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Map#values()
	 */
	public Iterable<V> values() {
		Sequence<V> ret = new LinkedSequence<V>();
		for(Entry<K,V> e: data){
			ret.addLast(e.getValue());
		}
		return ret;
	}
	public static void main( String[] args) {
		Logfile<Integer, String> logfile = new Logfile<Integer, String>();
		System.out.println(logfile.isEmpty());
		logfile.insert(0, "A");
		logfile.insert(1, "B");
		logfile.insert(2, "C");
		logfile.insert(3, "D");
		logfile.insert(4, "E");
		logfile.insert(5, "F");
		logfile.insert(6, "G");
		logfile.insert(7, "H");
		logfile.insert(1, "B");
		logfile.insert(8, "I");
		logfile.insert(9, "J");
		logfile.insert(10, "K");
		logfile.insert(11, "L");
		logfile.insert(12, "M");
		logfile.insert(1, "B");
		logfile.insert(13, "N");
		System.out.println(logfile.remove(logfile.find(5)).getValue());
		System.out.println(logfile);
		System.out.println(logfile.isEmpty());
		System.out.println(logfile.find(4).getValue());
		System.out.println(logfile.findAll(1));
		System.out.println(logfile.entries());



	}
}

	