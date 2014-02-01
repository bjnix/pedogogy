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
        public static Graph<String,PathInfo> setup( String aFileName ) {
        	
                /*#TODO: TCJ / SCJ! */
                /*# The scanner setup is O(1) time */
                Scanner filein = null;
                try {
                        filein = new Scanner(new File( aFileName ));
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
               
//                System.out.println("NumVertices " + playingField.numVertices());
//                System.out.println("NumEdges " + playingField.numEdges());
                
                return playingField;
        } // end setup()
        
        /*
         * End Project Part A
         */
        
        /*
         * #TODO Project Part B
         * Do not start until you have finished part A
         * implement
         *                 Sequence shortestPath(Graph<String,PathInfo> g,String start,String end)
         *                 double totalPathDistance(Sequence<PathInfo>)
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