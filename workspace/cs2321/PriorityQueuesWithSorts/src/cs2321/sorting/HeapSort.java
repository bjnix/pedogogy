package cs2321.sorting;

import cs2321.Heap;
import java.util.Random;

public class HeapSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	/**
	 * sort - Perform an PQ sort using a Heap
	 * @param array - Array to sort
	 */
	public void sort(K[] array){
		super.sort(array, new Heap<K,K>());
	}

	
}



