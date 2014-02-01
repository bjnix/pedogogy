package net.datastructures;

/**
 * Interface for an PositionList: a collection of objects that are
 * inserted and removed at random.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 */
public interface PositionList<E> extends Iterable<E>
{
    /**
     * Returns the number of elements in this list.
     * 
     * @return The number of elements in this list
     */
    public int size();
    
    /**
     * Returns whether the list is empty.
     * 
     * @return True if the list is empty; False otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the first node in the list.
     * 
     * @return The first node in the list
     */
    public Position<E> first();
    
    /**
     * Returns the last node in the list.
     * 
     * @return The last node in the list
     */
    public Position<E> last();
    
    /**
     * Returns the node after a given node in the list.
     * 
     * @param aPosition The position to get the next node from
     * 
     * @return The node after the given node in the list
     * 
     * @throws InvalidPositionException if the position given is invalid
     * @throws BoundaryViolationException if the position given is outside the range of positions
     */
    public Position<E> next( Position<E> aPosition ) throws InvalidPositionException, BoundaryViolationException;
    
    /**
     * Returns the node before a given node in the list.
     * 
     * @param aPosition The position to get the previous node from
     * 
     * @return The node before the given node in the list
     * 
     * @throws InvalidPositionException if the position given is invalid
     * @throws BoundaryViolationException if the position given is outside the range of positions
     */
    public Position<E> prev( Position<E> aPosition ) throws InvalidPositionException, BoundaryViolationException;
    
    /**
     * Inserts an element at the front of the list, returning new position.
     * 
     * @param aElementToAdd The element to add
     */
    public void addFirst( E aElementToAdd );
    
    /**
     * Inserts an element after the given node in the list.
     * 
     * @param aElementToAdd The element to add
     */
    public void addLast( E aElementToAdd );
    
    /**
     * Inserts an element after the given node in the list.
     * 
     * @param aPosition The position to add the element
     * @param aElementToAdd The element to add
     * 
     * @throws InvalidPositionException if the position given is invalid
     */
    public void addAfter( Position<E> aPosition, E aElementToAdd ) throws InvalidPositionException;
    
    /**
     * Inserts an element before the given node in the list.
     * 
     * @param aPosition The position to add the element
     * @param aElementToAdd The element to add
     * 
     * @throws InvalidPositionException if the position given is invalid
     */
    public void addBefore( Position<E> aPosition, E aElementToAdd ) throws InvalidPositionException;
    
    /**
     * Removes a node from the list, returning the element stored there.
     * 
     * @param aPosition The position to remove from the list
     * 
     * @return The element removed from the list
     * 
     * @throws InvalidPositionException if the position given is invalid
     */
    public E remove( Position<E> aPosition ) throws InvalidPositionException;
    
    /**
     * Replaces the element stored at the given node, returning old element.
     * 
     * @param aPosition The position to replace the stored element
     * @param aElement The new element that will replace the old element
     * 
     * @return The old element from the list at the given position
     * 
     * @throws InvalidPositionException if the position given is invalid
     */
    public E set( Position<E> aPosition, E aElement ) throws InvalidPositionException;

}
