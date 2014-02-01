package cs2321;

import java.awt.Polygon;
import net.datastructures.Position;

public class City implements Comparable, Infoable{
	
	public static int ARRAY_SIZE = 14;
	
	private LinkedSequence<Route> edges = new LinkedSequence<Route>();
	private double minDist = Double.MAX_VALUE;
	private Route[] previous = new Route[ARRAY_SIZE];
	private int degree = 0;
	private boolean placed = false;
	private boolean selected = false;
	private boolean[] visited = new boolean[ARRAY_SIZE];
	private String city_ID;
	private int item;
	private Position<PQEntry<Double, City>> pqpos = null;
	private int x;
	private int y;
	private KnownCities knownCities = new KnownCities();
	


	public City(int x, int y, String n){
		
		city_ID = knownCities.pallet_town.contains(x, y)? "Pallet Town" : n;
		
		this.x = x;
		this.y = y;
	}
	
	public int[] getCoordinates(){
		int[] temp = {x,y};
		return temp;
	}
	
	public void select(){
		selected = !selected;
	}

	public String setName(String s){
		String temp = city_ID;
		city_ID = s;
		return temp;
	}
	
	public String getName(){
		return city_ID;
	}

	public void addIncidentEdge(Route e){
		edges.addLast(e);
		degree++;
	}

	public void removeIncidentEdge(Position<Route> e){
		edges.remove(e);
		degree--;
	}

	public void removeIncidentEdge(Route e){
		int index = 0;
		for (Route edge: edges){
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

	public Position<Route> lastEdge(){ // Returns the last incident edge added
		return edges.last();
	}

	public Iterable<Route> getEdges(){
		return edges;
	}

	public int getDegree(){
		return degree;
	}

	public String element() {
		return city_ID;
	}

	public String toString(){
		return showInfo();
	}
	
	public void setPrev(Route v, int num){
		previous[num] = v;
	}
	
	public Route getPrev(int num){
		return previous[num];
	}
	
	public void setDist(double d){
		minDist = d;
	}
	
	public double getDist(){
		return minDist;
	}
	
	public void setPQPos(Position<PQEntry<Double, City>> position){
		pqpos = position;
	}
	
	public Position<PQEntry<Double, City>> getPQPos(){
		return pqpos;
	}
	
	public String showInfo() {
		String temp = city_ID + "";
		return temp;
	}
	
	public boolean equals(City c){
		return x==c.getCoordinates()[0]&&y==c.getCoordinates()[1];
	}

	public boolean wasVisted(int num) {
		return visited[num];
	}
	
	public void setVisited(boolean b, int num){
		visited[num] = b;
	}

	public int compareTo(Object o) {
		
		if (o instanceof City)
			return compareTo(this.getName().compareTo(((City)o).getName()));
		
		else return this.getName().compareTo(o.toString());
	}

}
