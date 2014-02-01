package cs2321;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * SCJ: there are n elements in the array it is O(n) 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @author Brent Nix
 */
@SpaceComplexity("O(n)")
public class OrderedPQ<K extends Comparable<K>,V> implements PriorityQueue<K,V> {
	//initialize variables
	private ArraySequence<Pos<K,V>> ArrSeq;//Array sequence used to implement the PQ

	//constructor
	public OrderedPQ()
	{
		ArrSeq = new ArraySequence<Pos<K,V>>();
	}

	/**
	 * private method that checks for an InvalidKeyException 
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
	@TimeComplexityAmortized("O(n)")
	@TimeComplexity("O(n)") 
	@TimeComplexityExpected("O(n)")
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		//TCJ: It is O(n) because it has to iterate through the list and find the proper place to insert
		checkKey(key);//may throw InvalidKeyException
		Pos<K,V> addme = new Pos<K,V>(key,value);//create position object

		if(ArrSeq.isEmpty())//check for empty list
		{
			//			System.out.println("empty 0"  + " -- insert" + addme.getKey().toString());
			//Check for special case
			ArrSeq.add(0,addme);//add element
			return addme;

		}
		for( int i = 0 ; i < size() ; i ++ )//find position and add
		{
			//			System.out.println(ArrSeq.get(i).getKey().compareTo(key) + " , " + ArrSeq.get(i).getKey().toString() + " , " + key);
			if (ArrSeq.get(i).getKey().compareTo(key) <= 0)//if compareTo result is negative or equivalent - add
			{				
				ArrSeq.add(i,addme);
				//				System.out.println( i + " -- insert " + addme.getKey().toString());
				return addme;
			}
		}
		ArrSeq.addLast(addme);//if compare to is positive, addLast

		return addme;
	}
	/**
	 * Boolean that returns whether or not the list is empty
	 */
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		//TCJ: time complexity does not vary with size - it is constant
		return ArrSeq.isEmpty();
	}

	@TimeComplexity("O(1)")
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.getLast();
	}
	@TimeComplexity("O(1)")
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.removeLast();
	}
	@TimeComplexity("O(1)")
	public int size() {
		//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.size();
	}

}
