
package cs2321;

import java.io.*;
import java.util.Scanner;
import net.datastructures.*;

/**
 * @author rpastel
 *
 * initialPlayingField.csv has p - paths between d - locations
 * Read all TODO sections (before complaining to the list about errors)
 * Note your time / space complexities in this file using p and d
 */
public class RDPlayingFeild {
	
	public static void main(String[] args){
		Graph <String, PathInfo> playingField = setup();
		//#TODO: write test code
				
	}
	
	/**
	 * #TODO Project Part A
	 * Only implement setup()
	 */
	
	/**
	 * initialPlayingField has p - paths between d - locations,cities
	 * Note your time complexities in this file using c and d
	 */
	public static Graph<String,PathInfo> setup() {
		/*#TODO: TCJ / SCJ! */
		/*# The scanner setup is O(1) time */
		Scanner filein = null;
		try {
			filein = new Scanner(new File("initialPlayingField.csv"));
			filein.useDelimiter(",|\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		/*
		 * TODO: Pick your implementation and un-comment
		 */
		Graph<String, PathInfo> playingField = new AdjListGraph<String, PathInfo>();
		//# Graph<String, PathInfo> playingField = new EdgeListGraph<String, PathInfo>();
		//# Graph<String, PathInfo> playingField = new AdjMatrixGraph<String, PathInfo>();

		//# TODO: Select a Map / Uncomment the following line
		//# Map<String, Vertex<String>> locations = new // Some type of Map;

		for(String line = filein.nextLine(); filein.hasNext(); line = filein.nextLine()){ 
			String[] fields = line.split(",");
			String edgeName = fields[1];
			String vertexName0 = fields[2];
			String vertexName1 = fields[3];
			double distance = Double.parseDouble(fields[4]);
			
			locations.put(vertexName0, new Location(vertexName0));
			Vertex<String> vertex0 = locations.get(vertexName0);
			locations.put(vertexName1, new Location(vertexName1));
			Vertex<String> vertex1 = locations.get(vertexName0);
			
			playingField.insertEdge(vertex0, vertex1, new PathInfo(edgeName,distance));
		} // end for 	
		return playingField;
	} // end setup()
	
	/*
	 * End Project Part A
	 */
	
	/*
	 * #TODO Project Part B
	 * Do not start until you have finished part A
	 * implement
	 * 		Sequence shortestPath(Graph<String,PathInfo> g,String start,String end)
	 * 		double totalPathDistance(Sequence<PathInfo>)
	 */
	/*
	 * @param g a Graph
	 * @param start is the start Vertex
	 * @param end is the end Vertex
	 * @returns a Sequence of PathInfo giving the shortest path between start and end
	 */
	public static Sequence<PathInfo> shortestPath(Graph<String,PathInfo> g, String start, String end){
		// #TODO: Complete and correct shortestPath()
		/*#TODO: TCJ / SCJ! */
		return null;
	}
	
	/*
	 * @param path is a Sequence describing a path
	 * @returns double total distance of the the path
	 */
	public static double totalPathDistance(Sequence<PathInfo> path){
		// #TODO: Complete and correct totalPathDistance()
		/*#TODO: TCJ / SCJ! */
		return 0;
	}
	/*
	 * End Project Part B
	 */
	
	/*
	 * #TODO Project Part C
	 * Finish the JApplet AgentDemoApplet 
	 * and the JPanel PlayingFieldConstructionPanel
	 */
	/*
	 * End Project Part C
	 */
	
} //end class PlayingField