package cs2321;

import java.util.Iterator;
import net.datastructures.*;
import cs2321.*;



/**
 * A sequence that is implemented as an array.
 * If the capacity is exceeded the capacity will be doubled. 
 * 
 * SCJ: there are n elements in the array it is O(n) 
 * 
 * Course: CS2321 Section 01
 * Assignment: #1
 * @author Brent Nix
 */ 
@SpaceComplexity("O(n)")
public class ArraySequence<E> implements Sequence<E>
{
	private int mCurrentSize; //the current amount of positions occupied in the array 
	private int mMaxSize; //the current size of the array Sequence
	private ArrSeqPos<E>[] mArray; //the array that is being manipulated

	//constructor
	public ArraySequence()
	{
		//initialization of objects
		mCurrentSize = 0;
		mMaxSize = 10;
		mArray = (ArrSeqPos<E>[])new ArrSeqPos[mMaxSize];
	}



	/*# Complete the implementation of all required methods.
	 *  Be sure to update this file to include:
	 *      
	 *      Annotations (@TimeComplexity, @SpaceComplexity, @TimeComplexityAmmortized, @TimeComplexityExpected)
	 *      that indicate the time and space complexities.
	 *      
	 *      Appropriate comments justifying time and space claims (TCJ and SCJ) 
	 */
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public Position<E> atIndex( int aIndex ) throws BoundaryViolationException
	{
		//TCJ: The time cost does not vary with input size,
		//so Time Cost is constant.
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )//Throw exception if out of index is violating bounds
		{
			throw new BoundaryViolationException ( "Index is invalid" );
		}

		return mArray[aIndex];
	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public int indexOf( Position<E> aPosition ) throws InvalidPositionException
	{
		//TCJ: The time cost does not vary with input size.
		//Because the Position<E> is type-casted to an ArrSeqPos<E> we can use it's index 
		//to find it without iterating through the list


		if ((((ArrSeqPos<E>)aPosition).getIndex() > mCurrentSize ) )//throw exception if the position given is invalid
		{
			throw new InvalidPositionException( "Position is invalid" );
		}
		return ((ArrSeqPos<E>)aPosition).getIndex();
	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty()
	{
		//TCJ: because a global counter is kept, the size is always known,
		//and accessing this fact takes 1 step
		if(mCurrentSize == 0){
			return true;
		}
		else return false;
	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public int size()
	{
		//TCJ: because a global counter is kept the size is always known
		//and accessing this fact takes 1 step
		return mCurrentSize;
	}

	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	@TimeComplexity("O(n)")
	@Override
	public void add( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{
		//TCJ: because this function has to shift all of the elements following the one
		//added to make space and it also has to reassign the index values, 
		//the list has to be traversed once giving this function a best case of O(1)  
		//(when an element is added to the end) and a worst case(if the element is  
		//added at index 0) of O(n). at its absolute worst, the array will have to resize adding
		//another O(n) operation, making this a O(2n) operation.

		//System.out.println("Adding " + aElement);
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )//throw Exception if the index is out of bounds
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}
	
//		if(isEmpty())
//			
//			mArray[0] = new ArrSeqPos<E>(0, aElement);
		
		if( mCurrentSize == mMaxSize )//check to see if arraylist is full, 
			resize();//if so, resize

		for(int i = mCurrentSize; i > aIndex; i--)
		{
			mArray[i] = mArray[i-1];//shift elements
			mArray[i].setIndex(i);//reset indices
		}

		mArray[aIndex] = new ArrSeqPos<E> ( aIndex , aElement );//add the new element 

		mCurrentSize = mCurrentSize + 1;//increment the current size counter


	}

	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public E get( int aIndex ) throws IndexOutOfBoundsException
	{
		//TCJ: The cost does not vary with input size,
		//therefore we can say it is constant
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )//throw exception if out of bounds
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}

