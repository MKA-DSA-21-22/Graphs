/* Graph.java for implementing Prim's Algorithm
 * to find the Minimum Spanning Tree of a graph.
 * DSA Spring 2021
 */

import java.util.*;
import java.io.*;
public class WUGraph {
	private int[][] adjMatrix;
	private int numVerts;
	
	public WUGraph(String fileName, int verts) {
		File f = new File(fileName);
		numVerts = verts;
		adjMatrix = new int[verts][verts];
		try {
			Scanner in = new Scanner(f);
			for (int i = 0; i < verts; i++) {
				for (int j = 0; j < verts; j++) {
					adjMatrix[i][j] = in.nextInt();
				}
			}
			in.close();
		}
		catch(Exception E) { System.out.println(E); }
	}
	
	
	public void printGraph() {
		for (int i = 0; i < numVerts; i++) {
			for (int j = 0; j < numVerts; j++) {
				System.out.print(adjMatrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public int[][] Prims() { 
		//returns the adjacency matrix of the compete MST 
		
	}


}
