package cs2321;
import net.datastructures.*;
import cs2321.*;

/**
 * A test driver for the three PriorityQueues.
 * 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @author Chris Brown (cdbrown@mtu.edu)
 */
public class TestingPQ {
	/**
	 * Simple test driver. For Assignment 5, it will need
	 * to be changed to include the PQSort algorithm.
	 * 
	 * @param args unused
	 */
	public static void main(String [] args) {
		PriorityQueue<Integer,String> unordpq = new UnorderedPQ<Integer,String>();
		PriorityQueue<String, Integer> ordpq = new OrderedPQ<String, Integer>();
		PriorityQueue<Double, Integer> heap = new Heap<Double, Integer>();
		Entry e;
		
		/*
		 * Unordered Priority Queue tests
		 */

		//*# Remove the first '/' in this comment to comment out the unordered priority queue tests
		unordpq.insert(16, "Bulbous Bouffant");
		unordpq.insert(6, "Gazebo");
		unordpq.insert(7, "Balooga");
		unordpq.insert(8, "Galoshes");
		unordpq.insert(6, "Eskimo");
		unordpq.insert(7, "Mukluks");
		unordpq.insert(9, "Macadamia");

		e = unordpq.min();
		System.out.println("\"min()\" - Key: " + e.getKey());
		
		while(!unordpq.isEmpty()){
			e = unordpq.removeMin();
			System.out.println("\"removeMin()\" - Key: " + e.getKey() + ", Value: " + e.getValue());
		}
		//*# ending comment */
		
		/*
		 * Ordered Priority Queue tests
		 */
		
		//*# Remove the first '/' in this comment to comment out the ordered priority queue tests
		ordpq.insert("Bulbous Bouffant", 16);
		ordpq.insert("Gazebo", 6);
		ordpq.insert("Balooga", 7);
		ordpq.insert("Banter", 4);
		ordpq.insert("Galoshes", 8);
		ordpq.insert("Eskimo", 6);
		ordpq.insert("Mukluks", 7);
		ordpq.insert("Macadamia", 9);

		e = ordpq.min();
		System.out.println("\"min()\" - Key: " + e.getKey());
		
		while(!ordpq.isEmpty()){
			e = ordpq.removeMin();
			System.out.println("\"removeMin()\" - Key: " + e.getKey() + ", Value: " + e.getValue());
		}
		//*# ending comment */

		/*
		 * Heap tests
		 */

		//*# Remove the first '/' in this comment to comment out the heap tests
		System.out.println(heap.isEmpty());
		heap.insert(321.2, 977);
		System.out.println(" - " + heap.size());
		heap.insert(779.59817432, 624);
		System.out.println(" - " + heap.size());
		heap.insert(818.728, 50);
		System.out.println(" - " + heap.size());
		heap.insert(917.596352, 216);
		System.out.println(" - " + heap.size());
		heap.insert(430.0, 547);
		System.out.println(" - " + heap.size());
		heap.insert(197.6649, 38);
		System.out.println(" - " + heap.size());
		heap.insert(50.598212865, 965);
		System.out.println(" - " + heap.size());
		System.out.println(heap.isEmpty());
		e = heap.min();
		System.out.println("\"min()\" - Key: " + e.getKey());
		
		while(!heap.isEmpty()){
			e = heap.removeMin();
			
			System.out.println("\"removeMin()\" - Key: " + e.getKey() + ", Value: " + e.getValue());
		}
		//*# ending comment */

	}	
} // End Main
 