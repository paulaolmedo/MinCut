/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template FILE, choose Tools | Templates
 * and open the template in the editor.
 */
package MinCut;
import UndirectedGraph.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
/**
 *
 * @author paula
 */
public class MinCut {
    private UndirectedGraph uGraph;
    private final File file;
    
    public MinCut(){
        uGraph = new UndirectedGraph<>();
        file = new File("graph.txt");
    }
    public MinCut(String fileName){
        uGraph = new UndirectedGraph();
        file = new File(fileName);
    }
    
    public Graph<Integer> createGraph(){
        List<String> line = null;
        int n;
        boolean [][] m;
        String [] aux;
        try{
            line = Files.readAllLines(Paths.get("graph.txt"));
        }
        catch(IOException e){
            System.out.println("Un error ha ocurrido: " + e.getLocalizedMessage());
        }
    
        n = line.size();
        m = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            aux = line.get(i).split(" ");
            for (int j = 0; j < aux.length; j++) {
            m[i][Integer.parseInt(aux[j])-1] = true;
            }
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = i+1; j < m[0].length; j++) {
                if (m[i][j]==true) {
                    uGraph.addArc(i+1, j+1, true);
                }
            }
        }
    
        return uGraph;
    }
    
    
}
