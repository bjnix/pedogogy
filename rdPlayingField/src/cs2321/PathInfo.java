package cs2321;

public class PathInfo {
	private String name;
	private double distance;
	PathInfo(String name, double distance){
		this.distance = distance;
		this.name = name;
	}
	public String name(){return name;}
	public double distance(){return distance;}
	public void setName(String name){this.name = name;}
	public void setDistance(double distance){this.distance = distance;}
}
