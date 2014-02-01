import java.util.*;
public class recursive1 {

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
	public recursive1() {

		first = null;
		length = 0;
	} // end of constructor


	/**
	 * Get the first node on the linked list
	 * @return the first  Node
	 */
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

		Integer test = first.getValue();
		
		if (test.equals(what)) {
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
	
	public void replace(Integer o, Integer a) {
		first = replaceElement(first,o,a);
	}
	
	private Node replaceElement(Node node, Integer o, Integer a) {
		if (node == null) {
			return null;
		}
		else if (node.equals(o)) {
			return new Node(a,node.getNext());
		}
		else {
			return new Node(node.getValue(),replaceElement(node.getNext(),o,a));
		}
	}
	
	public void sumLists(Node list2) {
		first = sumTwoLists(first,list2);
	}
	
	public boolean containsNoIntegers() {
		return noIntegers(first);
	}
	
	private boolean noIntegers(Node node) {
		if (node == null) {
			return true;
		}
		else if (node.getValue() instanceof Integer) {
			return false;
		}
		else {
			return noIntegers(node.getNext());
		}
	}
	
	private Node sumTwoLists(Node node1, Node node2) {
		if (node1 == null) {
			if (node2 == null) {
				return null;
			}
			else {
				return node2;
			}
		}
		else if (node2 == null) {
			return node1;
		}
		else {
			return new Node(node1.getValue()+node2.getValue(),
							sumTwoLists(node1.getNext(),node2.getNext()));
		}
	}
	
	public Object getNthElement(int n) {
		return nthElement(first,n);
	}
	
	private Object nthElement(Node node, int n) {
		if (node == null) {
			return null;
		}
		else if (n == 0) {
			return node.getValue();
		}
		else {
			return nthElement(node.getNext(),n-1);
		}
	}
	
	public void remove(Object val) {
		first = removeVal(first,val);
	}
	
	private Node removeVal(Node n, Object val) {
		if (n == null) {
			return null;
		}
		else if (val.equals(n.getValue())) {
			return n.getNext();
		}
		else {	
			return new Node(n.getValue(),removeVal(n.getNext(),val));
		}
	}
	
	public int getLength() {
		return length(first);
	}
	
	private int length(Node n) {
		if (n == null) {
			return 0;
		}
		else {
			return 1 + length(n.getNext());
		}
	}
	
	public boolean isEven(int n) {
		if (n == 0) {
			return true;
		} else {
			return isOdd(n-1);
		}
	}
	
	public boolean isOdd(int n) {
		if (n == 0) {
			return false;
		}
		else {
			return isEven(n-1);
		}
	}
	
	public boolean inList(Object val) {
		return isMember(first,val);
	}
	
	private boolean isMember(Node n, Object val) {
		if (n == null)  {
			return false;
		}
		else if (val.equals(n.getValue())) {
			return true;
		}
		else {
			return isMember(n.getNext(),val);
		}
	}

	public boolean isListOfIntegers() {
		return isIntegerList(first);
	}
	
	private boolean isIntegerList(Node n) {
		if (n == null) {
			return true;
		}
		else if (n.getValue() instanceof Integer) {
			return isIntegerList(n.getNext());
		}
		else {
			return false;
		}
	}
	
	
	public boolean isListOfInteger() {
		Node n = first;
		
		while (n != null && n.getValue() instanceof Integer) {
			n = n.getNext();
		}
		
		return n == null;
	}
	public int sumList() {
		return recursiveSumList(first);
	}
	
	private int recursiveSumList(Node n) {
		if(n == null) {
			return 0;
		}
		else {
			return n.getValue()+ recursiveSumList(n.getNext());
		}
	}
	
	public static void hanoi(int n, int source, int aux, int dest) {
		if (n != 0) {
			hanoi(n-1,source,dest,aux);
			moveDisk(source,dest);
			hanoi(n-1,aux,source,dest);
		}
	}
	
	private static void moveDisk(int source, int dest) {
		System.out.println("Move disk from "+source+" to "+dest);
	}
	
	public static void mergeSort(Integer[] array) {
		Integer[] temp = new Integer[array.length];
		mergeSort(array,temp,0,array.length-1);
	}
	
	private static void mergeSort(Integer[] array, Integer[]temp,int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			mergeSort(array,temp,left,middle);
			mergeSort(array,temp,middle+1,right);
			merge(array,temp,left,middle,right);
		}
	}
	
	private static void merge(Integer[] array, Integer[] temp,int leftEnd, int middle, int rightEnd) {
		int leftIndex = leftEnd;
		int rightIndex = middle+1;
		int tempIndex = 0;
		
		while (leftIndex <= middle && rightIndex <= rightEnd) {
			if (array[leftIndex] < array[rightIndex]) {
				temp[tempIndex++] = array[leftIndex++];
			}
			else {
				temp[tempIndex++] = array[rightIndex++];
			}
		}
		
		while (leftIndex <= middle) {
			temp[tempIndex++] = array[leftIndex++];
		}
		
		while (rightIndex <= rightEnd) {
			temp[tempIndex++] = array[rightIndex++];
		}
		
		int t = 0;
		for (int i = leftEnd; i <= rightEnd; i++, t++) {
			array[i] = temp[t];
		}
	}
	
	public static int binarySearch(Integer[] array, Integer target, int first, int last) {
		
		if (first > last) {
			return -1;
		}
		else {
			int middle = (first + last) / 2;
			
			if (array[middle].intValue() == target.intValue()) {
				return middle;
			}
			else if (target.intValue() < array[middle].intValue()) {
				return binarySearch(array,target,first,middle-1);
			}
			else {
				return binarySearch(array,target,middle+1,last);
			}
		}
	}
	
}
