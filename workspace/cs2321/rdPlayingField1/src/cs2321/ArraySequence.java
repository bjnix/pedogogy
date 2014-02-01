package cs2321;

import java.util.Iterator;
import net.datastructures.*;
import net.datastructures.IndexOutOfBoundsException;

/**
 * A sequence that is implemented as an array.
 * If the capacity is exceeded the capacity will be doubled. 
 * 
 * The following functions are O(n) time complexity:
 * 	add, addAfter, addBefore, remove (both).
 * 
 * 
 * The following functions are O(1) time complexity:
 * 	atIndex, indexOf, isEmpty, size, get, set (both), first, next, last, prev.
 * 
 * 
 * Course: CS2321 Section R01
 * Assignment: #1
 * @author Chris Rickerd
 * 
 */
//SCJ: A one-dimensional array of n elements takes up O(n) space.
@SpaceComplexity("O(n)")
public class ArraySequence<E> implements Sequence<E>
{
	private int mCurrentSize;
	private int mMaxSize;
	private ArraySequencePosition<E>[] mArray;


	public ArraySequence()
	{
		mCurrentSize = 0;
		mMaxSize = 10;
		mArray = (ArraySequencePosition<E>[])new ArraySequencePosition[mMaxSize];
	}

	/**
	 * Returns the position containing the element at the given index
	 * 
	 * @param aIndex The index of the element to retrieve
	 * 
	 * @return the position containing the element at the given index
	 * 
	 * @throws BoundaryViolationException
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> atIndex( int aIndex ) throws BoundaryViolationException
	{
		//TCJ: This method returns a variable and does not depend on input size.
		if (aIndex < 0 || aIndex > this.size())
		{
			throw new BoundaryViolationException("Index is invalid");
		}

		return mArray[aIndex];
	}

	/**
	 * Returns the index of the element stored at the given position.
	 * 
	 * @param aPosition The position of the element to get the index of
	 * 
	 * @return The index of the element at the given position
	 * 
	 * @throws InvalidPositionException
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public int indexOf( Position<E> aPosition ) throws InvalidPositionException
	{
		//TCJ: This method calls a method which returns a variable and does not depend on input size.
		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		else
			return ((ArraySequencePosition<E>)aPosition).getIndex();
	}

	/**
	 * Returns whether the list is empty
	 * 
	 * @return True if the list is empty; False otherwise
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public boolean isEmpty()
	{

		//TCJ: This method returns an expression and does not depend on input size.
		return (mCurrentSize == 0);
	}

	/**
	 * Returns the number of elements in the list
	 * 
	 * @return The number of elements in this list
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public int size()
	{
		//TCJ: This method returns a variable and does not depend on input size.
		return mCurrentSize;
	}

	/**
	 * Inserts an element aElement to be at index aIndex, shifting all elements after this
	 * 
	 * @param aIndex The index to insert the element at
	 * @param aElement The element to insert at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	@SpaceComplexity("O(n)")
	public void add( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{

		//TCJ: This loop searches through the entire array in worst case, and only executes once in best case.
		//SCJ: A new array is created every time


		//If the array size needs to be doubled
		if (mCurrentSize == mMaxSize)
		{
			mMaxSize*=2;
			ArraySequencePosition<E>[] newArray = (ArraySequencePosition<E>[])new ArraySequencePosition[mMaxSize];

			int j = 0;
			for (int i = 0; i < mCurrentSize+1; i++)
			{
				//If inserting the new element
				if (i == aIndex){
					
					newArray[i] = new ArraySequencePosition<E>(aElement, aIndex);
				}
				else{
					
					newArray[i] = mArray[j];
					newArray[i].setIndex(i);
					j++;
				}
			}
			mArray = newArray;
		}
		else
		{
			for (int i = mCurrentSize; i > aIndex; i--)
			{
				mArray[i] = mArray[i-1];
				mArray[i].setIndex(i);
			}

			mArray[aIndex] = new ArraySequencePosition<E>(aElement,aIndex);
		}

		mCurrentSize++;

	}

	/**
	 * Returns the element at index aIndex without removing it
	 * 
	 * @param aIndex The index to get the element from
	 * 
	 * @return The element at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public E get( int aIndex ) throws IndexOutOfBoundsException
	{

		if (aIndex < 0 || aIndex >= mCurrentSize || isEmpty())
			throw new IndexOutOfBoundsException("Index is invalid");

		//TCJ: This method returns a variable and does not depend on input size.
		return mArray[aIndex].element();
	}

	/**
	 * Removes and returns the element at index aIndex, shifting the elements after this.
	 * 
	 * @param aIndex The index to remove the element at
	 * 
	 * @return The element removed from the list
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	public E remove( int aIndex ) throws IndexOutOfBoundsException
	{
		if (aIndex < 0 || aIndex >= mCurrentSize || isEmpty())
			throw new IndexOutOfBoundsException("Index is invalid");

		E temp = mArray[aIndex].element();

		//TCJ: This loop searches through the entire array in worst case, and is only executed once in best case.
		for (int i = aIndex+1; i < mCurrentSize; i++)
		{
			mArray[i-1] = mArray[i];

			if (mArray[i-1] != null)
				mArray[i-1].setIndex(i-1);
		}

		mCurrentSize--;
		return temp;
	}

	/**
	 * Replaces the element at index aIndex with aElement, returning the previous element at aIndex.
	 * 
	 * @param aIndex The index to replace the element at
	 * @param aElement The element to replace the element at the given index
	 * 
	 * @return The element that was replaced at the given index
	 * 
	 * @throws IndexOutOfBoundsException if the given index is invalid
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public E set( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{

		if (aIndex < 0 || aIndex >= mCurrentSize || isEmpty())
			throw new IndexOutOfBoundsException("Index is invalid");

		//TCJ: This method calls a method that sets a variable and does not depend on input size.
		E temp = mArray[aIndex].element();
		mArray[aIndex].set(aElement);
		return temp;
	}

	/**
	 * Adds an element after a given position and shifts all values afterward
	 * 
	 * @param aPosition the position after which to add the element
	 * @param aElementToAdd the element to add
	 * 
	 * @throws InvalidPositionException if the given position is invalid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	public void addAfter( Position<E> aPosition, E aElementToAdd )
	throws InvalidPositionException
	{

		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		//TCJ: This method calls the add method, which has O(n) time complexity.
		add(indexOf(aPosition)+1, aElementToAdd);
	}

	/**
	 * Adds an element before a given position and shifts all values afterward
	 * 
	 * @param aPosition the position before which to add the element
	 * @param aElementToAdd the element to add
	 * 
	 * @throws InvalidPositionException if the given position is invalid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	public void addBefore( Position<E> aPosition, E aElementToAdd )
	throws InvalidPositionException
	{

		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		//TCJ: This method calls the add method, which has O(n) time complexity.
		add(indexOf(aPosition),aElementToAdd);
	}

	/**
	 * Returns the first element of the sequence
	 * 
	 * @return The first element of the sequence
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> first()
	{

		//TCJ: This method returns a variable and does not depend on input size.
		return mArray[0];
	}

	/**
	 * Returns the last element of the sequence
	 * 
	 * @return the last element of the sequence
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> last()
	{

		//TCJ: This method returns a variable and does not depend on input size.
		return mArray[mCurrentSize-1];
	}

	/**
	 * Returns the position after the given one 
	 * 
	 * @param p The position to get the next node from
	 * 
	 * @return The node after the given node in the list
	 * 
	 * @throws InvalidPositionException if the position given is invalid
	 * @throws BoundaryViolationException if the position given is outside the range of positions
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> next( Position<E> aPosition )
	throws InvalidPositionException, BoundaryViolationException
	{

		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		if (indexOf(aPosition) >= mCurrentSize)
			throw new BoundaryViolationException("Position is invalid");

		//TCJ: This method calls a method that returns a variable and does not depend on input size.
		return mArray[indexOf(aPosition)+1];
	}

	/**
	 * Returns the position after the given one 
	 * 
	 * @param p The position to get the next node from
	 * 
	 * @return The node after the given node in the list
	 * 
	 * @throws InvalidPositionException if the position given is invalid
	 * @throws BoundaryViolationException if the position given is outside the range of positions
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public Position<E> prev( Position<E> aPosition )
	throws InvalidPositionException, BoundaryViolationException
	{

		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		int index = indexOf(aPosition);

		//TCJ: This method returns a variable and does not depend on input size.
		if (index <= 0)
		{
			throw new BoundaryViolationException("Invalid position");
		}

		return mArray[index-1];
	}

	/**
	 * Removes and returns the element at position aPosition, shifting the elements after this.
	 * 
	 * @param aPosition The position containing the element to be removed
	 * 
	 * @return The element removed from the list
	 * 
	 * @throws InvalidPositionException if the given position is invalid
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	public E remove( Position<E> aPosition ) throws InvalidPositionException
	{

		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		int index = indexOf(aPosition);
		aPosition = null;

		if (index < 0 || index >= mCurrentSize || isEmpty())
			throw new IndexOutOfBoundsException("Index is invalid");

		//TCJ: This method calls the remove method, which has O(n) time complexity.
		E temp = mArray[index].element();
		remove(index);

		return temp;
	}

	/**
	 * Replaces the element at position aPosition with aElement, returning the previous element at aPosition.
	 * 
	 * @param aPosition The position whose element is to be replaced
	 * @param aElement The element to replace the element at the given position
	 * 
	 * @return The element that was replaced at the given position
	 * 
	 * @throws InvalidPositionException if the given position is invalid
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	public E set( Position<E> aPosition, E aElement )
	throws InvalidPositionException
	{
		if (aPosition == null)
			throw new InvalidPositionException("Position is invalid");

		E prev = aPosition.element();

		//TCJ: This method calls a method that changes one variable and does not depend on input size.
		((ArraySequencePosition<E>) aPosition).set(aElement);

		return prev;
	}

	/**
	 * Returns an iterator for this sequence.
	 * 
	 * @return Returns an iterator for this sequence
	 */
	public Iterator<E> iterator()
	{

		return new ArrayIterator<E>(this);
		
	}

	public void addFirst( E aElementToAdd )
	{
		add(0, aElementToAdd);
	}

	public void addLast( E aElementToAdd )
	{
		add(mCurrentSize, aElementToAdd);
	}

	@Override
	public E getFirst() throws EmptyDequeException
	{
		return mArray[0].element();
	}

	@Override
	public E getLast() throws EmptyDequeException
	{
		return mArray[mCurrentSize-1].element();
	}

	@Override
	public E removeFirst() throws EmptyDequeException
	{
		return remove(0);
	}

	@Override
	public E removeLast() throws EmptyDequeException
	{
		return remove(mCurrentSize-1);
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

}
