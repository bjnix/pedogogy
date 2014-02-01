package cs2321;

import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.InvalidKeyException;
import net.datastructures.Position;
import net.datastructures.Vertex;

@SpaceComplexity("O(1)")
public class Path<E> implements Edge<E> {

	private E path;
	private Location start;
	private Location end;
	private Position<Edge<E>> position;
	private Position<Edge<E>> incidentEdgePos;
	private Position<Edge<E>> incidentEdgePos2;

	public Path(Vertex<String> a, Vertex<String> b, E e){
		path = e;
		start = (Location)a;
		end = (Location)b;
	}

	public E element() {
		return path;
	}

	public void setPosition(Position<Edge<E>> p){
		position = p;
	}

	public void setPositions(Position<Edge<E>> p1, Position<Edge<E>> p2){
		incidentEdgePos = p1;
		incidentEdgePos2 = p2;
	}

	public Position<Edge<E>> edge1(){
		return incidentEdgePos;
	}

	public Position<Edge<E>> edge2(){
		return incidentEdgePos2;
	}

	public Position<Edge<E>> getPosition(){
		return position;
	}

	public E setElement(E e){
		E temp = path;
		path = e;
		return temp;
	}

	public Location[] endpoints(){

		Location[] temp = {start,end};
		return temp;
	}

	public String toString(){
		return "<"+ path.toString() + ";" + start + end + ">"; 
	}

	public Location opposite(Location a){

		if (start.equals(a))
			return end;
		else if (end.equals(a))
			return start;
		else
			return null;
	}

	/**
	 * MAP METHODS
	 */

	public Iterable<Entry<Object, Object>> entries() {
		return null;
	}

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
