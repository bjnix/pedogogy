package cs2321;

import net.datastructures.Edge;
import net.datastructures.Vertex;
/**
 * Test driver class for AdjListGraph
 * see comments at bottom for "correct" output
 * 
 * creates 20 vertices
 * creates 40 edges
 * @author bjnix
 *
 */
public class AdjListTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//instantiate graph
		AdjListGraph<String, Integer> gr = new AdjListGraph<String, Integer>();
		
		//-------------------------------------------------------
		//fill out map
		
		//create 20 vertices
		Vertex<String> a = gr.insertVertex("happy");
		Vertex<String> b = gr.insertVertex("sad");
		Vertex<String> c = gr.insertVertex("fun");
		Vertex<String> d = gr.insertVertex("bad");
		Vertex<String> e = gr.insertVertex("gunny");
		Vertex<String> f = gr.insertVertex("lunny");
		Vertex<String> g = gr.insertVertex("lappy");
		Vertex<String> h = gr.insertVertex("poppy");
		Vertex<String> i = gr.insertVertex("poop");
		Vertex<String> j = gr.insertVertex("foo");
		Vertex<String> k = gr.insertVertex("fight");
		Vertex<String> l = gr.insertVertex("Band");
		Vertex<String> m = gr.insertVertex("BOrg");
		Vertex<String> n = gr.insertVertex("qwerty");
		Vertex<String> o = gr.insertVertex("hahoo");
		Vertex<String> p = gr.insertVertex("r2d2");
		Vertex<String> q = gr.insertVertex("foonda");
		Vertex<String> r = gr.insertVertex("hots");
		Vertex<String> s = gr.insertVertex("Colds");
		Vertex<String> t = gr.insertVertex("yup");
		//create 41 edges
		//edges from a to every other edge all of weight 3
		Edge<Integer> e1 = gr.insertEdge(a, b, 3);
		Edge<Integer> e2 = gr.insertEdge(a, c, 3);
		Edge<Integer> e3 = gr.insertEdge(a, d, 3);
		Edge<Integer> e4 = gr.insertEdge(a, e, 3);
		Edge<Integer> e5 = gr.insertEdge(a, f, 3);
		Edge<Integer> e6 = gr.insertEdge(a, g, 3);
		Edge<Integer> e7 = gr.insertEdge(a, h, 3);
		Edge<Integer> e8 = gr.insertEdge(a, i, 3);
		Edge<Integer> e9 = gr.insertEdge(a, j, 3);
		Edge<Integer> e10 = gr.insertEdge(a, k, 3);
		Edge<Integer> e11 = gr.insertEdge(a, l, 3);
		Edge<Integer> e12 = gr.insertEdge(a, m, 3);
		Edge<Integer> e13 = gr.insertEdge(a, n, 3);
		Edge<Integer> e14 = gr.insertEdge(a, o, 3);
		Edge<Integer> e15 = gr.insertEdge(a, p, 3);
		Edge<Integer> e16 = gr.insertEdge(a, q, 3);
		Edge<Integer> e17 = gr.insertEdge(a, r, 3);
		Edge<Integer> e18 = gr.insertEdge(a, s, 3);
		Edge<Integer> e19 = gr.insertEdge(a, t, 3);
		//4 more edges from a to b (total of 5)
		Edge<Integer> e20 = gr.insertEdge(a, b, 1);
		Edge<Integer> e21 = gr.insertEdge(a, b, 2);
		Edge<Integer> e22 = gr.insertEdge(a, b, 4);
		Edge<Integer> e23 = gr.insertEdge(a, b, 5);
		//edge from a to a
		Edge<Integer> e24 = gr.insertEdge(a, a, 6);
		//various other edges
		//6 edges from b
		Edge<Integer> e25 = gr.insertEdge(b, b, 20);
		Edge<Integer> e26 = gr.insertEdge(b, c, 20);
		Edge<Integer> e27 = gr.insertEdge(b, d, 20);
		Edge<Integer> e28 = gr.insertEdge(b, e, 20);
		Edge<Integer> e29 = gr.insertEdge(b, f, 20);
		Edge<Integer> e30 = gr.insertEdge(b, g, 30);
		//5 edges from f
		Edge<Integer> e31 = gr.insertEdge(f, c, 30);
		Edge<Integer> e32 = gr.insertEdge(f, d, 30);
		Edge<Integer> e33 = gr.insertEdge(f, e, 30);
		Edge<Integer> e34 = gr.insertEdge(f, f, 30);
		Edge<Integer> e35 = gr.insertEdge(f, g, 30);
		//4 edges from s
		Edge<Integer> e36 = gr.insertEdge(s, h, 600);
		Edge<Integer> e37 = gr.insertEdge(s, i, 600);
		Edge<Integer> e38 = gr.insertEdge(s, j, 600);
		Edge<Integer> e39 = gr.insertEdge(s, k, 600);
		//2 edges from n
		Edge<Integer> e40 = gr.insertEdge(n, l, 35);
		Edge<Integer> e41 = gr.insertEdge(n, m, 35);
		
		//-----------------------------------------
		//test map     
		//
		//note on toString() format:
		//vertices are in the format [Element,degree]
		//edges are in the format <Element;Vertex0,Vertex1>
		//--------------------------------------------------------------------------------proper result
		System.out.println("Check areAdjacent: " + gr.areAdjacent(a, b));//---------------- true
		System.out.println("Check areAdjacent: " + gr.areAdjacent(b, h));//-----------------false
		System.out.println("Check areAdjacent: " + gr.areAdjacent(b, b));//-----------------true
		System.out.println("Check numEdges: " + gr.numEdges());//---------------------------41
		System.out.println("Check numVertices: " + gr.numVertices());//---------------------20
		System.out.println("Check removeVertex: " + gr.removeVertex(t));//------------------yup
		System.out.println("Check numVertices:" + gr.numVertices());//----------------------19
		System.out.println("Check numEdges: " + gr.numEdges());//---------------------------40
		System.out.println("Check replace(Vertex): " + gr.replace(n, "quote"));//-----------querty
		System.out.println("Verify replace: " + n);//---------------------------------------[quote,3]
		System.out.println("Check removeEdge: " + gr.removeEdge(e40));//--------------------35
		System.out.println("Check numEdges: " + gr.numEdges());//---------------------------39
		System.out.println("Check numVertices: " + gr.numVertices());//---------------------19
		System.out.println("Check removeEdge: " + gr.removeEdge(e11));//--------------------3
		System.out.println("Check numEdges: " + gr.numEdges());//---------------------------38
		System.out.println("Check numVertices: " + gr.numVertices());//---------------------19
		System.out.println("Check replace(Edge): " + gr.replace(e40, 10000));//-------------35
		System.out.println("Verify replace: " + e40);//-------------------------------------<10000;[quote,2],[Band, 0]>
		System.out.println("Check edges: " + gr.edges());//---------------------------------[<3;[happy,22],[sad,11]>, <3;[happy,22],[fun,3]>, <3;[happy,22],[bad,3]>, <3;[happy,22],[gunny,3]>, <3;[happy,22],[lunny,7]>, <3;[happy,22],[lappy,3]>, <3;[happy,22],[poppy,2]>, <3;[happy,22],[poop,2]>, <3;[happy,22],[foo,2]>, <3;[happy,22],[fight,2]>, <3;[happy,22],[BOrg,2]>, <3;[happy,22],[quote,2]>, <3;[happy,22],[hahoo,1]>, <3;[happy,22],[r2d2,1]>, <3;[happy,22],[foonda,1]>, <3;[happy,22],[hots,1]>, <3;[happy,22],[Colds,5]>, <1;[happy,22],[sad,11]>, <2;[happy,22],[sad,11]>, <4;[happy,22],[sad,11]>, <5;[happy,22],[sad,11]>, <6;[happy,22],[happy,22]>, <20;[sad,11],[sad,11]>, <20;[sad,11],[fun,3]>, <20;[sad,11],[bad,3]>, <20;[sad,11],[gunny,3]>, <20;[sad,11],[lunny,7]>, <30;[sad,11],[lappy,3]>, <30;[lunny,7],[fun,3]>, <30;[lunny,7],[bad,3]>, <30;[lunny,7],[gunny,3]>, <30;[lunny,7],[lunny,7]>, <30;[lunny,7],[lappy,3]>, <600;[Colds,5],[poppy,2]>, <600;[Colds,5],[poop,2]>, <600;[Colds,5],[foo,2]>, <600;[Colds,5],[fight,2]>, <35;[quote,2],[BOrg,2]>]
		System.out.println("Check endVertices: " + gr.endVertices(e41));//------------------n,m couldn't get my toString method to print this I just used the debugger to make sure it worked
		System.out.println("Check incedentEdges: " + gr.incidentEdges(s));//----------------[<3;[happy,22],[Colds,5]>, <600;[Colds,5],[poppy,2]>, <600;[Colds,5],[poop,2]>, <600;[Colds,5],[foo,2]>, <600;[Colds,5],[fight,2]>]
		System.out.println("Check opposite: " + gr.opposite(f, e33));//---------------------[gunny,3]
		System.out.println("Check removeEdge: " + gr.removeEdge(e41));//--------------------35
		System.out.println("Check verticies: " + gr.vertices());//--------------------------[[foonda,1], [bad,3], [r2d2,1], [lunny,7], [quote,1], [poop,2], [lappy,3], [Colds,5], [fight,2], [foo,2], [happy,22], [hots,1], [BOrg,1], [poppy,2], [gunny,3], [fun,3], [hahoo,1], [Band,0], [sad,11]]
		System.out.println("Check removeVertex: " + gr.removeVertex(b));//------------------sad

	}

}
