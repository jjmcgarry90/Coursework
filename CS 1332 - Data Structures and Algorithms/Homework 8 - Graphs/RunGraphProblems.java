//Collaboration Statement: This is solely my work.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class read from a format specific file to form an adjacency
 * list graph. It then test various methods of the AdjacencyListGraph
 * class.
 * @author Jessie McGarry
 *
 */
public class RunGraphProblems {
	/**
	 * Reads from a file where each line is in the format
	 * "Vertex1Name Vertex2Name EdgeLength".
	 * @param args - the file to read from
	 * @throws FileNotFoundException - if there is no file to read from.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File(args[0]);
		Scanner scan = new Scanner(input);
		AdjacencyListGraph graph = new AdjacencyListGraph();
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] strings = line.split(" ");
			Vertex v1 = new Vertex(strings[0]);
			Vertex v2 = new Vertex(strings[1]);
			graph.addVertex(v1);
			graph.addVertex(v2);
			graph.makeNeighbors(graph.find(v1), graph.find(v2), 
									Integer.parseInt(strings[2]));
		}

		System.out.println(graph.toString());
		System.out.println();
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Enter name of Origin Vertex: ");
		String v1 = scan2.next();
		System.out.println("Enter name of Target Vertex: ");
		String v2 = scan2.next();
		System.out.println(graph.dfs(new Vertex(v1), new Vertex(v2)));
		
		System.out.println();
		System.out.println(graph.prim());
			
	}
}

