package cs2321;

import net.datastructures.*;

import java.util.Comparator;
/**
 * An ordered Lookup Table implemented using an array of Entries.
 * 
 * @author Brent Nix
 *
 * SCJ: because there are "n" elements in the array its 
 * time complexity is linearly proportional to the amount 
 * of elements in the array
 *  
 * @param <K>
 * @param <V>
 */
@SpaceComplexity("O(n)")
public class ModifiedLookupTable<K extends Comparable, V > implements Map<K, V> {

	/**
	 * Simple class for creating entries
	 * @author Brent Nix
	 *
	 * SCJ: the entry is only a node containing two elements, 
	 * so the space complexity is constant
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	@SpaceComplexity("O(1)")

	private static class MyEntry<K,V>  implements Entry<K,V> {
		//proclaim variables
		private K key;
		private V value;

		//**constructor**
		public MyEntry(K k,V v){
			key = k;
			value = v;
		}

		@Override
		public K getKey() { return key; }

		@Override
		public V getValue() { return value; }

	}

	//proclaim variables
	private Entry<K,V>[] data;
	private Comparator<K> comparator;
	private int mCurrentSize = 0;
	private int mArraySize = 10;

	/**
	 * constructor
	 * @param comparator a comparator object 
	 * @param data an array of type Entry<K,V>
	 */
	public ModifiedLookupTable(){
		//		comparator = new Comparator<K>();
		data = (Entry<K,V>[])new Entry[mArraySize];
	}
	public ModifiedLookupTable(int size)
	{
		mArraySize = size;
		data = (Entry<K,V>[])new Entry[mArraySize];
	}

