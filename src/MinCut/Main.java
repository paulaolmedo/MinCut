/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinCut;

import UndirectedGraph.UndirectedGraph;
import java.io.IOException;

/**
 *
 * @author paula
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MinCut readGraph = new MinCut("graph.txt");
        UndirectedGraph <Integer> uGraph = (UndirectedGraph<Integer>) readGraph.createGraph();
        int iterations;
        System.out.println("Number of iterations: ");
        iterations = System.in.read();
        
        System.out.println("Minimum cut is: " + uGraph.minimumCut(iterations));
    }
    
}
