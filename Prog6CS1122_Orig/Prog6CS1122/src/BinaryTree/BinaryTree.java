package BinaryTree;

/**
 * This class implements a generic binary tree. For details regarding the
 * methods needed, see the BinaryTreeInterface.
 * 
 * @author Your Name
 * 
 * @param <V>
 *            The type of the data stored in each node
 */
public class BinaryTree<V> implements BinaryTreeInterface<V> {
	private BinaryTreeNode<V> root;

	public BinaryTreeNode<V> getRoot() {
		// TODO
		return null;
	}

	public void setRoot(BinaryTreeNode<V> newRoot) {
		// TODO
	}

	@Override
	public BinaryTreeNode<V> findValue(V value) {
		// TODO
		return null;
	}

	@Override
	public void addLeft(BinaryTreeNode<V> node, V value) {
		// TODO
	}

	@Override
	public void addRight(BinaryTreeNode<V> node, V value) {
		// TODO
	}

	@Override
	public BinaryTreeNode<V> getLeft(BinaryTreeNode<V> node) {
		// TODO
		return null;
	}

	@Override
	public BinaryTreeNode<V> getRight(BinaryTreeNode<V> node) {
		// TODO
		return null;
	}

	@Override
	public BinaryTreeNode<V> getParent(BinaryTreeNode<V> node) {
		// TODO
		return null;
	}

	/**
	 * This is a simple main to test some of the functionality of the
	 * BinaryTree<V> class. This is not an extensive test, and more thorough
	 * testing should be done to ensure that it works properly.
	 * 
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		BinaryTree<Integer> intTree = new BinaryTree<Integer>();
		intTree.setRoot(new BinaryTreeNode<Integer>(10, null));
		intTree.addLeft(intTree.getRoot(), 5);
		intTree.addRight(intTree.getRoot(), 20);
		intTree.addLeft(intTree.getRoot().getRight(), 15);

		System.out.println("root = " + intTree.getRoot().getValue());
		System.out.println("root -> left = "
				+ intTree.getRoot().getLeft().getValue());
		System.out.println("root -> right = "
				+ intTree.getRoot().getRight().getValue());
		System.out.println("root -> right -> left = "
				+ intTree.getRoot().getRight().getLeft().getValue());

		int findValue = 15;
		System.out.println("find(" + findValue + ") -> "
				+ intTree.findValue(findValue).getValue());
	}
}
