package net.datastructures;

/**
 * An interface for a sequence, a data structure supporting all
 * operations of a deque, indexed list and position list.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 * @author Modified by Dustin Larson
 */
public interface Sequence<E> extends Deque<E>, IndexList<E>, PositionList<E>, Iterable<E>
{
    /**
     * Returns the position containing the element at the given index.
     * 
     * @param aIndex The index of the element to retrieve
     * 
     * @return The position containing the element at the given index.
     * 
     * @throws BoundaryViolationException
     */
    public Position<E> atIndex( int aIndex ) throws BoundaryViolationException;
    
    /**
     * Returns the index of the element stored at the given position.
     * 
     * @param aPosition The position of the element to get the index of
     * 
     * @return The index of the element at the given position
     * 
     * @throws InvalidPositionException
     */
    public int indexOf( Position<E> aPosition ) throws InvalidPositionException;
}
