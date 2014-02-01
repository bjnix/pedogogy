package cs2321;

import net.datastructures.*;

/**
 * An implementation of a HashMap
 * 
 * @author Chris Rickerd
 * CS2321 Assignment #5
 *
 * @param <K> key
 * @param <V> value
 */
public class HashMap<K, V> implements Map<K, V> {

	private UnorderedMap<K,V>[] buckets;
	private int size = 0;
	private int hashsize = 23;
	
	public HashMap(int hashsize){
		
		buckets = (UnorderedMap<K,V>[])new UnorderedMap[hashsize];
		this.hashsize = hashsize;
		
		for (int i = 0; i < hashsize; i++)
			buckets[i] = new UnorderedMap<K,V>();
	}

	public HashMap(){ //default constructor
		
		buckets = (UnorderedMap<K,V>[])new UnorderedMap[hashsize];
		for (int i = 0; i < hashsize; i++)
			buckets[i] = new UnorderedMap<K,V>();
	}

	/**
	 * Returns an iterable list of all entries in the hashMap
	 * 
	 * @return an iterable list of all entries in the hashMap
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<Entry<K, V>> entries() {
		
		LinkedSequence<Entry<K,V>> seq = new LinkedSequence<Entry<K,V>>();
		
		for (Map<K,V> map: buckets)
			for (Entry<K,V> entry: map.entries())
				seq.addLast(entry);
		
		return seq;
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key The key to search for
	 * @return the value associated with the given key
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@SpaceComplexity("O(1)")
	public V get(K key) throws InvalidKeyException {

		//TCJ: Only costs O(n) if all entries fall in the same bucket
		//TCJ: Only costs more than O(1) if collisions occur
		int temp = key.hashCode();
		while (temp < 0)
			temp = (temp + hashsize)%hashsize;
		
		return buckets[temp%hashsize].get(key);
	}

	/**
	 * Returns whether the hashMap is empty.
	 * 
	 * @return whether the hashMap is empty
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	public boolean isEmpty() {
		
		return size == 0;
	}

	/**
	 * Returns an iterable list of the keys of all entries in the hashMap.
	 * 
	 * @return an iterable list of the keys of all entries in the hashMap
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<K> keys() {
		
		LinkedSequence<K> seq = new LinkedSequence<K>();
		
		for (Map<K,V> map: buckets)
			for (Entry<K,V> entry: map.entries())
				seq.addLast(entry.getKey());
		
		return seq;
	}

	/**
	 * Puts a given key-value pair into the hashMap, replacing an entry if the key already exists and returns the old value
	 * 
	 * @param key The key to insert
	 * @param value The value to insert
	 * @return The old value inserted
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	public V put(K key, V value) throws InvalidKeyException {
		
		//TCJ: Even if collisions occur, there is a constant cost.
		size++;
		
		int temp = key.hashCode();
		
		while (temp < 0)
			temp = (temp + hashsize)%hashsize;
		
		return buckets[temp%hashsize].put(key, value);
	}

	/**
	 * Puts a given key-value pair into the hashMap, replacing an entry if the key already exists and returns the old value
	 * 
	 * @param key The key to insert
	 * @param value The value to insert
	 * @return The old value inserted
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@SpaceComplexity("O(1)")
	public V remove(K key) throws InvalidKeyException {
		
		//TCJ: Only costs O(n) if all entries fall in the same bucket
		//TCJ: Only costs more than O(1) if collisions occur
		size--;
		int temp = key.hashCode();
		while (temp < 0)
			temp = (temp + hashsize)%hashsize;
		
		return buckets[temp%hashsize].remove(key);
	}

	/**
	 * Returns the number of entries in the hashMap.
	 * 
	 * @return the number of entries in the hashMap
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	public int size() {
		
		return size;
	}

	/**
	 * Returns an iterable list of the values of all entries in the hashMap.
	 * 
	 * @return an iterable list of the values of all entries in the hashMap
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<V> values() {
		
		LinkedSequence<V> seq = new LinkedSequence<V>();
		
		for (Map<K,V> map: buckets)
			for (Entry<K,V> entry: map.entries())
				seq.addLast(entry.getValue());
		
		return seq;
	}
	
	/**
	 * TESTING
	 */
	public String toString(){
		
		String temp = "";
		
		for (Map<K,V> map: buckets)
			for (Entry<K,V> entry: map.entries())
				temp += "Key: " + entry.getKey() + ", Value: " + entry.getValue() + "\n";
		
		return temp;
	}

}
