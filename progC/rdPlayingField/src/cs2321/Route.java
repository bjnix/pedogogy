package cs2321;

import java.awt.Polygon;

import net.datastructures.Position;

public class Route implements Infoable{

	private City start;
	private City end;
	private String name;
	private Position<Route> position;
	private Position<Route> incidentEdgePos;
	private Position<Route> incidentEdgePos2;
	private double distance;
	private Polygon line;

	public Route(City a, City b, int dist){
		start = a;
		end = b;
		double dx = start.getCoordinates()[0]-end.getCoordinates()[0];
		double dy = start.getCoordinates()[1]-end.getCoordinates()[1];
		distance = Math.sqrt(dx*dx+dy*dy);
	}
	
	public void setName(String s){
		name = s;
	}
	
	public void setPolygon(Polygon p){
		line = p;
	}
	
	public Polygon getPolygon(){
		return line;
	}

	public void setPosition(Position<Route> p){
		position = p;
	}
	
	public double getDist(){
		return distance;
	}
	
	public void setDist(int dist){
		distance = dist;
	}

	public void setPositions(Position<Route> p1, Position<Route> p2){
		incidentEdgePos = p1;
		incidentEdgePos2 = p2;
	}

	public Position<Route> edge1(){
		return incidentEdgePos;
	}

	public Position<Route> edge2(){
		return incidentEdgePos2;
	}

	public Position<Route> getPosition(){
		return position;
	}

	public City[] endpoints(){

		City[] temp = {start,end};
		return temp;
	}

	public String toString(){
		return "<" + start + ", " + end + ">"; 
	}

	public City opposite(City a){

		if (start.equals(a))
			return end;
		else if (end.equals(a))
			return start;
		else
			return null;
	}
	
	public String showInfo(){
		return name + "";
	}

	public int[] getCoordinates() {
		int[] temp = {start.getCoordinates()[0],start.getCoordinates()[1],end.getCoordinates()[0],end.getCoordinates()[1]};
		return temp;
	}

}
