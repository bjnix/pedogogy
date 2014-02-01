/**
 * @author Brent Nix
 * CS1090
 * Spring 2010
 */

/*
 *  A class that implement a linked list.
 *    
 */

public class ALinkedList {

	private class Node {

		// properties
		private Integer element;
		private Node next;

		/**
		 * Create a new node.
		 *
		 * @param v The value referred to by this node.
		 * @param n The next node in the list (or null if the end).
		 */
		public Node(Integer v, Node n) {
			element = v;
			next = n;
		} // end of constructor		


		/**
		 * Get the integer value referred to by this node.
		 *
		 * @return The value.
		 */
		public Integer getValue() {
			return element;
		} // end of getValue method

		/**
		 * Get the next node in the list.
		 *
		 * @return A reference to the next node (or null if this is the last
		 *         node in the list).
		 */
		public Node getNext() {
			return next;
		} // end of getNext method

		/**
		 * Make a new node be the next node in the list.
		 *
		 * @param A reference to the next node.
		 */
		public void setNext(Node where) {
			next = where;
		} // end of setNext method

	} // end of Node class

	// properties

	// reference to first node in the list
	// (or null if the list is empty)
	private Node first;

	// number of Integers in the collection
	private int length;

	/**
	 * Create a new, empty collection.
	 */
	public ALinkedList() {

		first = null;
		length = 0;
	} // end of constructor


	public Node getFirst(){
		return first;
	}

	/**
	 * Add an Integer to the collection.
	 *
	 * @param newValue The string to add.
	 */
	public void add(Integer newValue) {
		// create a new node and put it at the beginning of the list
		first = new Node(newValue,first);
		length ++;
	} // end of add method


	/**
	 * Remove an Integer from the collection.
	 *
	 * @param what The Integer to remove.
	 */
	public void remove(Integer what) { 

		// do nothing if the list is empty
		if (first == null)
			return;

		Integer testFirst = first.getValue();

		// if the integer is in the first node in the list...	
		if (testFirst.equals(what)) 
		{
			// remove it, 

			first = first.getNext();
			length -= 1;
			return;
		}

		// start at the beginning of the list
		Node temp = first;

		// as long as there are still nodes left
		while(temp.getNext() != null) {

			// if the "next" node points at the string to remove
			Object it = temp.getNext().getValue();
			if (it.equals(what)) {

				// remove it
				temp.setNext(temp.getNext().getNext());
				length -= 1;
				return;
			}

			// move to the next node
			temp = temp.getNext();
		}
	} // end of remove method

	/**
	 * Return the number of Integers in the collection.
	 *
	 * @return the number.
	 */
	public int size() {
		return length;
	} // end of size method

	/**
	 * Return a string representation of this collection, in the form
	 * ["Int1","Int2",...].
	 * 
	 * @return the string representation.
	 */
	public String toString() {

		// initialize result
		String result = "[";

		// if nothing in the list, return "[]"
		if (first == null) {
			return result+"]";
		}

		// put the first string in the result
		result = result + first.getValue();

		// go down the list...
		Node temp = first.getNext();
		while (temp != null) {

			// append a comma and the next string to the result
			result = result + "," + temp.getValue();

			// advance to the next element in the list
			temp = temp.getNext();
		}

		// return the result
		return result + "]";
	} // end of toString method



	/**
	 * Removes all nodes from the list so that a list containing
	 * [1,2,3] would then contain [] if the toString method is called
	 */
	public void clearList()
	{		
		first = null;//set list to null
	}


	/**
	 *  Sorts the link list in descending order.
	 *  So the list [1,4,2,5] becomes [5,4,2,1] after insertSort is run on it
	 */
	public void insertSort()
	{
		ALinkedList listTemp = new ALinkedList();//create new list
		Node lTemp = listTemp.getFirst();//create new node for listTemp
		if(first == null){
			return;
		}
		if( first != null)
		{
			Node jTemp = this.getFirst(); //new node jTemp equals first

			int big = Integer.MIN_VALUE; 
			while(jTemp != null)//find the biggest number in first and move it to listTemp
			{
				if(jTemp.getValue() > big)
				{
					big = jTemp.getValue();
				}
				jTemp = jTemp.getNext();

			}
			listTemp.add(big);
			remove(big);
		}
		while(!(first == null))//insert value descending  correct order
		{
			int tempVal = first.getValue();
			lTemp = listTemp.getFirst();

			while(lTemp != null && lTemp.getNext() != null && lTemp.getNext().getValue()>first.getValue())//find insertion point
			{
				lTemp = lTemp.getNext();
			}

			if(lTemp == null)//insert if lTemp is null
			{
				listTemp.add(tempVal);
				first = first.getNext();
			}
			else // //insert in correct position
			{
				Node mTemp = lTemp.getNext();
				lTemp.setNext(first);
				first = first.getNext();
				lTemp.getNext().setNext(mTemp);
			}


		}


		this.first = listTemp.getFirst();//set listTempto first
		this.length = listTemp.size();//set length of first 
	}



	/**
	 * Delete integers that do not contain duplicate copies in the list.
	 * Only integers with at least two entries in the list will remain.
	 * e.g. [1,2,3,1,5,2,4,1,6,2] will become [1,2,1,2,1,2]
	 */
	public void deleteNonDuplicates()
	{
		Node firstTemp = first;
		Node temp = first;



		if(first == null)
		{
			return;
		}

		while(firstTemp != null)
		{
			int count = 0;
			while(temp != null)//search list
			{	

				if(firstTemp.getValue().equals(temp.getValue()))//if value of firstTemp equals a value, increase count
				{
					++count;
				}

				temp = temp.getNext();
			}
			if(count == 1)//if count of an item is 1, remove it
			{
				remove(firstTemp.getValue());
			}

			temp = this.getFirst();
			firstTemp = firstTemp.getNext();
		}

	}

	/**
	 * Merge two linked list together to the current linked list, that is, the header is "first".
	 * merge their nodes together to make one list, taking nodes alternately between the two lists. 
	 * So shuffleMerge() with current list [1, 2, 3] and the argument [7, 13, 1] should yield 
	 * the current list containing [7, 1, 13, 2, 1, 3]. If either list runs out, all the nodes should 
	 * be taken from the other list.
	 * @param two another linked list
	 */
	public void shuffleMerge(ALinkedList two)
	{		
		ALinkedList listTemp = new ALinkedList();
		Node lTemp = listTemp.getFirst();
		Node twoN = two.getFirst();
		while(first != null||twoN != null){//grab one node from each list alternating until one or both run out, continue until the longer one runs out


			while(!(twoN == null))
			{
				listTemp.add(twoN.getValue());
				twoN = twoN.getNext();
				break;
			}
			while(!(first == null))
			{

				listTemp.add(first.getValue());
				first = first.getNext();
				break;
			}

		}

		lTemp = listTemp.getFirst();
		while(lTemp != null)//reverse list
		{
			this.add(lTemp.getValue());
			lTemp=lTemp.getNext();
		}
		this.length = listTemp.size();
	}



	/**
	 * Merge the current linked list with the argument linked list, both of which are sorted in decreasing order. 
	 * Merges the two together into one list which is still in decreasing order and the first is still the header.
	 * @param two another sorted linked list
	 * e.g. if the two lists are [5, 3, 1] and [6, 4, 2] then after the sorted merge, the first list will contain
	 * [6,5,4,3,2,1]
	 */	
	public void sortedMerge(ALinkedList two)
	{
		shuffleMerge(two);
		insertSort();
	}


} // end of LinkedStringCollection class
