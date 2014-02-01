package net.datastructures;

/**
 * Interface for a deque: a collection of objects that are inserted and removed
 * at both ends; a subset of java.util.LinkedList methods.
 * 
 * @author Roberto Tamassia
 * @author Michael Goodrich
 */
public interface Deque<E>
{
    /**
     * Returns the number of elements in this deque.
     * 
     * @return The number of elements in this deque
     */
    public int size();
    
    /**
     * Returns whether the deque is empty.
     * 
     * @return True if the deque is empty; False otherwise
     */
    public boolean isEmpty();

    /**
     * Returns the first element; an exception is thrown if deque is empty.
     * 
     * @return The first element in the deque
     * 
     * @throws EmptyDequeException if the deque is empty
     */
    public E getFirst() throws EmptyDequeException;
    
    /**
     * Returns the last element; an exception is thrown if deque is empty.
     * 
     * @return The last element in the deque
     * 
     * @throws EmptyDequeException if the deque is empty
     */
    public E getLast() throws EmptyDequeException;
    
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
     * Removes the first element; an exception is thrown if deque is empty.
     * 
     * @return The first element, which was just removed from the deque.
     * 
     * @throws EmptyDequeException if the deque is empty
     */
    public E removeFirst() throws EmptyDequeException;
    
    /**
     * Removes the last element; an exception is thrown if deque is empty.
     * 
     * @return The last element, which was just removed from the deque.
     * 
     * @throws EmptyDequeException if the deque is empty
     */
    public E removeLast() throws EmptyDequeException;
}
