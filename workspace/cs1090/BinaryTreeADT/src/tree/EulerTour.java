/**
 * 
 */
package tree;

import tree.BTPosition;
import tree.TourResult;

/**
 * @author carr
 *
 */
public abstract class EulerTour<E,R> {
	protected BinaryTree<E> tree;
	public abstract R execute(BinaryTree<E> T);
	protected void init(BinaryTree<E> T) { tree = T; }
	protected R eulerTour(BTPosition<E> p) {
		TourResult<R> r = new TourResult<R>();
		visitLeft(p, r);
		if (tree.hasLeft(p)) { 
			r.left=eulerTour(tree.left(p)); 
		}
		visitBelow(p, r);
		if (tree.hasRight(p)) { 
			r.right=eulerTour(tree.right(p)); 
			}
		visitRight(p,r);
		return r.out;
	}
	protected void visitLeft(Position<E> v, TourResult<R> r) {}
	protected void visitBelow(Position<E> v, TourResult<R> r) {}
	protected void visitRight(Position<E> v, TourResult<R> r) {}
}

