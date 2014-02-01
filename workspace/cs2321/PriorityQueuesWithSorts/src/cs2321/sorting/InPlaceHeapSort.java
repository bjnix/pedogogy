package cs2321.sorting;

import cs2321.ArraySequence;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {

	/**
	 * sort - Perform an in-place heap sort
	 * @param array - Array to sort
	 */
	
	public void sort(K[] array) {
		ArraySequence<K> array1 = new ArraySequence<K>(); 
		for ( int i = 0 ; i < array.length ; i ++ )
			array1.add(i, array[i]);

		ipHeapSort(array1);
		System.out.println(array1.toString() +" ***" );
		for( int i = 0 ; i< array.length ; i ++ )
		{
			array[i] = array1.get(i);
			System.out.println(array[i] + " -- ");
		}
	}

	private void ipHeapSort(ArraySequence<K> array) {
	    /* This method performs an in-place heapsort. Starting
	     * from the beginning of the array, the array is swapped
	     * into a binary max heap.  Then elements are removed
	     * from the heap, and added to the front of the sorted
	     * section of the array. */
	 
	    /* Insertion onto heap */
	    for (int heapsize=0; heapsize<array.size(); heapsize++) {
	        /* Step one in adding an element to the heap in the
	         * place that element at the end of the heap array-
	         * in this case, the element is already there. */
	        int n = heapsize; // the index of the inserted int
	        while (n > 0) 
	        { // until we reach the root of the heap
	            int p = (n-1)/2; // the index of the parent of n
	            if (array.get(n).compareTo(array.get(p)) > 0) { // child is larger than parent
	                K temp = array.get(p);
	            	array.swapElements(n, p); // swap child with parent
//	                n = p; // check parent
	            }
	            else // parent is larger than child
	                break; // all is good in the heap
	        }
	    }
	 
	    /* Removal from heap */
	    for (int heapsize = array.size(); heapsize>0;) {
	        array.swapElements( 0, --heapsize); // swap root with the last heap element
	        int n = 0; // index of the element being moved down the tree
	        while (true) {
	            int left = (n*2)+1;
	            if (left >= heapsize) // node has no left child
	                break; // reached the bottom; heap is heapified
	            int right = left+1;
	            if (right >= heapsize) { // node has a left child, but no right child
	                if (array.get(left).compareTo(array.get(n)) > 0) // if left child is greater than node
	                    array.swapElements(left, n); // swap left child with node
	                break; // heap is heapified
	            }
	            if (array.get(left).compareTo(array.get(left)) > 0){ // (left > n)
	                if (array.get(left).compareTo(array.get(right)) > 0) { // (left > right) & (left > n)
	                    array.swapElements(left, n);
	                    n = left; continue; // continue recursion on left child
	                } else { // (right > left > n)
	                    array.swapElements(right, n);
	                    n = right; continue; // continue recursion on right child
	                }
	            } else { // (n > left)
	                if (array.get(right).compareTo(array.get(n)) > 0) { // (right > n > left)
	                    array.swapElements(right, n);
	                    n = right; continue; // continue recursion on right child
	                } else { // (n > left) & (n > right)
	                    break; // node is greater than both children, so it's heapified
	                }
	            }
	        }
	    }
	}


}
