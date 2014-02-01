package net.datastructures;

public interface DiGraph<V, E> extends Graph<V, E> {
	/** tests whether an edge is directed */	
	public boolean isDirected(Edge<E> e);
	/** Insert and return a new directed edge with origin v and destination w,
	 *  storing element o */
	public Edge<E> insertDirectedEdge(Vertex<V> v, Vertex<V> w, E o);
	/** Return the edges with origin v */
	public Iterable<Edge<E>> outEdges(Vertex<V> v);
	/** Return the edges with destination v */
	public Iterable<Edge<E>> inEdges(Vertex<V> v);
	/** Returns whether the vertices are adjacent by an undirected edge, or
	 *  by a directed edge from v to w.*/
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w);
	/** Returns the endvertices of a vertex as an array of length 2.  If e is
	 *  directed, the A[0] should be the origin, and A[1] should be the
	 *  destination. */
	public Vertex[] endVertices(Edge<E> e) throws InvalidPositionException;
}
