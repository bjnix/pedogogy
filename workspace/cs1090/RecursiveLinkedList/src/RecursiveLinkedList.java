/**
  * @author Brent Nix
  * CS1090
  * Spring 2010
  */


/*
 *  A class that implement a linked list.
 *    
 */

public class RecursiveLinkedList {

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
		 * Make a new node be the next node in the list.
		 *
		 * @param A reference to the next node.
		 */
		

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


	} // end of Node class

	// properties

	// reference to first node in the list
	// (or null if the list is empty)
	private Node first;

	/**
	 * Create a new, empty collection.
	 */
	public RecursiveLinkedList() {

		first = null;
	} // end of constructor


	public Node getFirst(){
		return first;
	}


	/**
	 * Add an Integer to the collection.
	 *
	 * @param newValue The string to add.
	 */
	public void add(int newValue) {
		// create a new node and put it at the beginning of the list
		first = new Node(newValue,first);
	} // end of add method

	public void clear() {
		first = null;
	}

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
	 * Removes all nodes containing odd Integers from the list.
	 * The list [1,2,3] would then contain [2] if the toString method is called
	 */

	public void evenPart()
	{		
		RecursiveLinkedList temp = new RecursiveLinkedList();//create new linked list temp
		evenPart(temp);

	}

	private void evenPart(RecursiveLinkedList temp)
	{
		if(first == null)//base case
		{
			this.first = temp.first;//set the original list equal to temp
			return;			
		}
		int yN = first.getValue() % 2;//calculate whether or not the value is odd or even
		if(yN == 0)//if the value is even, add it to temp
		{
			temp.add(first.getValue());
		}

		first=first.getNext();//advance first
		evenPart(temp);//recur
	}
	/**
	 *  Modifies the list by inserting the item newInt before EVERY occurrence of the 
	 *  item oldInt in the list
	 *  So the list [1,4,2,4,5] becomes [1,3,4,2,3,4,5] after insertBefore is called
	 *  as insertBefore(3,4);
	 *  
	 *  @param newInt the integer to insert before oldInt
	 *  @param oldInt the integer before which newInt is inserted
	 */
	public void insertBefore(int newInt, int oldInt)
	{
		RecursiveLinkedList temp = new RecursiveLinkedList();//create new linked list temp
		insertBefore(temp, newInt, oldInt);
		this.reverse();
	}

	private void insertBefore(RecursiveLinkedList temp, int what, int where)
	{
		if(first == null)//base case
		{

			this.first = temp.first;//set the original list equal to temp
			return;	
		}
		if(first.getValue() == where)//if the value of the current node is equal to where,
		{
			temp.add(what);//add what to temp
		}
		temp.add(first.getValue());//add first to temp


		first = first.next;//increment first
		insertBefore(temp,what,where);//recur

	}


	/**
	 *  Modifies the list by inserting the item newInt after EVERY occurrence of the 
	 *  item oldInt in the list
	 *  So the list [1,4,2,4,5] becomes [1,4,3,2,4,3,5] after insertAfter is called
	 *  as insertAfter(3,4);
	 *  
	 *  @param newInt the integer to insert after oldInt
	 *  @param oldInt the integer after which newInt is inserted
	 */
	public void insertAfter(int newInt, int oldInt)
	{
		RecursiveLinkedList temp = new RecursiveLinkedList();//create new linked list temp
		insertAfter(temp, newInt, oldInt);
		this.reverse();
	}
	private void insertAfter(RecursiveLinkedList temp, int what, int where)
	{
		if(first == null)//base case
		{
			this.first = temp.first;//set the original list equal to temp
			return;	
		}
		temp.add(first.getValue());//add first to temp

		if(first.getValue() == where)//if the value of first is equal to where
		{
			temp.add(what);//add what to temp
		}


		first = first.next;//increment first
		insertAfter(temp,what,where);//recur
	}
	/**
	 * Merge two linked list together to the current linked list, that is, the header is "first".
	 * merge their nodes together to make one list, taking nodes alternately between the two lists. 
	 * So shuffleMerge() with current list [1, 2, 3] and the argument [7, 13, 1] should yield 
	 * the current list containing [7, 1, 13, 2, 1, 3]. If either list runs out, all the nodes should 
	 * be taken from the other list.
	 * @param two another linked list
	 */
	public void shuffleMerge(RecursiveLinkedList two)
	{		
		RecursiveLinkedList temp = new RecursiveLinkedList();//create new linked list temp
		shuffMrg(temp, two);
		this.reverse();
	}
	private void shuffMrg(RecursiveLinkedList temp, RecursiveLinkedList two)
	{
		if(this.first == null && two.first == null)//base case, if both this and two are equal to zero,
		{
			this.first = temp.getFirst();//set the original list equal to temp
			return;
		}
		if(two.first != null)//if two is not null,
		{
			temp.add(two.getFirst().getValue());//add two.first to temp
			two.first=two.first.getNext();//increment two.first
		}
		if(this.first != null)//if this is not null,
		{
			temp.add(this.getFirst().getValue());//add this.first to temp
			this.first=this.first.next;//increment this.first
		}
		shuffMrg(temp, two);//recur

	}


	/**
	 * Sort the current linked list in decreasing order. 
	 * The list [1, 5, 3] will be [5, 3, 1] after the sort
	 */	
	public void sortList()
	{
		Node head = first;//create new node head equal to first
		sortList(head);
		reverse();

	}
	public void sortList(Node head)
	{
		if(head == null)//base case
		{
			return;
		}
		else
		{
			head = head.next;//increment
			sortList(head);//recur
		}
		swap(head);
		return ;
	}
	private void swap( Node head)
	{
		if(head==null)//base case
			return;

		if(head.next == null)//if there is only one node, return
		{			
			return;
		}
	
		if(head.next.getValue() < head.getValue())//if the value of next is less than the value of head swap the values:
		{

			Integer temp = head.next.element;//set the value of the Integer temp to the value of next
			head.next.element = head.element;//set the value of next to the value of head
			head.element = temp;//set the value of head equal to temp
			
		}
		
		head = head.next;//increment head

		swap(head);
	}

	
	/**
	 * Reverses the order of the elements on the list.
	 * The list [1, 3, 5] will be [5, 3, 1] after reversing it.
	 */
	public void reverse() {
		RecursiveLinkedList temp = new RecursiveLinkedList();//create new linked list temp
		rev(temp);
	}
	private void rev(RecursiveLinkedList temp)
	{
		if(first == null)//base case
		{
			this.first = temp.getFirst();//set the original list equal to temp
			return;
		}
		temp.add(first.getValue());//add first to temp
		first = first.getNext();//increment first
		rev(temp);


	}
} // end of LinkedStringCollection class
