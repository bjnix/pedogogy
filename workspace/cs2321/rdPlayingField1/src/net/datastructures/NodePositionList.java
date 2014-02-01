package net.datastructures;

import java.util.Iterator;

import cs2321.SpaceComplexity;
import cs2321.TimeComplexity;



/**
 * Realization of a PositionList using a doubly-linked list of nodes.
 * 
 * @author Michael Goodrich, Natasha Gelfand, Roberto Tamassia, Eric Zamore
 * @author Modified by Dustin Larson
 */
@SpaceComplexity("O(n)")
public class NodePositionList<E> implements PositionList<E>
{
    protected int numElts; // Number of elements in the list
    
    // SCJ: A list with n elements contains n DNodes to store the data
    protected DNode<E> header, trailer; // Special sentinels

    /** 
     * Constructor that creates an empty list
     */
    @TimeComplexity("O(1)")
    public NodePositionList()
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        numElts = 0;
        header = new DNode<E>( null, null, null ); // create header
        trailer = new DNode<E>( header, null, null ); // create trailer
        header.setNext( trailer ); // make header and trailer point to each other
    }

    /**
     * Checks if position is valid for this list and converts it to DNode if it
     * is valid
     *
     * @param p The position to check for validity on this list
     * @return A valid position converted into a DNode
     * @throws InvalidPositionException if the position is invalid for this list
     */
    @TimeComplexity("O(1)")
    protected DNode<E> checkPosition( Position<E> p )
            throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        if ( p == null )
            throw new InvalidPositionException(
                    "Null position passed to NodeList" );
        if ( p == header )
            throw new InvalidPositionException(
                    "The header node is not a valid position" );
        if ( p == trailer )
            throw new InvalidPositionException(
                    "The trailer node is not a valid position" );
        try
        {
            DNode<E> temp = ( DNode<E> )p;
            if ( ( temp.getPrev() == null ) || ( temp.getNext() == null ) )
                throw new InvalidPositionException(
                        "Position does not belong to a valid NodeList" );
            return temp;
        }
        catch ( ClassCastException e )
        {
            throw new InvalidPositionException(
                    "Position is of wrong type for this list" );
        }
    }

    /**
     * Returns the number of elements in the list
     * 
     * @return The number of elements in this list
     * 
     * @see net.datastructures.PositionList#size()
     */
    @TimeComplexity("O(1)")
    public int size()
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        return numElts;
    }

    /**
     * Returns whether the list is empty
     * 
     * @return True if the list is empty; False otherwise
     * 
     * @see net.datastructures.PositionList#isEmpty()
     */
    @TimeComplexity("O(1)")
    public boolean isEmpty()
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        return ( numElts == 0 );
    }

    /**
     * Returns the first position in the list
     * 
     * @return The first node in the list
     * 
     * @see net.datastructures.PositionList#first()
     */
    @TimeComplexity("O(1)")
    public Position<E> first() throws EmptyListException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        if ( isEmpty() )
            throw new EmptyListException( "List is empty" );
        return header.getNext();
    }

    /**
     * Returns the last position in the list
     * 
     * @return The last node in the list
     *
     * @see net.datastructures.PositionList#last()
     */
    @TimeComplexity("O(1)")
    public Position<E> last() throws EmptyListException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        if ( isEmpty() )
            throw new EmptyListException( "List is empty" );
        return trailer.getPrev();
    }

    /** 
     * Returns the position before the given one
     * 
     * @param p The position to get the previous node from
     * 
     * @return The node before the given node in the list
     * 
     * @throws InvalidPositionException if the position given is invalid
     * @throws BoundaryViolationException if the position given is outside the range of positions
     * 
     * @see net.datastructures.PositionList#prev(net.datastructures.Position)
     */
    @TimeComplexity("O(1)")
    public Position<E> prev( Position<E> p ) throws InvalidPositionException,
            BoundaryViolationException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        DNode<E> prev = v.getPrev();
        if ( prev == header )
            throw new BoundaryViolationException(
                    "Cannot advance past the beginning of the list" );
        return prev;
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
     * 
     * @see net.datastructures.PositionList#next(net.datastructures.Position)
     */
    @TimeComplexity("O(1)")
    public Position<E> next( Position<E> p ) throws InvalidPositionException,
            BoundaryViolationException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        DNode<E> next = v.getNext();
        if ( next == trailer )
            throw new BoundaryViolationException(
                    "Cannot advance past the end of the list" );
        return next;
    }

    /**
     * Insert the given element before the given position
     * 
     * @param p The position to add the element
     * @param element The element to add
     * 
     * @throws InvalidPositionException if the position given is invalid
     * 
     * @see net.datastructures.PositionList#addBefore(net.datastructures.Position, java.lang.Object)
     */
    @TimeComplexity("O(1)")
    public void addBefore( Position<E> p, E element )
            throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        numElts++;
        DNode<E> newNode = new DNode<E>( v.getPrev(), v, element );
        v.getPrev().setNext( newNode );
        v.setPrev( newNode );
    }

    /**
     * Insert the given element after the given position
     * 
     * @param p The position to add the element
     * @param element The element to add
     * 
     * @throws InvalidPositionException if the position given is invalid
     *
     * @see net.datastructures.PositionList#addAfter(net.datastructures.Position, java.lang.Object)
     */
    @TimeComplexity("O(1)")
    public void addAfter( Position<E> p, E element )
            throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        numElts++;
        DNode<E> newNode = new DNode<E>( v, v.getNext(), element );
        v.getNext().setPrev( newNode );
        v.setNext( newNode );
    }

    /**
     * Insert the given element at the beginning of the list, returning the new
     * position
     * 
     * @param element The element to add
     * 
     * @see net.datastructures.PositionList#addFirst(java.lang.Object)
     */
    @TimeComplexity("O(1)")
    public void addFirst( E element )
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        numElts++;
        DNode<E> newNode = new DNode<E>( header, header.getNext(), element );
        header.getNext().setPrev( newNode );
        header.setNext( newNode );
    }

    /**
     * Insert the given element at the end of the list, returning the new
     * position
     * 
     * @param element The element to add
     * 
     * @see net.datastructures.PositionList#addLast(java.lang.Object)
     */
    @TimeComplexity("O(1)")
    public void addLast( E element )
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        numElts++;
        DNode<E> oldLast = trailer.getPrev();
        DNode<E> newNode = new DNode<E>( oldLast, trailer, element );
        oldLast.setNext( newNode );
        trailer.setPrev( newNode );
    }

    /**
     * Remove the given position from the list
     * 
     * @param p The position to remove from the list
     * 
     * @return The element removed from the list
     * 
     * @throws InvalidPositionException if the position given is invalid
     *
     * @see net.datastructures.PositionList#remove(net.datastructures.Position)
     */
    @TimeComplexity("O(1)")
    public E remove( Position<E> p ) throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        numElts--;
        DNode<E> vPrev = v.getPrev();
        DNode<E> vNext = v.getNext();
        vPrev.setNext( vNext );
        vNext.setPrev( vPrev );
        E vElem = v.element();
        // unlink the position from the list and make it invalid
        v.setNext( null );
        v.setPrev( null );
        return vElem;
    }

    /**
     * Replace the element at the given position with the new element and return
     * the old element
     * 
     * @param p The position to replace the stored element
     * @param element The new element that will replace the old element
     * 
     * @return The old element from the list at the given position
     * 
     * @throws InvalidPositionException if the position given is invalid
     *
     * @see net.datastructures.PositionList#set(net.datastructures.Position, java.lang.Object)
     */
    @TimeComplexity("O(1)")
    public E set( Position<E> p, E element ) throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        E oldElt = v.element();
        v.setElement( element );
        return oldElt;
    }

    /**
     * Returns an iterator of all the elements in the list.
     * 
     * @return an iterator of all the elements in the list 
     */
    @TimeComplexity("O(1)")
    public Iterator<E> iterator()
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        return new ElementIterator<E>( this );
    }

    /**
     * Returns an iterable collection of all the nodes in the list.
     * 
     * @return An iterable collection of all the nodes in the list
     */
    @TimeComplexity("O(n)")
    @SpaceComplexity("O(n)")
    public Iterable<Position<E>> positions()
    {
        // create a list of positions
        PositionList<Position<E>> P = new NodePositionList<Position<E>>();
        if ( !isEmpty() )
        {
            // TCJ: A list with n positions will cause this loop to
            // iterate n times, thus it has a time complexity of O(n)
            // TSJ: On a list with n elements, the iterable position list
            //      returned will be size n
            Position<E> p = first();
            while ( true )
            {
                P.addLast( p ); // add position p as the last element of list P
                if ( p == last() )
                    break;
                p = next( p );
            }
        }
        return P; // return P as our Iterable object
    }

    // Convenience methods
    /** Returns whether a position is the first one
     * 
     * @param p The position to check if it is the first in the list
     * 
     * @return True if the position given is the first in the list; False otherwise
     * 
     * @throws InvalidPositionException
     */
    @TimeComplexity("O(1)")
    public boolean isFirst( Position<E> p ) throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        return v.getPrev() == header;
    }

    /**
     * Returns whether a position is the last one
     * 
     * @param p The position to check if it is the last in the list
     * 
     * @return True if the position given is the last in the list; False otherwise
     * 
     * @throws InvalidPositionException
     */
    @TimeComplexity("O(1)")
    public boolean isLast( Position<E> p ) throws InvalidPositionException
    {
        // TCJ: The time complexity does not vary with input size,
        // thus the running time is constant.
        DNode<E> v = checkPosition( p );
        return v.getNext() == trailer;
    }

    /**
     * Returns a textual representation of a given node list using for-each
     * 
     * @param <E> The data type stored by the node
     * @param L The node list
     * 
     * @return A textual representation of the given node list
     */
    @TimeComplexity("O(n)")
    @SpaceComplexity("O(n)")
    public static <E> String forEachToString( PositionList<E> L )
    {
        String s = "[";
        int i = L.size();
        // TCJ: On a list with n elements, this loop will run n times
        // TSJ: On a list with n elements, the string constructed will
        //      contain the data from all n elements
        for ( E elem : L )
        {
            s += elem; // implicit cast of the element to String
            i--;
            if ( i > 0 )
                s += ", "; // separate elements with a comma
        }
        s += "]";
        return s;
    }

    /**
     * Returns a textual representation of a given node list
     * 
     * @param <E> The data type stored by the node
     * @param l The node list
     * 
     * @return A textual representation of the given node list
     */
    @TimeComplexity("O(n)")
    @SpaceComplexity("O(n)")
    public static <E> String toString( PositionList<E> l )
    {
        Iterator<E> it = l.iterator();
        String s = "[";
        // TCJ: On a list with n elements, this loop will run n times
        // TSJ: On a list with n elements, the string constructed will
        //      contain the data from all n elements
        while ( it.hasNext() )
        {
            s += it.next(); // implicit cast of the next element to String
            if ( it.hasNext() )
                s += ", ";
        }
        s += "]";
        return s;
    }

    /** 
     * Returns a textual representation of the list
     * 
     * @return A textual representation of the list
     * 
     * @see java.lang.Object#toString()
     */
    @TimeComplexity("O(n)")
    @SpaceComplexity("O(n)")
    public String toString()
    {
        //TCJ: The time complexity for the toString function
        //is O(n), thus the time complexity for the overall
        //function is O(n)
        // TSJ: On a list with n elements, the string constructed will
        //      contain the data from all n elements
        return toString( this );
    }
}