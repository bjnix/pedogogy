package tree;

/**
 * @author carr
 *
 */
public class BinaryTreeNode<E> implements BTPosition<E> {
	
	E element;
	private BinaryTreeNode<E> parent;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	BinaryTreeNode(E e, BinaryTreeNode<E> p, BinaryTreeNode<E> l, BinaryTreeNode<E> r) {
		element = e;
		parent = p;
		left = l;
		right = r;
	}
	
	BinaryTreeNode(E e) {
		element = e;
		parent = null;
		left = null;
		right = null;
	}
	
	public E element() {
		return element;
	}
	
	@Override
	public void setLeft(BTPosition<E> l) {
		left = (BinaryTreeNode<E>) l;
		left.setParent(this);
	}

	@Override
	public void setParent(BTPosition<E> p) {
		parent = (BinaryTreeNode<E>) p;
	}

	@Override
	public void setRight(BTPosition<E> r) {
		right = (BinaryTreeNode<E>) r;
		right.setParent(right);
	}

	@Override
	public BTPosition<E> left() {
		return left;
	}

	@Override
	public BTPosition<E> parent() {
		return parent;
	}

	@Override
	public BTPosition<E> right() {
		return right;
	}

	@Override
	public void setElement(E e) {
		element = e;
	}
	
}
