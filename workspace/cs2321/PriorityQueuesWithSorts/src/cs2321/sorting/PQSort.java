package cs2321.sorting;

import net.datastructures.*;

/**
 * A class that performs three forms of PQ Sort:
 *   SelectionSort (Unordered PQ)
 *   InsertionSort (Ordered PQ)
 *   HeapSort (Heap PQ)
 * 
 * @author bsiever
 *
 * @param <K>
 */
public abstract class PQSort<K extends Comparable<K>>{
	
	/*
	 * Simple PQSort - relies on a functional PQ and 
	 * a Sequence's addLast, first, last, and next operations are used.
	 * 
	 */
	
	protected void sort(K[] kArray, PriorityQueue<K,K> pq) {
		
		// Get each value from the array, placing it into the Priority Queue
		for(K k : kArray)
			pq.insert(k,k);

		
		// Put all the data back into the array (in order)
		int index = 0;
		while(!pq.isEmpty()){
			Entry<K,K> e = pq.removeMin();
			kArray[index] = e.getKey();
			index ++;
		}
	
		
	}
}
