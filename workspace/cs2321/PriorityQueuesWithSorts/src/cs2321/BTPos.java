package cs2321;

import net.datastructures.Position;
/**
 * A simple position node for a binary tree (namely Complete tree)
 * @author Brent Nix
 *
 * @param <E> element
 */
@SpaceComplexity("O(1)")
public class BTPos<E> implements Position<E> {

	//declare variables 
	E element;
	int index;
	//constructor
	public BTPos(E e, int i)
	{
		element = e;
		index = i;
			
	}
	@Override
	@TimeComplexity("O(1)")
	public E element()
	{
		//TCJ: the cost does not vary with input size so it is constant
		return element;
	}
	/**returns the index of the given BTPos
	 * 
	 * @return index
	 */
	@TimeComplexity("O(1)")
	public int index()
	{
		//TCJ: the cost does not vary with input size so it is constant
		return index;
	}
	/**
	 * Sets the current to the given element and returns the element that occupied the position before
	 * @param eT element to set to
	 * @return
	 */
	@TimeComplexity("O(1)")
	public E setElement(E eT)
	{
		//TCJ: the cost does not vary with input size so it is constant
		E temp = element;
		element = eT;
		return temp;
	}

}
