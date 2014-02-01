package PriorityQueue;

/**
 * This is the interface for the priority queue. You are not allowed to modify
 * this file.
 * 
 * DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE!!!
 * 
 * @author Bryan Franklin
 * 
 * @param <K>
 *            The type of the keys in the priority queue. The queue will be
 *            ordered by the key values.
 * @param <V>
 *            The type of data held in the priority queue.
 */
public interface PriorityQueueInterface<K extends Comparable<K>, V> {

	/**
	 * Add a new <key,value> pair to the queue.
	 * 
	 * @param key
	 *            The key associated with the item (used for ordering)
	 * @param value
	 *            The value the corresponds the item's contents
	 */
	public void enqueue(K key, V value);

	/**
	 * Removes one item from the queue and returns it.
	 * 
	 * @return The next value in the queue
	 */
	public V dequeue();

	/**
	 * Returns the next item in the queue, but does not remove it.
	 * 
	 * @return The next value in queue
	 */
	public V next();

	/**
	 * Returns the number of items in the queue.
	 * 
	 * @return Number of items in the queue.
	 */
	public int size();

}
