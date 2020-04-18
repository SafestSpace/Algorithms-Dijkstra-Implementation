public class Edge {
	int end;
	int weight;
	public Edge(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	@Override
	//debugging use
	public String toString() {
		return "Edge: "+end+" Weight: "+weight;
	}
}
