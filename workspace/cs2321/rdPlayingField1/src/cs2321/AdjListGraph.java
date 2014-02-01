package cs2321;

import net.datastructures.*;

/**
 * @author Chris Rickerd
 * CS2321 Project A
 * 
 * I chose to use an Adjacency List with position-aware entries because it allows for fast addition and removal
 * of vertices and edges during gameplay, as well as low space complexity overall.
 */
@SpaceComplexity("O(n+m)")
public class AdjListGraph<V, E> implements Graph<V, E> {

	private HashMap<V,Location<V,E>> vertices = new HashMap<V,Location<V,E>>(14002); // Individual vertices use a boolean to know if they are in the graph
	private LinkedSequence<Edge<E>> edges = new LinkedSequence<Edge<E>>();

	public AdjListGraph() {

	}

	/**
	 * Checks to see if two given vertices are adjacent
	 * 
	 * @param u one given vertex
	 * @param v the other given vertex
	 * @return whether the two vertices are adjacent
	 */
	@TimeComplexity("O(m)")
	@TimeComplexityAmortized("O(m)")
	@TimeComplexityExpected("O(m)")
	@SpaceComplexity("O(1)")
	public boolean areAdjacent(Vertex<V> u, Vertex<V> v)
	throws InvalidPositionException {

		//TCJ: Must check through the incident edges of one of the given vertices, which can be up to m edges.
		//SCJ: No extra containers are created that vary by size of the input.
		
		//If this were an adjacency matrix, this method would cost O(1), but other methods such as addVertex would cost O(n^2).
		//Not using an adjacency matrix also cuts down on the space complexity of the playing field.
		//If this were an edge list, this method would cost O(m), rather than O(degree(u || v)).
		//This is the only method that could be improved using another graph structure, and it is not worth the O(n^2) insert
			//and remove vertex methods that come along with it.
		
		if (!((Location<V,E>)u).isPlaced() || !((Location<V,E>)v).isPlaced()) // Checks if the given vertices are in the graph
			throw new InvalidPositionException();

		Location<V,E> c = null; // Lower degree vertex
		Location<V,E> d = null; // Higher degree vertex

		if (((Location<V,E>)u).getDegree() < ((Location<V,E>)v).getDegree()){ // Find which Vertex is lower degree
			c = (Location<V,E>)u;
			d = (Location<V,E>)v;
		}
		else{
			c = (Location<V,E>)v;
			d = (Location<V,E>)u;
		}

		for (Edge<E> path : c.getEdges()){ // Check all incident edges on the vertex
			if (((Path<E>)path).opposite(c).element().equals(d.element()))
				return true;
		}

		return false;
	}

