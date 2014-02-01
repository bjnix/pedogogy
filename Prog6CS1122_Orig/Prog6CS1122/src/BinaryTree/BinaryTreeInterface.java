package BinaryTree;

/**
 * This is the interface for the binary tree. You are not allowed to modify this
 * file.
 * 
 * DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE!!!
 * 
 * @author Bryan Franklin
 * 
 * @param <V>
 *            The type of data held in each node.
 */
public interface BinaryTreeInterface<V> {

	/**
	 * Set the root node of the tree.
	 * 
	 * @param node
	 *            The node that will become to new root.
	 */
	public void setRoot(BinaryTreeNode<V> node);

	/**
	 * Returns the root of the tree
	 * 
	 * @return The root node of the tree
	 */
	public BinaryTreeNode<V> getRoot();

	/**
	 * Adds a new tree node as the left child of a node.
	 * 
	 * @param node
	 *            The node for which a left child will be added
	 * @param value
	 *            The value to be stored in the new node
	 */
	public void addLeft(BinaryTreeNode<V> node, V value);

	/**
	 * Adds a new tree node as the right child of a node.
	 * 
	 * @param node
	 *            The node for which a right child will be added
	 * @param value
	 *            The value to be stored in the new node
	 */
	public void addRight(BinaryTreeNode<V> node, V value);

	/**
	 * Returns a reference to the left child of a node.
	 * 
	 * @param node
	 *            The node for which the left child should be returned
	 * @return A reference to a BinaryTreeNode that is the left child of the
	 *         node passed to this method
	 */
	public BinaryTreeNode<V> getLeft(BinaryTreeNode<V> node);

	/**
	 * Returns a reference to the right child of a node.
	 * 
	 * @param node
	 *            The node for which the right child should be returned
	 * @return A reference to a BinaryTreeNode that is the right child of the
	 *         node passed to this method
	 */
	public BinaryTreeNode<V> getRight(BinaryTreeNode<V> node);

	/**
	 * Returns a reference to the parent of a node.
	 * 
	 * @param node
	 *            The node for which the parent should be returned
	 * @return A reference to a BinaryTreeNode that is the parent of the node
	 *         passed to this method
	 */
	public BinaryTreeNode<V> getParent(BinaryTreeNode<V> node);

	/**
	 * Searches the entire tree for a node that contains the given value and
	 * returns a reference to that node.
	 * 
	 * @param value
	 *            The value to search for.
	 * @return A reference to a node that contains the value given.
	 */
	public BinaryTreeNode<V> findValue(V value);

}
