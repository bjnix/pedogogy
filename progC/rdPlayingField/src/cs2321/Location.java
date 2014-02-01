package cs2321;

import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.InvalidKeyException;
import net.datastructures.Position;
import net.datastructures.Vertex;

/*
 * Vertex for PlayingField
 * You may add to this class
 */
@SpaceComplexity("O(m)")
public class Location<V,E> implements Vertex<String> {

	private LinkedSequence<Edge<E>> edges = new LinkedSequence<Edge<E>>();
	private double minDist = Double.MAX_VALUE;
	private Path<E> previous = null;
	private int degree = 0;
	private boolean placed = false;
	private V name;
	private Position<PQEntry<Double,Vertex<String>>> pqpos = null;

	public Location(V n){
		name = n;
	}

	public V setElement(V s){
		V temp = name;
		name = s;
		return temp;
	}

	public void addIncidentEdge(Edge<E> e){
		edges.addLast(e);
		degree++;
	}

	public void removeIncidentEdge(Position<Edge<E>> e){
		edges.remove(e);
		degree--;
	}

	public void removeIncidentEdge(Edge<E> e){
		int index = 0;
		for (Edge<E> edge: edges){
			if (edge.equals(e))
				edges.remove(index);
			index++;
		}
	}
	
	public boolean isPlaced(){
		return placed;
	}
	
	public void setPlaced(boolean b){
		placed = b;
	}

	public Position<Edge<E>> lastEdge(){ // Returns the last incident edge added
		return edges.last();
	}

	public Iterable<Edge<E>> getEdges(){
		return edges;
	}

	public int getDegree(){
		return degree;
	}

	public String element() {
		return name.toString();
	}

	public String toString(){
		return "[" + name.toString() + "," + degree + "]";
	}
	
	public void setPrev(Path<E> v){
		previous = v;
	}
	
	public Path<E> getPrev(){
		return previous;
	}
	
	public void setDist(double d){
		minDist = d;
	}
	
	public double getDist(){
		return minDist;
	}
	
	public void setPQPos(Position<PQEntry<Double,Vertex<String>>> p){
		pqpos = p;
	}
	
	public Position<PQEntry<Double,Vertex<String>>> getPQPos(){
		return pqpos;
	}

	/**
	 * MAP METHODS
	 */

	@Override
	public Iterable<Entry<Object, Object>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Object key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Object> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(Object key, Object value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
