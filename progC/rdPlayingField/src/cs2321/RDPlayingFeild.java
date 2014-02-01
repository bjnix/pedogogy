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
		
		Graph <String, PathInfo> playingField = setup( "initialPlayingField.csv" );
		
		String jop = "Joplin MO";
		String fal = "Fall River MA";
		String grr = "Grand Rapids MI";
		String lon = "London ON";
		String gue = "Guelph ON";
		String lin = "Lincoln NE";
		
		System.out.println(totalPathDistance(shortestPath(playingField,jop,fal)));
		System.out.println(totalPathDistance(shortestPath(playingField,grr,lon)));
		System.out.println(totalPathDistance(shortestPath(playingField,gue,lon)));
		System.out.println(totalPathDistance(shortestPath(playingField,lin,lin)));
		
//		"Joplin MO", "Fall River MA"
//		Result: 1648.2
//
//		"Grand Rapids MI", "London ON"
//		Result: 589.0999999999999
//
//		"Guelph ON", "London ON"
//		Result: 244.3999999999998
//
//		"Lincoln NE", "Lincoln NE"
//		Result: 0.0
		
	}

	/**
	 * #TODO Project Part A
	 * Only implement setup()
	 */

	/**
	 * initialPlayingField has p - paths between d - locations,cities
	 * Note your time complexities in this file using c and d
	 */
	@TimeComplexity("O(p+d)")
	@SpaceComplexity("O(p+d)")
	public static Graph<String,PathInfo> setup( String aFileName ) {

		//TCJ: The scanner setup is O(1) time
		Scanner filein = null;
		try {
			filein = new Scanner(new File( aFileName ));
			filein.useDelimiter(",|\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Graph<String, PathInfo> playingField = new AdjListGraph<String, PathInfo>();

		//TCJ: this loop adds p edges and d vertices, each of which are O(1).
		while(filein.hasNext()){
			String line = filein.nextLine();
			String[] fields = line.split(",");
			String edgeName = fields[0];
			String vertexName0 = fields[1];
			String vertexName1 = fields[2];
			double distance = Double.parseDouble(fields[3]);

			Vertex<String> lVertex0 = playingField.insertVertex( vertexName0 );
			Vertex<String> lVertex1 = playingField.insertVertex( vertexName1 );
			playingField.insertEdge(lVertex0, lVertex1, new PathInfo( edgeName, distance ) );
		} // end while   

		return playingField;

	} // end setup()

	/*
	 * End Project Part A
	 */

	/**
	 * @author cjricker
	 * 
	 * Part B:
	 * 
	 * I chose an unordered PQ for my implementation of the shortestPath method because we expect there to be a lot of edges
	 * in our graph, and the time complexity for Dijkstra's Algorithm when using an unordered PQ is O(n^2 + m). This minimizes
	 * the operations required when the number of edges is much larger than the number of vertices.
	 * 
	 * The other choices were:
	 * Ordered PQ: O(n lg n + nm)
	 * Heap: O(n lg n + m lg n)
	 * 
	 * Returns the sequence containing the shortest path between the two given vertices.
	 * 
	 * @param g a Graph
	 * @param start is the start Vertex
	 * @param end is the end Vertex
	 * @returns a Sequence of PathInfo giving the shortest path between start and end
	 */
	@TimeComplexity("O(n^2+m)")
	@TimeComplexityAmortized("O(n^2+m)")
	@TimeComplexityExpected("O(n^2+m")
	@SpaceComplexity("O(m)")
	public static Sequence<PathInfo> shortestPath(Graph<String,PathInfo> g, String start, String end){

		Vertex<String> aa = g.insertVertex(start);
		Vertex<String> zz = g.insertVertex(end);
		Location<String,PathInfo> a = (Location<String,PathInfo>)aa; // Casting
		Location<String,PathInfo> z = (Location<String,PathInfo>)zz; // Casting
		UnorderedPQ<Double,Vertex<String>> q = new UnorderedPQ<Double,Vertex<String>>();
		
		//TCJ: Initialize PQ, O(n)
		for (Vertex<String> v : g.vertices()){ //Initialize the PQ
			Location<String,PathInfo> n = (Location<String,PathInfo>)v; // Casting
			if (v.element().equals(start)) // Start at start vertex, set initial distance to 0
				n.setDist(0.0);
			else
				n.setDist(Double.MAX_VALUE); // Set all other distances to infinity
			q.insert(n.getDist(), n); // Insert into the PQ
			n.setPQPos(q.getArray().last()); // Set position awareness
		}
		
		//TCJ: Outside loop executes O(n) times
		while (!q.isEmpty()){ // Outside loop
			
			//TCJ: Stuff inside outside loop, removeMin is O(n) time complexity
			Vertex<String> minn = q.removeMin().getValue(); // Casting
			Location<String,PathInfo> min = (Location<String,PathInfo>)minn; // Casting
			
			//TCJ: Inside loop executes O(sum of degree of all n) times, which is O(m)
			for (Edge<PathInfo> e : g.incidentEdges(min)){ // Inside loop
				Path<PathInfo> m = (Path<PathInfo>)e; // Casting
				
				//TCJ: Stuff inside inside loop O(1) time complexity
				Location<String,PathInfo> opposite = m.opposite(min);
				if (min.getDist() + m.element().distance() < opposite.getDist()){ // Relaxation
					opposite.setDist(min.getDist() + m.element().distance());
					opposite.setPrev(m);
					q.getArray().set(opposite.getPQPos(), new PQEntry<Double,Vertex<String>>(opposite.getDist(),opposite)); // Replace key in PQ
				}
				
			}
		}
		
		//TCJ: Executes once for each edge in the shortest path. Maximum length of path: O(m)
		//SCJ: Stores shortest path in a sequence. Maximum length of path: O(m)
		LinkedSequence<PathInfo> shortestPath = new LinkedSequence<PathInfo>();
		while (!z.equals(a)){
			shortestPath.addFirst(z.getPrev().element());
			z = (Location<String, PathInfo>) g.opposite(z, z.getPrev());
		}
		
		return shortestPath;
	}
	
	/**
	 * @author cjricker
	 * 
	 * @param path is a Sequence describing a path
	 * @returns double total distance of the the path
	 */
	@TimeComplexity("O(p)")
	@TimeComplexityAmortized("O(p)")
	@TimeComplexityExpected("O(p)")
	@SpaceComplexity("O(1)")
	public static double totalPathDistance(Sequence<PathInfo> path){
		
		//TCJ: Executes once for each edge in the given path.
		//SCJ: No variables are made that vary by input size.
		double dist = 0.0; // Distance starts at 0
		for (PathInfo info : path)
			dist += info.distance();
		return dist;
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