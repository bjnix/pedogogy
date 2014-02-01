package Main;

import BinaryTree.BinaryTree;
import HuffmanEncoder.HuffmanEncoder;
import PriorityQueue.MinimumPriorityQueue;

/**
 * An overall main class to provide easier testing.
 * 
 * @author Bryan Franklin
 * 
 */
public class Main {

	/**
	 * This calls the main methods from several classes to facilitate easier
	 * testing.
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		System.out.println("\nCalling MinimumPriorityQueue.main(args)");
		MinimumPriorityQueue.main(args);

		System.out.println("\nCalling BinaryTree.main(args)");
		BinaryTree.main(args);

		System.out.println("\nCalling HuffmanEncoder.main(args)");
		HuffmanEncoder.main(args);
	}
}
