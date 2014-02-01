package BinaryTree;

/**
 * This class implements a generic binary tree node for use in a class that
 * implements the BinaryTree<V> interface.
 * 
 * Modifications to this file are allowed, but should not be necessary.
 * 
 * @author Bryan Franklin
 * 
 * @param <V>
 *            The type of the data stored in each node
 */
public class BinaryTreeNode<V> {

	private BinaryTreeNode<V> parent;
	private BinaryTreeNode<V> left;
	private BinaryTreeNode<V> right;
	private V value;

	public BinaryTreeNode(V val, BinaryTreeNode<V> p) {
		value = val;
		parent = p;
	}

	public BinaryTreeNode<V> getParent() {
		return parent;
	}

	public BinaryTreeNode<V> getLeft() {
		return left;
	}

	public BinaryTreeNode<V> getRight() {
		return right;
	}

	public void setLeft(BinaryTreeNode<V> node) {
		left = node;
	}

	public void setRight(BinaryTreeNode<V> node) {
		right = node;
	}

	public void setParent(BinaryTreeNode<V> node) {
		parent = node;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public V getValue() {
		return value;
	}

}
