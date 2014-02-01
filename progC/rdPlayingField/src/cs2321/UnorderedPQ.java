package cs2321;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * The following methods have O(1) time complexity:
 * insert(), isEmpty(), size()
 * 
 * The following methods have O(n) time complexity:
 * min(), removeMin()
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Chris Rickerd
 */

public class UnorderedPQ<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	
	private ArraySequence<PQEntry<K,V>> array = new ArraySequence<PQEntry<K,V>>();;
	private Position<PQEntry<K, V>> pos;
	
	/**
	 * Inserts an entry into the priority queue with a given key and value and returns the entry inserted.
	 * 
	 * @param key The key contained in the entry to be inserted.
	 * @param value The value contained in the entry to be inserted.
	 * 
	 * @return The entry inserted.
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(n)")
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		
		if (key == null)
			throw new InvalidKeyException("Key is invalid");
		
		//TCJ: Elements are simply added to the end of the array, with no shifting required.
		//SCJ: A new array is created every time.
		
		Entry<K,V> entry = new PQEntry<K,V>(key, value);
		array.add(array.size(), (PQEntry<K, V>) entry);
		return entry;
	}

	/**
	 * Returns true if the priority queue is empty, otherwise returns false.
	 * 
	 * @return true if the priority queue is empty, otherwise returns false.
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public boolean isEmpty() {
		
		return array.size() == 0;
	}

	/**
	 * Returns the entry with the lowest key in the priority queue.
	 * 
	 * @return the entry with the lowest key in the priority queue.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(n)")
	@TimeComplexityExpected("O(n)")
	@SpaceComplexity("O(1)")
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		
		if (isEmpty())
			throw new EmptyPriorityQueueException("Priority Queue is Empty");
		
		//TCJ: It must check the entire list of size n to find the minimum key.
		//SCJ: Only 2 local variables are created.
		
		Entry<K,V> min = array.first().element();
		pos = array.first();
		
		for (int i = 1; i < array.size(); i++){
			
			Entry<K,V> temp = array.atIndex(i).element();
			if (min.getKey().compareTo(temp.getKey()) > 0){
				
				pos = array.atIndex(i);
				min = temp;
			}
			
		}
		
		return min;
	}

	/**
	 * Removes the entry with the lowest key in the priority queue.
	 * 
	 * @return the entry that was removed.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(n)")
	@TimeComplexityExpected("O(n)")
	@SpaceComplexity("O(1)")
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		
		if (isEmpty())
			throw new EmptyPriorityQueueException("Empty Priority Queue");
		
		//TCJ: It must check through the entire list of size n to find the minimum key.
		//SCJ: A constant number of local variables are created.
		
		PQEntry<K,V> temp = (PQEntry<K, V>) min();
		array.remove(array.indexOf(pos));
		
		return temp;
	}

	/**
	 * Returns the number of entries in the priority queue.
	 * 
	 * @return the number of entries in the priority queue.
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public int size() {
		
		return array.size();
	}
	
	public ArraySequence<PQEntry<K,V>> getArray(){
		return array;
	}
	
	/**
	 * TESTING PURPOSES
	 * TESTING PURPOSES
	 * TESTING PURPOSES
	 */
	public String toString(){
		
		String temp = "";
		
		for (int i = 0; i < array.size(); i++){
			
			temp += "Key: " + array.atIndex(i).element().getKey() + ", Value: " + array.atIndex(i).element().getValue() + "\n";
		}
		
		return temp;
	}

}
