package cs2321.sorting;
import java.util.Random;

/**
 * A test driver for Sorts.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #5
 * @author Chris Brown (cdbrown@mtu.edu)
 */

public class SortTiming {

	public static void main(String [] args){

//						for( int j = 0 ; j < 4 ; j ++){
		{int j = 3;
		System.out.println("Test  " + (j+1));
		Integer[] nArray = {10, 25, 50, 100, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000};
//		Integer[] nArray = {10};
		for( int n = 0 ; n < nArray.length ; n ++ )//iterate through sizes
		{
			Integer[] array1 = new Integer[nArray[n]];//create new array using size
			
			for( int i = 0 ; i < array1.length ; i ++ )//fill array with randomly generated elements
			{
				if(j == 0)
				{Random g = new Random();
				array1[i] = g.nextInt(100000);}
				else if(j == 1 || j == 3)
					array1[i] = i;

				else if(j == 2)
					array1[i] = nArray[n]-i;
			}
			if (j == 3)
			{
				Random random = new Random();
				int m = array1.length;

				for(int i=0; i < n / 10; i++)
				{
					int p1 = random.nextInt(m);
					int p2 = random.nextInt(m);
					
					int temp = array1[p1];
					array1[p1] = array1[p2];
					array1[p2] = temp;
				}
			}
			//			double inplaceHeapsort = testSort(array1, new InsertionSort<Integer>());

			//test algorithms 
			double orderedPQTime = testSort(array1, new InsertionSort<Integer>());
			double unorderedPQTime = testSort(array1, new SelectionSort<Integer>());
			double heapTime = testSort(array1, new HeapSort<Integer>());
			double mergeTime = testSort(array1, new MergeSort<Integer>());


			System.out.println(nArray[n] + "," + unorderedPQTime + "," + orderedPQTime + "," + heapTime + "," + mergeTime);

		}


		}



	}

	/**
	 * Algorithm: testSort
	 * @param arr - an array of Integers to use for empirical measurement of a sort
	 * @param sortClass - the Class representing the sorting algorithm to be run
	 * @param iterations - the number of times the sort is repeated
	 * @return average time taken for a single execution of a sort (in nanoseconds)
	 * 
	 * A copy (clone) of the array is made to test over, so that the original may be reused.
	 */
	public static double testSort(Integer[] arr, Sorter<Integer> sortClass){
		long startTime = 0, endTime = 0;
		int samples = 0;

		System.gc();
		startTime = System.nanoTime();
		//#repeated measurements (no less than .5 seconds worth of repeats)
		do{
			//create a copy of the array for each test case
			Integer[] testCase = arr.clone();
			//the sorting algorithm, based on the Sorter Class
			sortClass.sort(testCase);

			samples++;
			endTime = System.nanoTime();
		}while(endTime - startTime < 500000000);

		return (double)(endTime - startTime) / samples;
	}

}
