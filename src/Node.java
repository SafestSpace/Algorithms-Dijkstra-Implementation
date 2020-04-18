import java.util.ArrayList;

public class Node {
	int value;
	ArrayList<Edge> edges;
	boolean seen;
	int distance;
	public Node(int value) {
		edges = new ArrayList<>();
		this.value = value;
		seen = false;
	}
	public void add (Edge edge) {
		edges.add(edge);
	}
	@Override
	//debugging use
	public String toString() {
		String listOfEdges= "";
		//for (int i = 0; i < edges.size(); i++) {
		//	listOfEdges = listOfEdges + " ," + Integer.toString(edges.get(i).end);
		//}
		return "Node: "+Integer.toString(value); //+" Edges: "+listOfEdges;
	}
}