	/**
	 * 
	 * @param comparator - on construction, set comparator
	 */
	public ModifiedLookupTable(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	/**
	 * !WARNING! (only use if input will be ordered!)
	 * Adds desired entry to the table - unodered
	 * @param key
	 * @param value
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(n)")
	@TimeComplexityExpected("O(1)")
	public void add(K key ,V value)
	{
		//TCJ: since this is primarily an add last method, no shifting has to be done
		//so the only non-constant expense will be doubling the size of the table
		//if initialized to the correct size, these doublings can be avoided.
		//The only time this will cost more than constant time is if the data is ordered

		if (mCurrentSize == mArraySize)//if the Array is full, double it
			resize();
		Entry<K, V> nue = new MyEntry<K, V>(key, value); //the new Entry
		if(mCurrentSize > 0 && data[mCurrentSize-1].getKey().compareTo(key) < 0)//Check to see if Entry is pre-ordered
		{
			for ( int i = 0 ; i < mCurrentSize ; i ++ )//iterate through list
			{
				if ( data[i].getKey().compareTo(key) > 0 )//if nue entry is less than the current key, insert.
				{

					for(int j = mCurrentSize; j >= i + 1 ; j--)
					{
						data[j] = data[j-1];//shift elements

					}
					data[i] = nue;//insert element
					mCurrentSize ++;//increment size
					return;
				}

			}
		}
		data[mCurrentSize] = nue;//insert item at end of array
		mCurrentSize ++ ;//increment size

	}
	/**
	 * Returns a range of entries starting at start and stopping at stop
	 * @param start
	 * @param stop
	 * @return
	 */
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> getRange(K start, K stop)  {
		//TCJ: the time varies linearly with the size of list because it has to iterate through the list
		LinkedSequence<Entry<K, V>> iter = new LinkedSequence<Entry<K, V>>();//create iterable list
		if ( start.compareTo(stop) > 0)
		{
			K temp = start;
			start = stop;
			stop = temp;
		}
		//		System.out.println("Searching by zip codes from:" +start + " to: "+stop);//notify user of action
		int beg = binarySearch(start);
		int end = binarySearch(stop);

		K fend = data[end].getKey();
		K fbeg = data[beg].getKey();
		if ( fend.compareTo(stop) < 0 )
			end = end;

		if ( fbeg.compareTo(start) < 0 )
			beg = beg + 1;

		if ( fend.compareTo(start) < 0 || fbeg.compareTo(stop) > 0){
			//			System.out.println("\nStart and Stop values outside of list\n------------------\n");
			return null;}
		if (beg == end) iter.addLast(data[beg]);
		for (int i = beg; i < end ; i ++)//for each entry in data,
			iter.addLast(data[i]);//add the entry to the list
		return iter;
	}
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entries() {
		//TCJ: the time varies linearly with the size of list because it has to iterate through the list
		LinkedSequence<Entry<K, V>> iter = new LinkedSequence<Entry<K, V>>();//create iterable list
		for (int i = 0; i < mCurrentSize ; i ++)//for each entry in data,
			iter.addLast(data[i]);//add the entry to the list
		return iter;
	}
	@TimeComplexity("O(lg n)")
	public V get(K key) throws InvalidKeyException { 
		//TJC:This uses a binary search which has a worst time expectancy of lg n and a constant best time expectancy
		if (key == null) { throw new InvalidKeyException("Invalid Key");}//throws exception if key is not in list
		int k = binarySearch(key);//run binary search
		return data[k].getValue(); //return value
	}
	@TimeComplexity("O(1)")	
	public boolean isEmpty() {		
		//TCJ: time does not vary with the size of the array, so it is constant
		return (mCurrentSize == 0);
	}
	@TimeComplexity("O(n)")
	public Iterable<K> keys() {
		//TCJ: the time varies linearly with the size of list because it has to iterate through the list
		LinkedSequence<K> iter = new LinkedSequence<K>();//create iterable list
		for (int i = 0; i < mCurrentSize ; i ++)//for each entry in data,
			iter.addLast(data[i].getKey());//add the entry to the list
		return iter;		
	}
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(n)")
	public V put(K key, V value) throws InvalidKeyException {
		//TCJ: because this array will double every time it reaches peak capacity, 
		//it will at its worst case be O(n). Because of the fact that it must iterate
		//through all of the elements if it isn't empty it is still O(n), and amortization
		//does not help.
		if( key == null){ throw new InvalidKeyException("Invalid Key for put(K,V)");}
		Entry<K, V> temp = null;//create temp Entry for storing a replaced entry
		Entry<K, V> nue = new MyEntry<K, V>(key, value); //the new Entry
		if (mCurrentSize == mArraySize)//if the Array is full, double it
			resize();
		if (isEmpty())//check if list is empty
		{
			data[mCurrentSize] = nue;//insert new entry
			mCurrentSize ++;//increment size
			return null;
		}
		for ( int i = 0 ; i < mCurrentSize ; i ++ )//iterate through list
		{

			if (data[i].getKey().compareTo(key) == 0)//if the keys are equal, swap elements
			{
				temp = data[i];//save original entry
				data[i] = nue;//insert element 
				return temp.getValue();//return old entry
			}

			else if ( data[i].getKey().compareTo(key) > 0 )//if nue entry is less than the current key, insert.
			{

				for(int j = mCurrentSize; j >= i + 1 ; j--)
				{
					data[j] = data[j-1];//shift elements

				}
				data[i] = nue;//insert element
				mCurrentSize ++;//increment size
				return null;
			}

		}
		data[mCurrentSize] = nue;//insert item at end of array
		mCurrentSize ++ ;//increment size
		return null;
	}
	@TimeComplexity("O(n)")
	private void resize() {
		//TCJ: because this has to iterate through the list to copy it, the time
		//complexity varies linearly with the size of the list
		mArraySize = mArraySize * 2;//double array size
		Entry<K, V>[] temp = (Entry<K,V>[])new Entry[mArraySize];//create new list
		for ( int i = 0 ; i < mCurrentSize ; i ++ )//map all elements from data to temp
			temp[i] = data [i];
		data = temp;//map temp to data
	}
	@TimeComplexity("O(n)")
	public V remove(K key) throws InvalidKeyException {
		//TCJ: because it has to shift all of the elements in the list over, the time 
		//varies linearly with the size of the list 
		if ( key.equals(null) ) { throw new InvalidKeyException("Invalid Key");}//throw exception if key not in list
		int j = binarySearch(key);

		if ( data[j].getKey() != key ){
			//			System.out.println("key not in list");
			return null;}
		V temp = data[j].getValue();

		for ( int i = j ; i < mCurrentSize ; i ++)//shift elements, removing the element
			data[i] = data[i+1];
		mCurrentSize --;//increment size
		return temp;
	}
	@TimeComplexity("O(1)")
	public int size() {
		//TCJ: time does not vary with size
		return mCurrentSize ;
	}
	@TimeComplexity("O(n)")
	public Iterable<V> values() {
		//TCJ: the time varies linearly with the size of list because it has to iterate through the list
		LinkedSequence<V> iter = new LinkedSequence<V>();//create iterable list
		for (int i = 0; i < mCurrentSize ; i ++)//for each entry in data,
			iter.addLast(data[i].getValue());//add the entry to the list
		return iter;		

	}
	@SpaceComplexity("O(1)")
	@TimeComplexity("O(lg n)")
	private int binarySearch(K key){
		//TCJ: because it takes lg n + 1 iterations at worst case to make is through the list
		//SCJ: because this is an iterative binary search, it doesn't make use of the stack in
		//the compiler like a recursive function would.

		int left = 0; //create left pointer
		int right = mCurrentSize-1;//create right pointer
		int mid = 0;//create mid pointer

		while ( !(left > right))//iterate until left is greater than right
		{
			mid = left + ((right - left)/2);//redefine mid
			if ( data[mid].getKey().equals(key) )//if the key is found, return it
				return mid;

			if ( key.compareTo(data[ mid ].getKey() ) > 0)//if the key is the right portion
				left = mid + 1;//move to the right portion
			else 
				right = mid - 1;//move to the left

		}

		return mid;

	}
	@TimeComplexity("O(n)")
	public String toString(){
		//TCJ: must iterate throw list, so the time varies linearly with respect to the size of the list.
		String gum = " ";
		for ( int i = 0 ; i < mCurrentSize ; i ++ )
		{
			gum = gum + data[i].getKey() + " , " + data[i].getValue() + " \n ";
		}
		return gum;
	}
	/**
	 * Main class for testing purposes
	 * @param args
	 */
	public static void main(String[] args){
		ModifiedLookupTable<Integer, String> lut = new ModifiedLookupTable<Integer, String>();
		System.out.println(lut.isEmpty());
		lut.put(3, "C");
		lut.put(1, "A");
		lut.put(2, "B");
		lut.put(4, "D");
		lut.put(3, "D");
		lut.put(4, "E");
		lut.put(5, "F");
		lut.put(6, "G");
		lut.put(7, "H");
		lut.put(8, "I");
		lut.put(9, "J");
		lut.put(10, "K");
		lut.put(11, "L");
		lut.put(3, "foo");
		lut.put(12, "M");
		lut.put(13, "N");
		lut.put(14, "O");
		lut.put(15, "P");
		lut.put(16, "Q");
		lut.put(40, "C");
		lut.put(10, "A");
		lut.put(20, "B");
		lut.put(40, "D");
		lut.put(30, "D");
		lut.put(40, "E");
		lut.put(50, "F");
		lut.put(60, "G");
		lut.put(70, "H");
		lut.put(80, "I");
		lut.put(90, "J");
		lut.put(100, "K");
		lut.put(101, "L");
		lut.put(30, "foo");
		lut.put(102, "M");
		lut.put(103, "N");
		lut.put(104, "O");
		lut.put(105, "P");
		lut.put(106, "Q");

		System.out.println(lut.isEmpty());
		System.out.println(lut);
		System.out.println(lut.entries());
		System.out.println(lut.keys());
		System.out.println(lut.values());

		lut.remove(3);
		lut.remove(1);
		lut.remove(2);
		lut.remove(4);
		lut.remove(3);
		lut.remove(4);
		lut.remove(5);
		lut.remove(6);
		lut.remove(7);
		lut.remove(8);
		lut.remove(9);
		lut.remove(10);
		lut.remove(11);
		lut.remove(3);
		lut.remove(12);
		lut.remove(13);
		lut.remove(14);
		lut.remove(15);
		lut.remove(16);
		lut.remove(40);
		lut.remove(10);
		lut.remove(20);
		lut.remove(40);
		lut.remove(30);
		lut.remove(40);
		lut.remove(50);
		lut.remove(60);
		lut.remove(70);
		lut.remove(80);
		lut.remove(90);
		lut.remove(100);
		lut.remove(101);
		lut.remove(30);
		lut.remove(102);
		lut.remove(103);
		lut.remove(104);
		lut.remove(105);
		lut.remove(106);

		System.out.println(lut.isEmpty());
		System.out.println(lut);
		System.out.println(lut.entries());
		System.out.println(lut.keys());
		System.out.println(lut.values());

	}
}

