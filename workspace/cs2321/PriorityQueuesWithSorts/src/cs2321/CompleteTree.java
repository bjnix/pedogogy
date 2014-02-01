package cs2321;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.util.Arrays;
import net.datastructures.BinaryTree;
import net.datastructures.BoundaryViolationException;
import net.datastructures.EmptyTreeException;
import net.datastructures.Entry;
import net.datastructures.InvalidPositionException;
import net.datastructures.Position;







public class CompleteTree<E> implements BinaryTree<E> {

	protected ArraySequence<Position<E>> ArrSeq;


	//constructor
	public CompleteTree()
	{
		ArrSeq = new ArraySequence<Position<E>>();
		ArrSeq.add(0, null);//location 0 is left empty


	}

	/**
	 * Adds an element to the tree in the "last" position.
	 * This is the lowest level, justified to the left
	 * 
	 * @param e the element to be added to the tree
	 * @return the position of the newly added element
	 * @throws InvalidPositionException
	 */
	@TimeComplexityExpected("O(1)")
	public Position<E> addLast(E e) {
		//TCJ: time complexity does not vary with size - it is constant
		int i = size()+1;
		BTPos<E> p = new BTPos<E>(e, i);
		ArrSeq .add(i, p);
		return p;
	}

	/**
	 * Removes an element from the "last" position of the tree.
	 * This is the lowest level, rightmost element.
	 * 
	 * @return
	 * @throws InvalidPositionException
	 */
	@TimeComplexity("O(1)")
	public E removeLast() throws EmptyTreeException {

		//TCJ: because it always removes from the end, time cost is constant
		if(isEmpty()) throw new EmptyTreeException("Tree is empty");
		
		E temp = ArrSeq.getLast().element();
		ArrSeq.removeLast();
		return temp;

	}

	/**
	 * Swaps the values contained in these two positions.
	 * 
	 * @param e the element to be added to the tree
	 * @throws InvalidPositionException
	 */
	@TimeComplexity("O(1)")
	public void swapElements(Position<E> p1, Position<E> p2) {
		//TCJ: time complexity does not vary with size - it is constant
		E temp = p1.element();//create temporary element and map the element of the first position to it
		this.replace(p1, p2.element());//replace the element in p1 with p2
		this.replace(p2,temp );//map the element of temp to p2 
	}

	/* (non-Javadoc)
	 * @see net.datastructures.BinaryTree#hasLeft(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant
		BTPos<E> vv = checkPosition(v);
		return 2*vv.index()<=size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.BinaryTree#hasRight(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant
		BTPos<E> vv = checkPosition(v);
		return 2*vv.index() + 1 <= size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.BinaryTree#left(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public Position<E> left(Position<E> v) 
	throws InvalidPositionException, BoundaryViolationException 
	{
		//TCJ: time complexity does not vary with size - it is constant
		if(!hasLeft(v)) throw new BoundaryViolationException("Tree is empty");//if there is no left throw exception
		BTPos<E> vv = checkPosition(v);
		return ArrSeq.get(2*vv.index());
	}

	/* (non-Javadoc)
	 * @see net.datastructures.BinaryTree#right(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public Position<E> right(Position<E> v) 
	throws InvalidPositionException, BoundaryViolationException
	{
		//TCJ: time complexity does not vary with size - it is constant
		if(!hasRight(v)) throw new BoundaryViolationException("Tree is empty");
		BTPos<E> vv = checkPosition(v);
		return ArrSeq.get(2*vv.index() + 1);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#children(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public Iterable<Position<E>> children(Position<E> v)
	throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant
		ArraySequence<Position<E>> list = new ArraySequence<Position<E>>();		
		list.add(0,left(v));
		list.add(1,right(v));
		return list;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#isEmpty()
	 */
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		//TCJ: time complexity does not vary with size - it is constant
		return (size() == 0);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#isExternal(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant
		return !isInternal(v);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#isInternal(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant

		return hasLeft(v);//if v has a right child it will have a left child
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#isRoot(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant

		BTPos<E> vv = checkPosition(v);
		return vv.index() == 1;
	}

	/**
	 * Private method that checks the validity of a Position and castes it to a BTPos
	 * @param v - Position<E>
	 * @return BTPos<E>
	 * @throws InvalidPositionException
	 */
	@TimeComplexity("O(1)")
	private BTPos<E> checkPosition(Position<E> v) throws InvalidPositionException{
		//TCJ: time complexity does not vary with size - it is constant

		if(v == null || !(v instanceof BTPos))
			throw new InvalidPositionException("Position is invalid");

		return (BTPos<E>)v;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#iterator()
	 */
	@TimeComplexity("O(n)")
	public Iterator<E> iterator() {
		//TCJ: It iterates through the entire tree which is n elements - O(n)
		ArraySequence<E> list = new ArraySequence<E>();//create array
		Iterator<Position<E>> iter = ArrSeq.iterator();//create iterator	
		iter.next();//skip the first element
		while(iter.hasNext())
			list.addLast(iter.next().element());
		return list.iterator();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#parent(net.datastructures.Position)
	 */
	@TimeComplexity("O(1)")
	public Position<E> parent(Position<E> v) 
	throws InvalidPositionException,BoundaryViolationException 
	{
		//TCJ: time complexity does not vary with size - it is constant
		if(isRoot(v)) throw new BoundaryViolationException("No Parent");//if the checked element is root, throw exception
		BTPos<E> vv = checkPosition(v);
		return ArrSeq.get(vv.index()/2);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#positions()
	 */
	public Iterable<Position<E>> positions() {
		//Not required
		return null;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#replace(net.datastructures.Position, java.lang.Object)
	 */
	@TimeComplexity("O(n)")
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		//TCJ: time complexity does not vary with size - it is constant
		BTPos<E> vv = checkPosition(v);
		return vv.setElement(e);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#root()
	 */
	@TimeComplexity("O(1)")
	public Position<E> root() throws EmptyTreeException 
	{
		//TCJ: time complexity does not vary with size - it is constant
		if(isEmpty()) throw new EmptyTreeException("Tree is empty");
		return ArrSeq.get(1);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Tree#size()
	 */
	@TimeComplexity("O(1)")
	public int size() {
		//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.size()-1;
	}
	@TimeComplexity("O(1)")
	public Position<E> getLast() throws EmptyTreeException
	{		
		//TCJ: time complexity does not vary with size - it is constant

		if(isEmpty()) throw new EmptyTreeException("Tree is empty");
		return ArrSeq.getLast();
	}
	@TimeComplexity("O(1)")
	public String toString()
	{		
		//TCJ: time complexity does not vary with size - it is constant

		return ArrSeq.toString();
	}	

}