	/**
	 * Returns an iterable list of all edges in the graph
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Iterable<Edge<E>> edges() {

		return edges;
	}

	/**
	 * Returns the endpoint vertices of a given edge
	 * 
	 * @param e the given edge
	 * @return the endpoints of the given edge
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Location[] endVertices(Edge<E> e) throws InvalidPositionException {

		return ((Path<E>)e).endpoints();
	}


	/**
	 * Returns an iterable list of the incident edges of a given vertex
	 * 
	 * @param v the given vertex
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v){

		return ((Location<V,E>)v).getEdges();
	}

	/**
	 * Inserts an edge if both given vertices already exist in the given graph
	 * 
	 * @param u the first given vertex
	 * @param v the second given vertex
	 * @param o the data to store in the edge
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
	throws InvalidPositionException {

		//TCJ: All adds are addLast to a linkedSequence, and thus constant.
		//SCJ: No extra containers are created that vary by size of input
		
		if (!((Location<V,E>)u).isPlaced() || !((Location<V,E>)v).isPlaced()) // Checks if the given vertices are in the graph
			throw new InvalidPositionException();

		Path<E> edge = new Path(u,v,o);
		edges.addLast(edge);
		edge.setPosition(edges.last()); // Set position awareness

		((Location<V,E>)u).addIncidentEdge(edge);

		if (!u.equals(v)) // If not creating a self-loop
			((Location<V,E>)v).addIncidentEdge(edge);

		edge.setPositions(((Location<V,E>)u).lastEdge(),((Location<V,E>)v).lastEdge()); // Set position awareness

		return edge;

	}

	/**
	 * Inserts a vertex with the given element
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		
		//TCJ: Adding and removing to and from a hash table is O(n), but amortized is O(1).
		//SCJ: No extra containers are created that vary by the input size.
		
		// If this was an adjacency matrix, this method would cost O(n^2).

		Location<V,E> temp = new Location<V,E>(o);

		if (vertices.get(o) == null){	
			vertices.put(o, temp);
			temp.setPlaced(true);
		}
		else
			temp = vertices.get(o);

		return (Vertex<V>)temp;
	}

	/**
	 * Returns the total number of edges in the graph
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public int numEdges() {

		return edges.size();
	}

	/**
	 * Returns the total number of vertices in the graph
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public int numVertices() {

		return vertices.size();
	}

	/**
	 * Returns the vertex opposite a given vertex on a given edge
	 * 
	 * @param v the given vertex
	 * @param e the given edge
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
	throws InvalidPositionException {

		return (Vertex<V>)((Path<E>)e).opposite((Location)v);
	}

	/**
	 * 
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public E removeEdge(Edge<E> e) throws InvalidPositionException {

		//TCJ: All removes are position-aware from linked sequences
		//SCJ: No extra containers which vary by input size are used
		
		if (e == null)
			throw new InvalidPositionException();

		edges.remove(((Path<E>)e).getPosition()); // Remove the edge from the main list of edges

		Location<V,E>[] endpoints = ((Path<E>)e).endpoints();
		
		if (!endpoints[0].isPlaced() || !endpoints[1].isPlaced())
			throw new InvalidPositionException();

		endpoints[0].removeIncidentEdge(((Path<E>)e).edge1()); // Remove location aware edges from the list in each endpoint
		if (!endpoints[0].equals(endpoints[1])) // If not removing a self-loop
			endpoints[1].removeIncidentEdge(((Path<E>)e).edge2());

		return e.element();
	}

	/**
	 * 
	 */
	@TimeComplexity("O(m+n)")
	@TimeComplexityAmortized("O(m)")
	@TimeComplexityExpected("O(m)")
	@SpaceComplexity("O(1)")
	public V removeVertex(Vertex<V> v) throws InvalidPositionException {

		//TCJ: This method must search through all the incident edges of the given vertex, which has an upper bound of m
		//TCJ: All subsequent removes are position-aware or from a hash table, which is O(n).
		//TCJ: Amortized and expected cost of adding to and removing from a hash table is O(1).
		//SCJ: No extra containers which vary by input size are used
		
		// If this was an edge list, this method would cost O(m) instead of O(degree(v)).
		// If this was an adjacency matrix, this method would cost O(n^2).
		
		for (Edge<E> edge: ((Location<V,E>)v).getEdges()){ // For each incident edge

			edges.remove(((Path<E>)edge).getPosition()); // Remove it from the main list of edges

			Location<V,E>[] endpoints = (Location<V,E>[])((Path<E>)edge).endpoints();

			endpoints[0].removeIncidentEdge(((Path<E>)edge).edge1()); // Remove all incident edges in a position-aware manner
			if (!endpoints[0].equals(endpoints[1])) // If vertex does not have a self-loop
				endpoints[1].removeIncidentEdge(((Path<E>)edge).edge2());

		}

		vertices.remove(v.element());
		((Location<V,E>)v).setPlaced(false);

		return v.element();
	}

	/**
	 * Replaces the element at the given edge with the given value and returns the old value
	 */
	@TimeComplexity("O(1)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public E replace(Edge<E> p, E o) throws InvalidPositionException {

		return ((Path<E>)p).setElement(o);
	}

	/**
	 * Replaces the element at the given vertex with the given value and returns the old value
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(1)")
	@TimeComplexityExpected("O(1)")
	@SpaceComplexity("O(1)")
	public V replace(Vertex<V> p, V o) throws InvalidPositionException {

		//TCJ: The new vertex must be added and the old vertex must be removed from a hash table.
		//TCJ: This add and remove is O(n), but is amortized and expected O(1).
		//SCJ: No extra containers which vary by input size are used

		V oldV = p.element();

		Location<V,E> temp = (Location<V,E>)p;

		temp.setElement(o);
		vertices.put(o, temp);
		vertices.remove(oldV);

		return oldV;
	}

	/**
	 * Returns an iterable list of the vertices in the graph
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityAmortized("O(n)")
	@TimeComplexityExpected("O(n)")
	@SpaceComplexity("O(n)")
	public Iterable<Vertex<V>> vertices() {

		//TCJ: Each vertex must be added to an iterable list
		//SCJ: Each vertex must be copied into an iterable list

		LinkedSequence<Vertex<V>> temp = new LinkedSequence<Vertex<V>>();

		for (Location<V,E> vertex : vertices.values())
			temp.addLast((Vertex<V>)vertex);

		return temp;
	}
	
	//TESTING
	//TESTING
	//TESTING
	//TESTING
	//TESTING
	//TESTING
	//TESTING
	//TESTING
	public String toString(){
		String temp = "";
		temp += "[";
		for (Location<V,E> location : vertices.values()){
			temp += location.element() + ", ";
		}
		temp += "]\n[";
		for (Edge<E> edge : edges){
			temp += edge + ", ";
		}
		temp += "]";
		return temp;
	}

}
