package cs2321;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * SCJ: there are n elements in the array it is O(n) 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @author Brent Nix
 */
@SpaceComplexity("O(n)")
public class UnorderedPQ<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	//initialize variables
	private ArraySequence<Pos<K,V>> ArrSeq;//Array sequence used to implement the PQ
	private Position<Pos<K,V>> temp;

	//constructor
	public UnorderedPQ()
	{
		ArrSeq = new ArraySequence<Pos<K,V>>();
	}
	/**
	 * Checks if Key is valid
	 * @param key
	 * @throws InvalidKeyException
	 */
	@TimeComplexity("O(1)")
	private void checkKey(K key) throws InvalidKeyException {
		//TCJ: time complexity does not vary with size - it is constant
		try{
			key.compareTo(key);
		}
		catch(Exception e){
			throw new InvalidKeyException("Invalid key");
		}

	}

	@TimeComplexity("O(1)")
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		//TCJ: time complexity does not vary with size - it is constant
		checkKey(key);// may throw InvalidKeyException
		Pos<K,V> addme = new Pos<K,V>(key,value);//create position object

		ArrSeq.addLast(addme);

		return addme;
	}

	public boolean isEmpty() {

		return ArrSeq.isEmpty();
	}
	@TimeComplexity("O(n)")
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		//TCJ: time complexity is O(n) because the method has to iterate 
		//through the queue to find the smallest element
		//initialize variables
		Entry<K, V> min = ArrSeq.getFirst();
		temp = ArrSeq.first();//keeps track of array sequence element

		for( int i = 1 ; i < size() ; i ++ )
		{
			int yN = ArrSeq.get(i).getKey().compareTo(min.getKey());

			if ( yN <= 0)
			{
				min = ArrSeq.get(i);
				temp = ArrSeq.atIndex(i);
			}
		}
		return min;
	}
	@TimeComplexity("O(n)")
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		//TCJ: time complexity is O(n) because the method has to iterate 
		//through the queue to find the smallest element

		min();
		return ArrSeq.remove(temp);
	}
@TimeComplexity("O(1)")
	public int size() {
	//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.size();
	}

}
