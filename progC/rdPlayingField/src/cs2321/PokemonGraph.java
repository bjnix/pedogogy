package cs2321;
import net.datastructures.InvalidPositionException;

/**
 * An adjacency list graph specially designed for Cities and Routes.
 * Essentially the same as AdjListGraph but with no generics or casting.
 * 
 * @author cjricker
 *
 */
public class PokemonGraph{
	
	private HashMap<String,City> vertices = new HashMap<String,City>(); // Individual vertices use a boolean to know if they are in the graph
	private LinkedSequence<Route> edges = new LinkedSequence<Route>();

	
	public boolean areAdjacent(City u, City v)
			throws InvalidPositionException {
		
		if (!u.isPlaced() || !v.isPlaced()) // Checks if the given vertices are in the graph
			throw new InvalidPositionException();

		City c = null; // Lower degree vertex
		City d = null; // Higher degree vertex

		if (u.getDegree() <= v.getDegree()){ // Find which Vertex is lower degree
			c = u;
			d = v;
		}
		else{
			c = v;
			d = u;
		}

		for (Route route : c.getEdges()){ // Check all incident edges on the vertex
			if (route.opposite(c).element().equals(d.element()))
				return true;
		}

		return false;
	}


	public Iterable<Route> edges() {
		return edges;
	}


	public City[] endVertices(Route e) throws InvalidPositionException {
		
		return e.endpoints();
	}

	public Iterable<Route> incidentEdges(City v)
			throws InvalidPositionException {
		return v.getEdges();
	}

	public Route insertEdge(City u, City v)
			throws InvalidPositionException {
		
		if (!u.isPlaced() || !v.isPlaced()) // Checks if the given vertices are in the graph
			throw new InvalidPositionException();
		
		int[] uc = u.getCoordinates();
		int[] vc = v.getCoordinates();
		
		int dist = (int)Math.sqrt((uc[0]-vc[0])*(uc[0]-vc[0]) + (uc[1]-vc[1])*(uc[1]-vc[1])); // Calculate distance between points

		Route edge = new Route(u,v,dist);
		edges.addLast(edge);
		edge.setPosition(edges.last()); // Set position awareness

		u.addIncidentEdge(edge);

		if (!u.equals(v)) // If not creating a self-loop
			v.addIncidentEdge(edge);

		edge.setPositions(u.lastEdge(),v.lastEdge()); // Set position awareness

		return edge;
	}

	public City insertVertex(City o) {
		
		City temp = null;

		if (vertices.get(o.element()) == null){	
			vertices.put(o.element(), o);
			o.setPlaced(true);
		}
		else
			temp = vertices.get(o.element());

		return temp;
	}

	public int numEdges() {	
		return edges.size();
	}

	public int numVertices() {
		return vertices.size();
	}

	public City opposite(City v, Route e)
			throws InvalidPositionException {
		return e.opposite(v);
	}

	public Route removeEdge(Route e) throws InvalidPositionException {
		
		if (e == null)
			throw new InvalidPositionException();

		edges.remove(e.getPosition()); // Remove the edge from the main list of edges

		City[] endpoints = e.endpoints();
		
		if (!endpoints[0].isPlaced() || !endpoints[1].isPlaced())
			throw new InvalidPositionException();

		endpoints[0].removeIncidentEdge(e.edge1()); // Remove location aware edges from the list in each endpoint
		if (!endpoints[0].equals(endpoints[1])) // If not removing a self-loop
			endpoints[1].removeIncidentEdge(e.edge2());

		return e;
	}

	public City removeVertex(City v) throws InvalidPositionException {
		
		for (Route route: v.getEdges()){ // For each incident edge

			edges.remove(route.getPosition()); // Remove it from the main list of edges

			City[] endpoints = route.endpoints();

			endpoints[0].removeIncidentEdge(route.edge1()); // Remove all incident edges in a position-aware manner
			if (!endpoints[0].equals(endpoints[1])) // If vertex does not have a self-loop
				endpoints[1].removeIncidentEdge(route.edge2());

		}

		vertices.remove(v.element());
		v.setPlaced(false);

		return v;
	}

	public String replace(City p, String o) throws InvalidPositionException {
		
		String oldV = p.element();
		City temp = p;
		temp.setName(o);
		vertices.put(o, temp);
		vertices.remove(oldV);

		return oldV;
	}

	public double replace(Route p, int o)
			throws InvalidPositionException {
		
		double temp = p.getDist();
		p.setDist(o);
		return temp;
	}

	public Iterable<City> vertices() {
		
		return vertices.values();
	}

}
