import java.io.File;
import java.util.*;
// Topological Sort

public class UnweightedGraph {
	private ArrayList<ArrayList<Integer>> inDegree;
	private ArrayList<ArrayList<Integer>> outDegree;
	private int[][] adjMatrix;
	private int numVerts;
	
	public UnweightedGraph(String fileName, int verts) {
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
		// making out degree
		outDegree = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numVerts; i++) {
			ArrayList<Integer> ins = new ArrayList<Integer>();
			for (int j = 0; j < numVerts; j++) {
				if (adjMatrix[i][j] == 1) ins.add(j);
			}
			outDegree.add(ins);
		}
		inDegree = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numVerts; i++) {
			ArrayList<Integer> ins = new ArrayList<Integer>();
			for (int j = 0; j < numVerts; j++) {
				if (adjMatrix[j][i] == 1) ins.add(j);
			}
			inDegree.add(ins);
		}
	}
	private void makeInDegree() {
		inDegree = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < numVerts; i++) {
			ArrayList<Integer> ins = new ArrayList<Integer>();
			for (int j = 0; j < numVerts; j++) {
				if (adjMatrix[j][i] == 1) ins.add(j);
			}
			inDegree.add(ins);
		}
	}
	public void printInDegree() {
		for (int i = 0; i < numVerts; i++) {
			System.out.println((i) +": "+ inDegree.get(i));
		}
	}
	
	private int getNextEmpty(ArrayList<Integer> sort) {
		for (int i = 0; i < inDegree.size(); i++) {
			// if nothing in indegree and not in sort return
			boolean empty = inDegree.get(i).size() == 0;
			boolean inSort = false;
			for (int j = 0; j < sort.size(); j++) 
				if (sort.get(j) == i) inSort = true;
			if (empty && !inSort) return i;
		}
		return -1;
	}
	
	private void removeAll(int num) {	
		for (int i = 0; i < inDegree.size(); i++) {
			for (int j = inDegree.get(i).size()-1; j >=0; j--) {				
				if (inDegree.get(i).get(j) == num) { 
					inDegree.get(i).remove((Integer) num); 
				}
			}
		}
	}
	
	
	public ArrayList<Integer> topologicalSort(){
		ArrayList<Integer> sort = new ArrayList<Integer>();
		int currNode = getNextEmpty(sort);
		while (sort.size() < numVerts && currNode >= 0) {
			sort.add(currNode);
			removeAll(currNode);
			currNode = getNextEmpty(sort);			
		}
		makeInDegree();
		return sort;
	}
	
	
	public int[] shortestPath(int curr) {
		int[] counts = new int[numVerts];
		for (int i = 0; i < numVerts; i++) counts[i] = -1;
		counts[curr] = 0; // mark start node as visited 
		ArrayList<Integer> q = new ArrayList<Integer>();
		q.add(curr); // add start node to the q
		while (q.size() > 0) { //while Q is not empty
			curr = q.remove(0); // remove the head of the q
			for (int next: outDegree.get(curr)) { // iterate over all neighbors
				if (counts[next] < 0) { // if unvisited
					q.add(next); // add to queue
					counts[next] = counts[curr] + 1; //mark as visited
				}
			}
		}
		return counts;
	}
	
	public static void main(String[] args) {
		UnweightedGraph g = new UnweightedGraph("graph.txt", 7);
		//g.printInDegree();
		//System.out.println(g.topologicalSort());
		//g.printInDegree();
		g.shortestPath(2);
	}
}
