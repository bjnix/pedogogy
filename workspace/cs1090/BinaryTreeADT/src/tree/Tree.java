/**
 * 
 */
package tree;

import java.util.Iterator;

/**
 * @author carr
 *
 */
public interface Tree<E> {
	int size();
	boolean isEmpty();
	Iterator<Position<E>> iterator();
	Iterable<Position<E>> positions();
	
	Position<E> root();
	Position<E> parent(Position<E> p);
	Iterable<Position<E>> children(Position<E> p);
	
	boolean isInternal(Position<E> p);
	boolean isExternal(Position<E> p);
	boolean isRoot(Position<E> p);
	
	E replace(Position<E> p, E object);
}
