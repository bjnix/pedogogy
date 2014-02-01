package cs2321;

import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Sequence. 
 * 
 * SCJ: there are n elements in the array it is O(n) 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #4
 * @Brent Nix
 */
@SpaceComplexity("O(n)")
public class Heap<K extends Comparable<K>,V> implements PriorityQueue<K,V> {

	/**
	 * a simple private class making a node for the heap to use
	 * @author Brent Nix
	 * 
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	@SpaceComplexity("O(1)")
	private static class MyEntry<K,V> implements Entry<K,V> {

		//declare variables
		private K key;
		private V value;

		//constructor
		public MyEntry(K k,V v)
		{
			key = k;
			value = v;
		}
		@Override
		@TimeComplexity("O(1)")
		public K getKey() {
			//TCJ: time complexity does not vary with size - it is constant
			return key;
		}

		@Override
		@TimeComplexity("O(1)")
		public V getValue() {		
			//TCJ: time complexity does not vary with size - it is constant
			return value;
		}
		/**
		 *method that converts the contents in the node to string format for debugging purposes 
		 */
		@TimeComplexity("O(1)")
		public String toString()
		{ 
			//TCJ: time complexity does not vary with size - it is constant
			return"(" + key + " , " + value + ")";
		}

	}
	//initialize variable
	private CompleteTree<Entry<K,V>> tree;
	//constructor
	public Heap() {
		tree = new CompleteTree<Entry<K,V>>();
	}

	/**
	 * private method used to "bubble up", or "upheap"
	 * @param p a position from the tree, containing an entry into the heap
	 */
	@TimeComplexity("O(lg n)")
	private void bubbleUp(Position<Entry<K,V>> p){
		//TCJ it is lg n time because it has to go the height of the tree which is lg n
		Position<Entry<K, V>> u;
		while (!tree.isRoot(p))//while tree is not root
		{
			u = tree.parent(p);
			if(u.element().getKey().compareTo(p.element().getKey()) <= 0) break;
			tree.swapElements(u,p);
			p=u;
		}
	}

	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param p
	 */
	@TimeComplexity("O(lg n)")
	private void bubbleDown(Position<Entry<K,V>> p){
		//TCJ it is lg n time because it has to go the height of the tree which is lg n
		while (tree.isInternal(p)){
			Position<Entry<K, V>> s; //position of the smaller child
			if(!tree.hasRight(p))//if node doesn't have right a is internal, it must be left
				s = tree.left(p);//if its'd the only child then it is the smallest child
			else if(tree.left(p).element().getKey().compareTo(tree.right(p).element().getKey()) <= 0)
				s = tree.left(p);//compare right and left and let map the smaller to "s"
			else
				s = tree.right(p);
			if(s.element().getKey().compareTo(p.element().getKey()) < 0 )//check if nodes need to be swapped
			{
				tree.swapElements(p, s);
				p = s;
			}
			else 
				break;
		}

	}

	@TimeComplexity("O(lg n)")
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		//TCJ: O(lg n) TC because it has to bubble up after inserting
		checkKey(key);//may throw InvalidKeyException
		Entry<K, V> entry = new MyEntry<K,V>(key,value);

		bubbleUp(tree.addLast(entry));
		//		System.out.println(entry.toString());
		//		System.out.println(size());
		return entry;
	}
	/**
	 * private method that checks for an InvalidKeyException
	 * @param key
	 * @throws InvalidKeyException
	 */
	@TimeComplexity("O(1)")
	private void checkKey(K key) throws InvalidKeyException {
		//TCJ: time complexity does not vary with size - it is constant
		try{
			key.compareTo(key);
		}
		catch(Exception e){
			throw new InvalidKeyException("Invalid key");
		}

	}

	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		//TCJ: time complexity does not vary with size - it is constant
		return tree.isEmpty();
	}


	@TimeComplexity("O(1)")
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		//TCJ: Accesses the first element in the tree, time complexity does not vary with size - it is constant
		if(isEmpty()) throw new EmptyPriorityQueueException("Priority queue is empty");

		return tree.root().element();
	}

	@TimeComplexity("O(lg n)")
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		//TCJ: it is O(lg n) because it has to bubbleDown
		if(isEmpty()) throw new EmptyPriorityQueueException("Priority queue is empty");
		Entry<K, V> min = tree.root().element();
		if(size() == 1)
			tree.removeLast();
		else{

			tree.swapElements(tree.root(), tree.getLast());

			tree.removeLast();

			bubbleDown(tree.root());
		}
		return min;
	}
	@TimeComplexity("O(1)")
	public int size() {
		//TCJ: time complexity does not vary with size - it is constant
		return tree.size();
	}

}
