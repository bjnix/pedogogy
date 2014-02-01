package cs2321;

import net.datastructures.Position;

public class ArrSeqPos<E> implements Position<E>{
	
	
	public ArrSeqPos(int Ind, E Elem){
		Index = Ind;
		Element = Elem;
		
	}
	private int Index;
	private E Element;
	
	@TimeComplexity("O(1)")
	@Override
	public E element() 
	{
		//TCJ: The cost does not vary with input size,
		//therefore we can say it is constant
		return Element;
	}
	
	/**
	 * Returns Index
	 * @return the index of the current position
	 */
	@TimeComplexity("O(1)")
	public int getIndex()
	{
		//TCJ: The cost does not vary with input size,
		//therefore we can say it is constant
		return Index;
	}
	/**
	 * Sets the value of Element
	 * @param e is the new element  
	 * @return Element
	 */
	@TimeComplexity("O(1)")
	public E setElement(E e)
	{
		//TCJ: The cost does not vary with input size,
		//therefore we can say it is constant
		return Element = e;
	}
	/**
	 * Sets the value of Index 
	 * @param I is the new index
	 * @return Index
	 */
	@TimeComplexity("O(1)")
	public int setIndex(int I)
	{
		//TCJ: The cost does not vary with input size,
		//therefore we can say it is constant
		return Index = I;
	}

}
