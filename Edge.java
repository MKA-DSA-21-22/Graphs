/* 
 * Edge Class for Kruskals Algorithm Graph
 */
public class Edge {
	private int vertex1;
	private int vertex2;
	private int weight;
	
	public Edge(int v1, int v2, int w) {
		vertex1 = v1; vertex2 = v2; weight = w;
	}
	
	private boolean hasVertex(int v) { return vertex1 == v || vertex2 == v; }
	
	public int getV1() { return vertex1; }
	
	public int getV2() { return vertex2; }
	
	public int getWeight() { return weight; }
	
	public boolean equals(Object o) {
		Edge e = (Edge) o;
		return e.hasVertex(vertex1) && e.hasVertex(vertex2) 
				&& e.getWeight() == weight;
		
	}
	
	public String toString() {
		String s = (vertex1 + 1) + " ---" + weight + "---> ";
		s = s + (vertex2 + 1);
		return s;
	}
}
