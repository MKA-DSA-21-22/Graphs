
import java.io.File;
import java.util.*;

public class WeightedDirGraph {
	private int[][] adjMatrix;
	private int numVerts;
	
	public WeightedDirGraph(String fileName, int verts) {
		File f = new File(fileName);
		numVerts = verts;
		adjMatrix = new int[verts][verts];
		// rows outgoing 
		// cols incoming
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

	private boolean allKnown(boolean[] known) {
		for (int i =0; i<numVerts; i++) {
			if (!known[i]) {return false;}
		}
		return true;
	}
	
	private int nextShortest(int[] dist, boolean[] known) {
		int smallest = Integer.MAX_VALUE;
		int node = -1;
		for (int i =0; i<numVerts; i++) {
			if (dist[i]<smallest && !known[i]) {node = i; smallest = dist[i];}
		}
		return node;
	}
	
	public int[] shortestPath(int curr) {	
		int[] dist = new int[numVerts];
		boolean[] known = new boolean[numVerts];
		for (int i = 0; i<numVerts; i++) {
			dist[i] = Integer.MAX_VALUE;
			known[i] = false;
		}
		dist[curr] = 0;
		while (!allKnown(known)) {
			curr = nextShortest(dist, known); 
			known[curr]= true; 
			for (int i = 0; i < numVerts; i++) {
				if (adjMatrix[curr][i]!=0 && !known[i]) {
					int newDist = dist[curr] + adjMatrix[curr][i]; 
					if (newDist < dist[i]) {
						dist[i] = newDist; 
					}
				}
			}
		}
		for (int d: dist) System.out.println(d);
		return dist; 
		
		
	}
	
	public static void main(String[] args) {
		WeightedDirGraph g = new WeightedDirGraph("wgraph.txt", 7);
		g.shortestPath(0);
	}
}
