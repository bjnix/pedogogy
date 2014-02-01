package tree;

public interface BTPosition<E> extends Position<E> {

	void setElement(E e);
	void setParent(BTPosition<E> p);
	void setLeft(BTPosition<E> l);
	void setRight(BTPosition<E> r);
	
	BTPosition<E> parent();
	BTPosition<E> left();
	BTPosition<E> right();
}
