package cs2321;

import net.datastructures.Position;

/**
 * A generic position class to be stored in an ArraySequence.
 * Course: CS2321 Section R01
 * Assignment: #3
 * @author Chris Rickerd
 *
 * @param <E> The generic type to be stored in the ArraySequencePosition
 */
public class ArraySequencePosition<E> implements Position<E> {

	private E element;
	private int index;

	public ArraySequencePosition(E initialElement)
	{
		element = initialElement;
	}

	public ArraySequencePosition(E initialElement, int index)
	{

		element = initialElement;
		this.index = index;
	}

	public ArraySequencePosition()
	{
		element = null;
	}

	public void set(E a)
	{
		element = a;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int newIndex)
	{
		index = newIndex;
	}

	public E element()
	{
		return element;
	}

}
