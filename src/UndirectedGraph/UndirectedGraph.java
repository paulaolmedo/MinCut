package UndirectedGraph;

import java.util.LinkedList;


public class UndirectedGraph<T> extends Graph<T> 
{
    /**
     * Crea un grafo no dirigido, con lista de vértices vacía, lista de arcos 
     * vacía y sin permitir arcos paralelos.
     */
    public UndirectedGraph() 
    {
    }
    
    /**
     * Crea un grafo no dirigido con lista de vértices vacía y lista de arcos 
     * vacía. El grafo permite arcos paralelos si el parámetro p es true, y no 
     * los permite si p es false.
     * @param p true: se permiten arcos paralelos.
     */
    public UndirectedGraph(boolean p)
    {
        super(p);
    }
            

    /**
     * Crea un grafo no dirigido cuya lista de vértices será <b>v</b> y cuya 
     * lista de arcos será <b>a</b>, sin permitir arcos paralelos. El método no 
     * controla si las listas de entrada contienen objetos válidos. Si alguna de 
     * las dos listas de entrada es null, la lista correspondiente se creará 
     * vacía.
     * @param v la lista de vértices a almacenar en el grafo.
     * @param a la lista de arco a almacenar en el grafo.
     */
    public UndirectedGraph(LinkedList< Node<T> > v, LinkedList< Arc<T> > a) 
    {
        super(v, a);
    }

    /**
     * Crea un grafo no dirigido cuya lista de vértices será <b>v</b> y cuya 
     * lista de arcos será <b>a</b>. El parámetro p indica si el grafo aceptará 
     * arcos paralelos (p = true) o no (p = false). El método no controla si las 
     * listas de entrada contienen objetos válidos. Si alguna de las dos listas 
     * de entrada es null, la lista correspondiente se creará vacía.
     * @param v la lista de vértices a almacenar en el grafo.
     * @param a la lista de arco a almacenar en el grafo.
     * @param p true: el grafo acepta arcos paralelos.
     */
    public UndirectedGraph(LinkedList< Node<T> > v, LinkedList< Arc<T> > a, boolean p) 
    {
        super(v, a, p);
    }
   
    /**
     * Crea un arco no dirigido con in como primer vértice y en como segundo 
     * vértice. El peso del arco será w. No comprueba si las referencias in y en
     * son null.
     * @param in el vértice inicial.
     * @param en el vértice final. 
     * @param w el peso del arco
     * @return el arco creado.
     */
    @Override
    public Arc<T> createArc(Node <T> in, Node <T> en, int w)
    {
        return new UndirectedArc(in, en, w);
    }
    private void contraction(Arc<T> arc){
        Node<T> begin = arc.getInit();
        Node<T> end = arc.getEnd();
        
        Node<T> aux;
        
        for(Arc<T> arcEnd: end.getArcs()){
            if(arcEnd.equals(arc)) {
                begin.getArcs().remove(arcEnd);}
            else{
              if(arcEnd.getInit().equals(end)){
                    aux = arcEnd.getEnd();
                }
              else{
                  aux = arcEnd.getInit();
              }
              this.addArc(begin, aux);
              aux.getArcs().remove(arcEnd);
            }
           this.edges.remove(arcEnd);
        }
        begin.getArcs().remove(arc);
        edges.remove(arc);
        vertices.remove(end);
    }
    private void contraction(){
        contraction(this.getRandomArc());
    }
    public int minimumCut(int iterations){
      int minCut = this.countEdges();
      
        for (int i = 0; i < iterations; i++) {
        try{
          UndirectedGraph copy = (UndirectedGraph) this.clone();
          while(copy.countNodes()>2){
             copy.contraction();
          }
          if (copy.countEdges()<minCut) {
              minCut = copy.countEdges();
          }

        }
        catch(CloneNotSupportedException e){
          System.out.println("Error: " + e.getLocalizedMessage());
      }
        }
        return minCut;
    }
}
