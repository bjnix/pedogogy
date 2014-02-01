package BinaryTree;

/**
 * This is the interface for the binary tree node. You are not allowed to modify
 * this file.
 * 
 * DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE!!!
 * 
 * @author Bryan Franklin
 * 
 * @param <V>
 *            The type of data held in each node.
 */
public interface BinaryTreeNodeInterface<V> {

	public BinaryTreeNodeInterface<V> getParent();

	public BinaryTreeNodeInterface<V> getLeft();

	public BinaryTreeNodeInterface<V> getRight();

	public void setLeft(BinaryTreeNodeInterface<V> node);

	public void setRight(BinaryTreeNodeInterface<V> node);

	public void setParent(BinaryTreeNodeInterface<V> node);

	public void setValue(V value);

	public V getValue();
}
