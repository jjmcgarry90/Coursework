//Collaboration Statement: This is solely my work.

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class represent a typical graph which can hold any number
 * of vertices, which in turn hold edge and neighbor references.
 * 
 * @author Jessie McGarry
 *
 */
public class AdjacencyListGraph {
	/**
	 * All vertices in the graph are stored in this list.
	 */
	private ArrayList<Vertex> vertices;
	
	/**
	 * Sets up graph and vertex list.
	 */
	public AdjacencyListGraph() {
		vertices = new ArrayList<Vertex>();
	}
	
	/**
	 * Adds a vertex to the graph, duplicate
	 * vertices are not allowed.
	 * @param v - the vertex to be added
	 */
	public void addVertex(Vertex v) {
		if(!this.contains(v))
			vertices.add(v);	
	}
	
	/**
	 * Attempts to find a vertex with the same name
	 * as the specified vertex.
	 * @param v - the vertex to check for.
	 * @return - true if the specified vertex was found,
	 * 			false otherwise.
	 */
	public boolean contains(Vertex v) {
		boolean found = false;
		for(int i = 0; i < vertices.size(); i++) 
			if(vertices.get(i).getName().equals(v.getName()))
				found = true;
		return found;
	}
	
	/**
	 * Finds the specified vertex in the graph and returns it.
	 * @param v - the vertex to search for.
	 * @return The specified vertex if it is found, null otherwise.
	 */
	public Vertex find(Vertex v) {
		int i = 0;
		boolean found = false;
		while(!found && i < vertices.size()) {
			if(vertices.get(i).getName().equals(v.getName()))
				found = true;
			i++;
		}
		
		if(!found)
			return null;
		
		return vertices.get(i-1);
	}
	
	/**
	 * Forms an edge between two vertices.
	 * @param v1 - any vertex (preferably in the graph)
	 * @param v2 - any vertex (preferably in the graph)
	 * @param distance - the distance between the vertices.
	 */
	public void makeNeighbors(Vertex v1, Vertex v2, int distance) {
		v1.addNeighbor(v2, distance);
		v2.addNeighbor(v1, distance);
	}
	
	/**
	 * Performs a depth first search in the graph for the specified
	 * destination.
	 * @param origin - the vertex to start at.
	 * @param destination - the vertex to search for.
	 * @return - true if the destination was found, false otherwise.
	 */
	public boolean dfs(Vertex origin, Vertex destination) {
		origin = this.find(origin);
		Stack<Vertex> toVisit = new Stack<Vertex>();
		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		boolean found = false;
		toVisit.add(origin);
		while (!toVisit.isEmpty()) {
			Vertex current = toVisit.pop();
			visited.add(current);
			if(current.getName().equals(destination.getName()))
				found = true;
			System.out.println("Visited " + current);
			ArrayList<Vertex> neighbors = current.getNeighbors();
			int index = neighbors.size()-1;
			while(!neighbors.isEmpty() && index >= 0) {
				if (!visited.contains(neighbors.get(index)) && 
						!toVisit.contains(neighbors.get(index))) 
						toVisit.add(neighbors.get(index));	
				index--;
			}
		}
		return found;
	
	}
	
	/**
	 * Uses prim's algorithm to find the minimum spanning tree (MST)
	 * of the graph.
	 * @return A new adjacency list graph representing the MST of
	 * 			the original graph.
	 */
	public AdjacencyListGraph prim() {
		if(vertices.isEmpty())
			return null;
		
		AdjacencyListGraph newGraph = new AdjacencyListGraph();
		newGraph.addVertex(new Vertex(vertices.get(0).getName()));
		
		ArrayList<Vertex> visited = new ArrayList<Vertex>();
		visited.add(vertices.get(0));
		
		while(!(vertices.size() == visited.size())) {
			Integer min = visited.get(0).closestNeighborDistance();
			Vertex closest = visited.get(0);
			Vertex cursor = visited.get(0);
			for(int i = 0; i < visited.size(); i++)
				if (visited.get(i).closestNeighborDistance() <= min) {
					min = visited.get(i).closestNeighborDistance();
					cursor = visited.get(i);
					closest = visited.get(i).closestNeighbor();
				}
			visited.add(closest);
			Vertex v1 = new Vertex(closest.getName());
			newGraph.addVertex(v1);
			newGraph.makeNeighbors(newGraph.find(cursor), v1, cursor.closestNeighborDistance());
		}
			
		return newGraph;
	}
	
	/**
	 * Prints all the vertices in the graph using the Vertex's 
	 * toString method.
	 */
	public String toString() {
		int i = 0;
		String info = "";
		while(!vertices.isEmpty() && i < vertices.size()) {
			info += vertices.get(i) + "\n";
			i++;
		}
		return info;
	}	
}
