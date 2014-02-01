package cs2321;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Sequence;
import net.datastructures.Vertex;

/**
 * 
 * @author cjricker
 * Tests AdjListGraph part B
 */
public class Test {
	
	public static Sequence<PathInfo> shortestPath(Graph<String,PathInfo> g, String start, String end){
		//COPY AND PASTE METHOD IN HERE
		return null;
	}

	public static double totalPathDistance(Sequence<PathInfo> path){
		//COPY AND PASTE METHOD IN HERE
		return 0;
	}
	
	public static void main(String[] args) {
		//instantiate graph
		AdjListGraph<String, PathInfo> graph = new AdjListGraph<String, PathInfo>();
		
		Vertex<String> a = graph.insertVertex("A");
		Vertex<String> b = graph.insertVertex("B");
		Vertex<String> c = graph.insertVertex("C");
		Vertex<String> d = graph.insertVertex("D");
		Vertex<String> e = graph.insertVertex("E");
		
		Edge<PathInfo> e1 = graph.insertEdge(a, a, new PathInfo("AA",1));
		Edge<PathInfo> e2 = graph.insertEdge(a, b, new PathInfo("AB",18));
		Edge<PathInfo> e3 = graph.insertEdge(a, c, new PathInfo("AC",1));
		Edge<PathInfo> e4 = graph.insertEdge(a, d, new PathInfo("AD",6));
		Edge<PathInfo> e5 = graph.insertEdge(a, e, new PathInfo("AE",9));
		Edge<PathInfo> e6 = graph.insertEdge(d, b, new PathInfo("DB",8));
		Edge<PathInfo> e7 = graph.insertEdge(c, d, new PathInfo("CD",3));
		Edge<PathInfo> e8 = graph.insertEdge(d, e, new PathInfo("DE",4));
		Edge<PathInfo> e9 = graph.insertEdge(e, b, new PathInfo("EB",5));
		Edge<PathInfo> e10 = graph.insertEdge(c, e, new PathInfo("CE",14));
		
		System.out.println("Shortest distance from A to B: " + totalPathDistance(shortestPath(graph,"A","B")));
		System.out.println("Shortest distance from E to C: " + totalPathDistance(shortestPath(graph,"E","C")));
		System.out.println("Shortest distance from A to E: " + totalPathDistance(shortestPath(graph,"A","E")));
		System.out.println("Shortest distance from E to A: " + totalPathDistance(shortestPath(graph,"E","A")));
		System.out.println("Shortest distance from B to C: " + totalPathDistance(shortestPath(graph,"B","C")));
	}
	
	/**
	 * Results
	 */
//	Shortest distance from A to B: ---------12.0
//	Shortest distance from E to C: ---------7.0
//	Shortest distance from A to E: ---------8.0
//	Shortest distance from E to A: ---------8.0
//  Shortest distance from B to C: ---------11.0

}
