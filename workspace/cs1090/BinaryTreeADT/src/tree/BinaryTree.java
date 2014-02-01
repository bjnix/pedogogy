/**
 * 
 */
package tree;

/**
 * @author carr
 *
 */
public interface BinaryTree<E> extends Tree<E> {
	
	BTPosition<E> left(BTPosition<E> p);
	BTPosition<E> right(BTPosition<E> p);
	boolean hasLeft(BTPosition<E> p);
	boolean hasRight(BTPosition<E> p);
}
