package cs2321;

import java.util.Iterator;

import net.datastructures.Position;

/**
 * A generic iterator to be used an an ArraySequence
 * 
 * Course: CS2321 Section R01
 * Assignment: #1
 * @author Chris Rickerd
 *
 * @param <E> The generic type to be stored in the ArraySequencePosition
 */
public class ArrayIterator<E> implements Iterator<E> {

	protected ArraySequence<E> list;
	protected Position<E> cursor;

	private static ArraySequence<Integer>[] lists;
	private static long startTime;
	private static long stopTime;
	private static long averageTime;

	public static void main(String[] args){

		int tests = 5000;
		int size = 100;
		lists = (ArraySequence<Integer>[])new ArraySequence[tests];
		
		for (int k = 0; k < tests; k++){
			lists[k] = new ArraySequence<Integer>();
		}

		for (int n = 0; n < size; n++){
			
			startTime = System.nanoTime();
			for (int i = 0; i < tests; i++){

				lists[i].add(0, i);

			}
			stopTime = System.nanoTime();
			System.out.println((stopTime-startTime)/tests);
		}


	}

	public ArrayIterator(ArraySequence<E> A)
	{
		list = A;

	}

	public boolean hasNext() {

		return (list.next(cursor) != null);
	}

	public E next() {

		return list.next(cursor).element();
	}

	public void remove() {

		//DO NOT PUT ANYTHING HERE
	}

}
