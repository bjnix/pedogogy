package cs2321;

import java.util.Random;
import net.datastructures.Sequence;

public class AI {

	public AI(){

	}

	public Sequence<Route> shortestPath(PokemonGraph graph, City start, City end){

		UnorderedPQ<Double,City> q = new UnorderedPQ<Double,City>();

		//TCJ: Initialize PQ, O(n)
		for (City city : graph.vertices()){ //Initialize the PQ

			city.setPrev(null,1);

			if (city.equals(start)) // Start at start vertex, set initial distance to 0
				city.setDist(0.0);
			else
				city.setDist(Double.MAX_VALUE); // Set all other distances to infinity
			q.insert(city.getDist(), city); // Insert into the PQ
			city.setPQPos(q.getArray().last()); // Set position awareness
		}

		//TCJ: Outside loop executes O(n) times
		while (!q.isEmpty()){ // Outside loop

			//TCJ: Stuff inside outside loop, removeMin is O(n) time complexity
			City min = q.removeMin().getValue();

			//TCJ: Inside loop executes O(sum of degree of all n) times, which is O(m)
			for (Route route : graph.incidentEdges(min)){ // Inside loop

				//TCJ: Stuff inside inside loop O(1) time complexity
				City opposite = route.opposite(min);
				if (min.getDist() + route.getDist() < opposite.getDist()){ // Relaxation
					opposite.setDist(min.getDist() + route.getDist());
					opposite.setPrev(route,1);
					q.getArray().set(opposite.getPQPos(), new PQEntry<Double,City>(opposite.getDist(),opposite)); // Replace key in PQ
				}

			}
		}

		//TCJ: Executes once for each edge in the shortest path. Maximum length of path: O(m)
		//SCJ: Stores shortest path in a sequence. Maximum length of path: O(m)
		LinkedSequence<Route> shortestPath = new LinkedSequence<Route>();
		while (!end.equals(start)){
			shortestPath.addFirst(end.getPrev(1));
			end = graph.opposite(end, end.getPrev(1));
		}

		return shortestPath;
	}

	public Sequence<Route> DFS(City start, City end){

		LinkedSequence<Route> path = new LinkedSequence<Route>();
		java.util.Stack<City> Q = new java.util.Stack<City>();
		Q.push(start);

		while(!Q.isEmpty()){
			City w = Q.pop();
			if (w.equals(end)){
				while( w != start ){
					path.addFirst(w.getPrev(3));
					w=w.getPrev(3).opposite(w);
				}
				return path;
			}
			for(Route e: w.getEdges()){
				City other = e.opposite(w);
				if(!other.wasVisted(3)){
					other.setPrev(e,3);
					other.setVisited(true,3);
					Q.push(other);		
				}
			}
		}
		
		return path;

	}

	//--------------------
	//Breadth First Search
	//--------------------


	public Sequence<Route> BFS(City start, City end){

		LinkedSequence<Route> path = new LinkedSequence<Route>();
		java.util.Queue<City> Q = new java.util.PriorityQueue<City>();
		Q.add(start);

		while(!Q.isEmpty()){
			City w = Q.poll();
			if (w.equals(end)){
				while( w != start ){
					path.addFirst(w.getPrev(2));
					w=w.getPrev(2).opposite(w);
				}
				return path;
			}
			for(Route e: w.getEdges()){
				City other = e.opposite(w);
				if(!other.wasVisted(2)){
					other.setPrev(e,2);
					other.setVisited(true,2);
					Q.offer(other);		
				}
			}
		}
		return null;
	}
	
	public Sequence<Route> random(City start, City end){
		
		LinkedSequence<Route> path = new LinkedSequence<Route>();
		
		while (!start.equals(end)){
			
			Random random = new Random();
			
			LinkedSequence<Route> routes = (LinkedSequence<Route>)start.getEdges();
			Route route = routes.get(random.nextInt(routes.size()));
			
			path.addLast(route);
			start = route.opposite(start);
		}
		
		return path;
		
	}

}
