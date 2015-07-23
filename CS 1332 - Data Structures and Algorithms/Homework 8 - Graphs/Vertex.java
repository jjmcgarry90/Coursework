//Collaboration Statement: This is solely my work.

import java.util.ArrayList;

/**
 * This class represents a vertex to be used in a typical graph.
 * References to neighboring edges and vertices are stored in ArrayLists.
 * 
 * 
 * @author Jessie McGarry
 *
 */

public class Vertex {
	/**
	 * Identifies the vertex.
	 */
	private String name;
	
	/**
	 * Stores references to all the neighbors of this vertex.
	 */
	private ArrayList<Vertex> neighbors;
	
	/**
	 * Stores references to all the edges of this vertex.
	 */
	private ArrayList<Integer> edges;
	
	/**
	 * Constructor sets name and sets up neighbor and edge lists.
	 * @param name - the name of the vertex
	 */
	public Vertex(String name) {
		this.name = name;
		neighbors = new ArrayList<Vertex>();
		edges = new ArrayList<Integer>();
	}
	
	/**
	 * Adds a neighbor and edge to each ArrayList. A neighbor and 
	 * the edge to it are kept in corresponding indices in the lists.
	 * @param newNeigh - the new neighbor
	 * @param distance - the length of the edge to that neighbor
	 */
	public void addNeighbor(Vertex newNeigh, int distance) {
		neighbors.add(newNeigh);
		edges.add(distance);	
	}
	
	/**
	 * @return - the name of this vertex
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return - a list containing all of this vertex's neighbors
	 * @return
	 */
	public ArrayList<Vertex> getNeighbors() {
		return neighbors;
	}
	
	/**
	 * Finds the minimum of all this vertex's edges and returns
	 * the corresponding neighbor.
	 * @return the closest neighbor
	 */
	public Vertex closestNeighbor() {
		if(neighbors.isEmpty())
			return null;
		int min = edges.get(0);
		for(int i = 0; i < edges.size(); i++)
			if(edges.get(i) < min)
				min = i;
		return neighbors.get(min);
		
	}
	
	/**
	 * Finds the smallest edge in the list of edges and returns it.
	 * @return - the distance to the closest neighbor.
	 */
	public Integer closestNeighborDistance() {
		if(neighbors.size() == 0)
			return null;
		int min = edges.get(0);
		for(int i = 0; i < edges.size(); i++)
			if(edges.get(i) < min)
				min = edges.get(i);
		return min;
	}
	
	/**
	 * @return The list of edges of this vertex.
	 */
	public ArrayList<Integer> getEdges() {
		return edges;
	}
	
	/**
	 * An informative toString method, prints the name of the
	 * vertex and all adjacent neighbors and edges.
	 */
	public String toString() {
		String neighs = "";	
		int i = 0;
		while(!neighbors.isEmpty() && i < neighbors.size()) {
			neighs+= "(" + neighbors.get(i).getName() + ", "+
			edges.get(i) + ") ";
			i++;
		}
		if(neighs == "")
			neighs = "none";
		return "Vertex: " + name + "   Adjacent Vertices: " + neighs;
	}
}
