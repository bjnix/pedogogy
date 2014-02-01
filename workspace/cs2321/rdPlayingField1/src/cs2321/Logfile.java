package cs2321;

import java.util.Iterator;
import net.datastructures.*;

/**
 * An implementation of a Logfile storing entries containing both a Key and a Value.
 * 
 * @author Chris Rickerd
 * CS2321 Assignment #5
 *
 * @param <K> Key
 * @param <V> Value
 */
public class Logfile<K, V> implements Dictionary<K, V> {

	private LinkedSequence<Entry<K,V>> data;

	public Logfile(){

		data = new LinkedSequence<Entry<K,V>>();
	}

	/**
	 * Returns an iterable list of the elements in the log file.
	 * 
	 * @return An iterable list of the elements in the log file
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<Entry<K, V>> entries() {

		LinkedSequence<Entry<K,V>> newlist = new LinkedSequence<Entry<K,V>>();

		for (Entry<K,V> e: data)
			newlist.addLast(e);

		return newlist;
	}

	/**
	 * Returns the first element in the log file containing the given key.
	 * 
	 * @param key The key to find in the log file
	 * @return The first entry containing the given key
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	public Entry<K, V> find(K key) throws InvalidKeyException {
		
		if (key == null)
			throw new InvalidKeyException("Key is invalid");

		for (Entry<K,V> e: data) //iterate through the list
			if (e.getKey().equals(key)) //compare each element in the list with the parameter
				return e;

		return null; //returns null if no such entry exists
	}

	/**
	 * Returns an iterable list of all entries in the log file containing the given key.
	 * 
	 * @param key The key to search for
	 * @return an iterable list of all entries in the log file containing the given key
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {

		LinkedSequence<Entry<K,V>> all = new LinkedSequence<Entry<K,V>>();

		for (Entry<K,V> e: data)
			if (e.getKey().equals(key))
				all.addLast(e);

		return all;
	}

	/**
	 * Inserts a new entry containing the given key and value into the log file.
	 * 
	 * @param key The key to give the new entry
	 * @param value The value to give the new entry
	 * @return The entry created
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {

		DictionaryEntry<K,V> temp = new DictionaryEntry<K,V>(key,value);

		data.addLast(temp);
		return temp;
	}

	/**
	 * Returns true if the log file contains no elements.
	 * 
	 * @return true if the log file contains no elements.
	 */
	public boolean isEmpty() {

		return data.isEmpty();
	}

	/**
	 * Removes and returns the given entry from the log file.
	 * 
	 * @param e The entry to remove
	 * @return The entry removed
	 */
	@TimeComplexity("O(n)")
	@SpaceComplexity("O(1)")
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		
		if (e == null)
			throw new InvalidEntryException("Entry is null");

		Position<Entry<K,V>> pos = data.first();

		for (int i = 0; i < data.size(); i++){
			
			if (pos.element().equals(e))
				return data.remove(pos);
			
			else
				pos = data.next(pos);
		}
		
		return null;
	}

	/**
	 * Returns the number of elements stored in the log file.
	 * 
	 * @return the number of elements stored in the log file.
	 */
	@TimeComplexity("O(1)")
	@SpaceComplexity("O(1)")
	public int size() {

		return data.size();
	}

	/**
	 * TESTING PURPOSES
	 */
	public String toString(){

		String temp = "";

		for (Entry<K,V> e: data)
			temp += "Key: " + e.getKey() + ", Value: " + e.getValue() + "\n";

		return temp;
	}

}
