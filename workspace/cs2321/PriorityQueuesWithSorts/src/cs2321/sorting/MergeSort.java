package cs2321.sorting;
/**
 * 
 * @author brent Nix
 *
 * @param <E>
 */
public class MergeSort<E extends Comparable> implements Sorter<E> {

	public void sort(E[] array) {
				for(int i = 0 ; i < array.length; i ++ )	
					System.out.println(" " + array[i]);
		sortM(array);
				for(int i = 0 ; i < array.length; i ++ )	
					System.out.println("-- " + array[i]);		

	}
	/**
	 * Recursively divides list in half
	 * @param array
	 */
	private void sortM(E[] array){


		if (array.length == 1) 
			return;  // the in list is already sorted in this case
		// divide
		E[] arrayl = (E[])new Comparable[array.length/2]; 
		E[] arrayr = (E[])new Comparable[array.length - array.length/2]; 

		for(int i = 0; i < arrayl.length ; i ++) 
			arrayl[i] = array[i]; // move the first n/2 elements to arrayl

		int j = 0;
		for ( int i = arrayl.length; i < array.length; i ++ )
		{
			arrayr[j] = array[i]; // move the rest to arrayr
			j++;
		}
		// recur
		sortM(arrayl);
		sortM(arrayr);
		//conquer
		merge(arrayl,arrayr,array);

	}


	/**
	 * Merges two sorted lists, aL and aR, into a sorted list in.
	 */
	private void merge(E[] al, E[] ar, E[] array) {

		//initialize pointer variables
		int pointl = 0;
		int pointr = 0;
		int pointArray = 0;

		while (pointl < al.length && pointr <ar.length){//while both al and ar have 

			if (al[pointl].compareTo(ar[pointr]) >= 0)//check for larger element
			{
				//if the element in ar is larger,
				array[pointArray] = ar[pointr]; //add to ar at the index pointr
				pointr++;

			}
			else
			{
				//if the element in al is larger,
				array[pointArray] = al[pointl]; // add to ar at the index pointl
				pointl++;

			}
			pointArray++;
		}
		while(pointl < al.length) // move the remaining elements of aL
		{
			array[pointArray] = al[pointl];
			pointArray++;
			pointl++;
		}	
		while(pointr<ar.length) // move the remaining elements of aR
		{
			array[pointArray] = ar[pointr];
			pointr++;
			pointArray++;
		}			

	}

}



