package PriorityQueue;

public class MinimumPriorityQueue<K extends Comparable<K>, V> implements
		PriorityQueueInterface<K, V> {

	@Override
	public void enqueue(K key, V value) {
		// TODO
	}

	@Override
	public V dequeue() {
		// TODO
		return null;
	}

	public V next() {
		// TODO
		return null;
	}

	@Override
	public int size() {
		// TODO
		return -1;
	}

	/**
	 * This is a simple main to test some of the functionality of the
	 * MinimumPriorityQueue<K,V> class. This is not an extensive test, and more
	 * thorough testing should be done to ensure that it works properly. s
	 * 
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		MinimumPriorityQueue<Integer, String> queue1 = new MinimumPriorityQueue<Integer, String>();
		MinimumPriorityQueue<String, Integer> queue2 = new MinimumPriorityQueue<String, Integer>();

		queue1.enqueue(1, "One");
		queue1.enqueue(3, "Three");
		queue1.enqueue(2, "Two");
		queue1.enqueue(4, "Four");
		queue1.enqueue(3, "Another Three");

		while (queue1.size() > 0)
			System.out.println(queue1.dequeue() + " (next: " + queue1.next()
					+ ")");

		queue2.enqueue("The", 1);
		queue2.enqueue("quick", 2);
		queue2.enqueue("grey", 3);
		queue2.enqueue("fox", 4);

		while (queue2.size() > 0)
			System.out.println(queue2.dequeue() + " (next: " + queue2.next()
					+ ")");

	}

}
