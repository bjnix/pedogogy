package tree;

import java.util.Iterator;

/**
 * @author carr
 *
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	private int size = 0;
	private BTPosition<E> root;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public BTPosition<E> addRoot(E i) {
		root = new BinaryTreeNode<E>(i);
		size = 1;
		return root;
	}
	
	public Position<E> root() {
		if (root != null)
			return root;
		else
			return null;
		
	}
	
	public Position<E> parent(Position<E> n) {
		return ((BTPosition<E>) n).parent();
	}
		
	public boolean isInternal(Position<E> n) {
		return ((BTPosition<E>) n).left() != null || ((BTPosition<E>) n).right() != null;
	}
	
	public boolean isExternal(Position<E> n) {
		return ((BTPosition<E>) n).left() == null && ((BTPosition<E>) n).right() == null;
	}
	
	public boolean isRoot(Position<E> n) {
		return n == root;
	}
	
	public BTPosition<E> addLeft(BTPosition<E> n,E i) {
		BTPosition<E> left = new BinaryTreeNode<E>(i);
		n.setLeft(left);
		
		return left;
	}
	
	public BTPosition<E> addRight(BTPosition<E> n,E i) {
		BTPosition<E> right = new BinaryTreeNode<E>(i);
		n.setRight(right);
		
		return right;
	}
	
	public E replace(Position<E> n, E i) {
		E old = n.element();
		((BTPosition<E>) n).setElement(i);
		return old;
	}
	
	public BTPosition<E> left(BTPosition<E> n) {
		return ((BTPosition<E>) n).left();
	}
	
	public BTPosition<E> right(BTPosition<E> n) {
		return ((BTPosition<E>) n).right();
	}
	
	public boolean hasLeft(BTPosition<E> n) {
		return ((BTPosition<E>) n).left() != null;
	}
	
	public boolean hasRight(BTPosition<E> n) {
		return ((BTPosition<E>) n).right() != null;
	}

	@Override
	public Iterable<Position<E>> children(Position<E> p) {
		return null;
	}

	@Override
	public Iterator<Position<E>> iterator() {
		return null;
	}

	@Override
	public Iterable<Position<E>> positions() {
		return null;
	}

		
}
