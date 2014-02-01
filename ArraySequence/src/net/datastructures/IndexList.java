package net.datastructures;

/**
 * Interface for an IndexList: a collection of objects that are
 * inserted and removed at random.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 */
public interface IndexList<E>
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
     * Inserts an element aElement to be at index aIndex, shifting all elements after this
     * 
     * @param aIndex The index to insert the element at
     * @param aElement The element to insert at the given index
     * 
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    public void add( int aIndex, E aElement ) throws IndexOutOfBoundsException;
    
    /**
     * Returns the element at index aIndex without removing it
     * 
     * @param aIndex The index to get the element from
     * 
     * @return The element at the given index
     * 
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    public E get( int aIndex ) throws IndexOutOfBoundsException;
    
    /**
     * Removes and returns the element at index aIndex, shifting the elements after this.
     * 
     * @param aIndex The index to remove the element at
     * 
     * @return The element removed from the list
     * 
     * @throws IndexOutOfBoundsException if the given index is invalid
     */
    public E remove( int aIndex ) throws IndexOutOfBoundsException;
    
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
    public E set( int aIndex, E aElement ) throws IndexOutOfBoundsException;
}
