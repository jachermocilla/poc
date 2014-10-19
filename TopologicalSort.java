/**
 * A Java implementation of Topological Sorting
 *
 * @author JAC Hermocilla (jachermocilla@gmail.com)
 *
 */ 
import java.util.*;


/*
 * Adjacency list graph representation. 
 */
class Digraph{
  int vertexCount;
  int edgeCount;
  Hashtable<String,ArrayList<String>> vertices = new Hashtable();

  /**
   * Add a directed edge from vertex U to vertex V to the graph
   */
  void addEdge(String U, String V){
    if (!vertices.containsKey(U))
    {
      vertices.put(U, new ArrayList());
      vertexCount++;
    }
    if (V != null) 
    {
      if (!vertices.containsKey(V))
      {
        vertices.put(V, new ArrayList());
        vertexCount++;
      }
      ArrayList<String> adjacentToU = vertices.get(U);
      adjacentToU.add(V);
    } 
    edgeCount++;
  }

  /**
   * Utility method to check if the graph was properly constructed
   */
  public void showGraph()
  {
    Enumeration<String> e = vertices.keys();
    System.out.println("Vertex Count: " + vertexCount);
    System.out.println("Edge Count: " + edgeCount);
    while(e.hasMoreElements()){
      String U = (String) e.nextElement();
      System.out.print(U+"-->");
      ArrayList adjacentList = vertices.get(U);
      Iterator<String> i = adjacentList.iterator();
      while (i.hasNext())
      {
        String V = (String) i.next();
        System.out.print("[" + V + "]");
      }
      System.out.println();
    }
  }


  /**
   * Returns the in-degree of vertex U
   */
  int inDegree(String U)
  {
    int inCount=0;
    Enumeration<String> e = vertices.keys();
    while(e.hasMoreElements()){
      String V = (String) e.nextElement();
      ArrayList adjacentList = vertices.get(V);
      Iterator<String> i = adjacentList.iterator();
      while (i.hasNext())
      {
        String W = (String) i.next();
        if (U == W)
        {
          inCount++;
        }
      }
    }
    return inCount;
  }


  /*
   * Algorithm from Goodrich/Tamassia
   */
  void topSort()
  {
    Stack<String> s = new Stack();
    Hashtable<String,Integer> incounter = new Hashtable();
    Integer tmp;
    Enumeration<String> e = vertices.keys();
    while(e.hasMoreElements())
    {
      String U = (String) e.nextElement();
      incounter.put(U,new Integer(tmp=inDegree(U)));
      if (tmp.intValue() == 0)
      {
        s.push(U);
      }
    }
    while(!s.empty())
    {
      String U = s.pop();
      System.out.println(U); //Show it!!
      ArrayList adjacentList = vertices.get(U);
      Iterator<String> iter = adjacentList.iterator();
      while (iter.hasNext())
      {
        String W = (String) iter.next();
        tmp = incounter.get(W);
        incounter.put(W,new Integer(tmp.intValue()-1));
        if ((tmp.intValue()-1) == 0)
          s.push(W);
      }
    }
  }
}

public class TopologicalSort{
  public static void main(String args[])
  {
    Digraph g = new Digraph();
    g.addEdge("CMSC 123","CMSC 125");
    g.addEdge("CMSC 125","CMSC 137");
    g.addEdge("CMSC 57","CMSC 123");
    g.addEdge("CMSC 11","CMSC 21");
    g.addEdge("CMSC 21","CMSC 123");
    g.addEdge("CMSC 11","CMSC 57");
    g.addEdge("MATH 17","CMSC 56");
    g.addEdge("MATH 17","CMSC 11");
    g.addEdge("MATH 17","MATH 26");
    g.addEdge("CMSC 56","CMSC 57");

    g.showGraph();
    g.topSort();
  }

}