		return mArray[aIndex].element();
	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	@TimeComplexity("O(n)")
	@Override
	public E remove( int aIndex ) throws IndexOutOfBoundsException
	{
		//TCJ: because this function has to shift all of the elements following the one
		//added to make space and it also has to reassign the index values, 
		//the list has to be traversed once giving this function a best case of O(1)  
		//(when an element is added to the end) and a worst case(if the element is  
		//added at index 0) of O(n). 
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )//throw exception if index is out of bounds
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}

		E temp = mArray[aIndex].element();
		for( int i = aIndex; i<mCurrentSize-1; i++ )
		{
			
			mArray[i] = mArray[i+1];//shift elements
			mArray[i].setIndex(i);//reassign index values

		}
		//System.out.println("removing " + mArray);
		mCurrentSize = mCurrentSize - 1;//decrement the current size
		return temp;
	}


	@TimeComplexity("O(1)")
	@Override
	public E set( int aIndex, E aElement ) throws IndexOutOfBoundsException
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )//throw exception if index is out of bounds
		{
			throw new IndexOutOfBoundsException( "Index is invalid" );
		}
		System.out.println("set " + aIndex);
		E temp = mArray[aIndex].element();
		mArray[aIndex].setElement(aElement);//set element

		return temp;
	}

	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	@TimeComplexity("O(n)")
	@Override
	public void addAfter( Position<E> aPosition, E aElementToAdd )
	throws InvalidPositionException
	{
		//TCJ: this method is essentially the add() method,
		//so it has the same time complexity; see add().
		add(indexOf(aPosition)+1,aElementToAdd);

	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(n)")
	@TimeComplexity("O(n)")
	@Override
	public void addBefore( Position<E> aPosition, E aElementToAdd )
	throws InvalidPositionException
	{
		//TCJ: this method is essentially the add() method,
		//so it has the same time complexity; see add().
		add(indexOf(aPosition),aElementToAdd);

	}


	@TimeComplexity("O(1)")
	@Override
	public Position<E> first()
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.
		if (mArray[0] == null)
			return null;

		return mArray[0];
	}

	@TimeComplexity("O(1)")
	@Override
	public Position<E> last()
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant. see first()
		System.out.println("last " + mCurrentSize);
		return mArray[mCurrentSize-1];
	}
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(1)")
	@Override
	public Position<E> next( Position<E> aPosition )
	throws InvalidPositionException, BoundaryViolationException
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.
		int aIndex = indexOf(aPosition)+1;
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )
		{
			throw new BoundaryViolationException ( "Index is invalid" );
		}
		return mArray[aIndex];
	}

	@TimeComplexity("O(1)")
	@Override
	public Position<E> prev( Position<E> aPosition )
	throws InvalidPositionException, BoundaryViolationException
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.
		int aIndex = indexOf(aPosition)-1;
		if ( ( aIndex < 0 ) || ( aIndex > mCurrentSize ) )
		{
			throw new BoundaryViolationException ( "Index is invalid" );
		}
		return mArray[aIndex];
	}
	@TimeComplexity("O(n)")
	@Override
	public E remove( Position<E> aPosition ) throws InvalidPositionException
	{
		// TCJ: see remove(int)
		E temp = aPosition.element();
		remove(indexOf(aPosition));
		return temp;
	}
	@TimeComplexity("O(1)")
	@Override
	public E set( Position<E> aPosition, E aElement )
	throws InvalidPositionException
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.
		E temp = aPosition.element();
		System.out.println("foofight " + indexOf(aPosition));
		set(indexOf(aPosition),aElement);
		return temp;
	}

	@TimeComplexity("O(1)")
	@Override
	public Iterator<E> iterator()
	{
		// TCJ: The time complexity does not vary with input size,
		// thus the running time is constant.		
		return new ElementIterator<E>( this );
	} 
	/**
	 * This function doubles the size of the array
	 */
	@TimeComplexity("O(n)")
	public void resize()
	{
		//TCJ this function has to iterate through the list in order 
		//to copy from the original to the doubled list
		//thus making it O(n)
		mMaxSize = mMaxSize*2;
		ArrSeqPos<E>[] tmpArray = new ArrSeqPos[mMaxSize];//create temp list
		for( int i = 0 ; i < mCurrentSize ; i++ )//iterates through the list
			tmpArray[i] = mArray[i];//and maps the elements into the new list

		mArray = tmpArray;//map the temporary list to mArray
		//System.out.println("doubling array to " + mMaxSize);

	}

	@Override
	public void addFirst( E aElementToAdd )
	{
		add( 0,aElementToAdd );
	}

	@Override
	public void addLast( E aElementToAdd )
	{
		add( mCurrentSize, aElementToAdd );
	}

	@Override
	public E getFirst() throws EmptyDequeException
	{

		return mArray[0].element();
	}

	@Override
	public E getLast() throws EmptyDequeException
	{

		//System.out.println("last " + mArray[mCurrentSize-1].element().toString());
		return mArray[mCurrentSize-1].element();

	}

	@Override
	public E removeFirst() throws EmptyDequeException
	{
		E temp =  mArray[0].element(); 
		remove(0);
		return temp;
	}

	@Override
	public E removeLast() throws EmptyDequeException
	{
		E temp = mArray[mCurrentSize - 1].element();
		remove(mCurrentSize - 1);
//		System.out.println("removing " + temp.toString());
		return temp;
	}
	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Swaps the values contained in these two positions.
	 * 
	 * @param e the element to be added to the tree
	 * @throws InvalidPositionException
	 */
	@TimeComplexity("O(1)")
	public void swapElements(int i1, int i2) {
		//TCJ: time complexity does not vary with size - it is constant
		
		//replace the element in i2 with i1
		this.set(i2,this.set(i1, this.get(i2)) );//and map the element of i1 to i2 
	}
	
	public String toString()
	{
String s = "";
		for(int i = 0 ; i < size() ; i ++ )
		{
			s += (" , " + mArray[i].element());
		}
		return s;		
	}


	/**
	 * Used to test ArraySequence
	 * @param args
	 */
	@TimeComplexity("O(?)")
	@SpaceComplexity("O(?)")
	static public void main( String [] args )
	{
		ArraySequence<String> lLS = new ArraySequence<String>();

		if ( lLS.isEmpty() == false )
		{
			System.out.println( "FAILED: isEmpty did not return empty" );
		}
		else
		{
			System.out.println( "PASSED: isEmpty returned empty" );
		}
		System.out.println(lLS.first());
		lLS.add(0,"A");
//		System.out.println(lLS.size() + "** " + lLS.toString());
		lLS.add(0,"0");
//		System.out.println(lLS.size() + "** " + lLS.toString());
		lLS.add(1,"1");
//		System.out.println(lLS.size() + "** " + lLS.toString());
		lLS.add(2,"2");
//		System.out.println(lLS.size() + "** " + lLS.toString());
		lLS.add(3,"3");
		lLS.add(4,"4");
		lLS.add(5,"5");
		lLS.add(6,"6");
		lLS.add(7,"7");
		lLS.add(8,"8");
		lLS.add(9,"9");
		lLS.swapElements(9,8);
		lLS.swapElements(2,7);
		
		
//		lLS.add(10,"10");
//		lLS.add(8, "11");
//		lLS.add(12, "5000");
//		System.out.println(" -- " + lLS.last().element());
//		System.out.println(lLS.size() + "** " + lLS.toString());
//		lLS.set( lLS.last(), "6" );
////		System.out.println("+++++ " + lLS.get(0));
//		lLS.addBefore(lLS.last(), "5b");
		
//		lLS.getLast();
//		lLS.removeLast();
//		lLS.getLast();
//		lLS.removeLast();
//		lLS.getLast();
//		lLS.removeLast();
//		lLS.getLast();


		System.out.println("next" + lLS.next(lLS.atIndex(1)).element());
		System.out.println("prev" + lLS.prev(lLS.atIndex(3)).element());
		lLS.set(5, "1000");
		System.out.println("current size from size()" + lLS.size());
		for(int i = 0 ; i < lLS.mCurrentSize ; i++)
		{
			System.out.println(lLS.get(i));
		}
		if ( lLS.isEmpty() == false )
		{
			System.out.println( "PASSED: isEmpty did not return empty" );
		}
		else
		{
			System.out.println( "FAILED: isEmpty returned empty" );
		}


	}





}