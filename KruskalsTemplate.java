/*
 * Kruskals Algorithm Graph
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class KGraph {
	private int verts;
	private ArrayList<Edge> edges;
	private ArrayList<ArrayList<Integer>> adj;
	
	public KGraph(int verts) {
		this.verts = verts;
		edges = new ArrayList<Edge>();
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < verts; i++) adj.add(new ArrayList<Integer>());
	}
	
	public KGraph(String fileName, int verts) {
		File f = new File(fileName);
		this.verts = verts;
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < verts; i++) adj.add(new ArrayList<Integer>());
		edges = new ArrayList<Edge>();
		try {
			Scanner in = new Scanner(f);
			for (int i = 0; i < verts; i++) {
				for (int j = 0; j < verts; j++) {
					int weight = in.nextInt();
					if (weight != 0) {
						Edge e = new Edge(i, j, weight);
						if (!edges.contains(e)) {
							edges.add(e);
						}
						adj.get(i).add(j);
						adj.get(j).add(i);
					}
				}
			}
			in.close();
		}
		catch(Exception E) { System.out.println(E); }
	}
	
	public void printEdges() {
		for (Edge e: edges) System.out.println(e);
	}
	
	private boolean addEdge(Edge e) {
		if (!edges.contains(e)) {
			edges.add(e);
			adj.get(e.getV1()).add(e.getV2());
			adj.get(e.getV2()).add(e.getV1());
			return true;
		}
		return false;
	}
	
	private boolean removeEdge(Edge e) {
		int v1 = e.getV1();
		int v2 = e.getV2();
		adj.get(v1).remove((Integer) v2);
		adj.get(v2).remove((Integer) v1);
		return edges.remove(e);
	}
	
	private int numEdges() { return edges.size(); }
	
	private void sortEdges() {
		//TO DO
	}
	
	
	public boolean containsCycle() {
		//TO DO
	}
	
	public KGraph Kruskals() {
		//TO DO
	}
	
	public static void main(String[] args) {
		KGraph kg = new KGraph("wugraph1.txt", 5);
		kg.printEdges();
		KGraph mst = kg.Kruskals();
		System.out.println("MST: ");
		mst.printEdges();
		
	}

	

}