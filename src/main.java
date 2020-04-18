import java.util.Scanner;
import java.io.File;
import java.net.URL;
import java.util.regex.*;
import java.util.ArrayList;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			File input = new File("\\Algorithms Dijkstra Implementation\\src\\1000.txt");

			Scanner scanner = new Scanner(input);

			//Regex patterns used to find input size, nodes, and edges
			String size = "n=([0-9]*)";
			String node = "^[0-9]+";
			String edge = "^[\\s]+([0-9]+)[\\s]+([0-9]+)";
			
			Pattern sizePattern = Pattern.compile(size);
			Pattern edgePattern = Pattern.compile(edge);
			Pattern nodePattern = Pattern.compile(node);
			
			
			//Loop to fill nodeList
			ArrayList <Integer> finalDistances = new ArrayList<>();
			MinHeap list = null;
			Node currentNode = null;
			Edge currentEdge = null;
			boolean[] SPT = new boolean[1000];
			while(scanner.hasNextLine()) {
				
				String line = scanner.nextLine();
				Matcher match1 = sizePattern.matcher(line);
				Matcher match2 = nodePattern.matcher(line);
				Matcher match3 = edgePattern.matcher(line);
				//catches size
				if(match1.find()) {
					int numberOfNodes = Integer.parseInt(match1.group(1));
					//Holds all the nodes to process with Dijkstra's Algo
					list = new MinHeap(numberOfNodes);
					
				}
				//catches node
				if (match2.find()) {
					if (!list.contains(Integer.parseInt(line))) {
						currentNode = new Node(Integer.parseInt(line));
						currentNode.seen = false;
						currentNode.distance = Integer.MAX_VALUE;
						list.insert(currentNode);
						SPT[currentNode.value]=false;
					}
					else {
						currentNode = list.getNode(Integer.parseInt(line));
					}
				}
				//catches edge
				if(match3.find()) {
					if (!list.contains(Integer.parseInt(match3.group(1)))) {
						Node temp = new Node(Integer.parseInt(match3.group(1)));
						temp.seen = false;
						temp.distance = Integer.MAX_VALUE;
						list.insert(temp);
						currentEdge = new Edge(Integer.parseInt(match3.group(1)), Integer.parseInt(match3.group(2)));
						currentNode.add(currentEdge);
						SPT[temp.value]=false;
					} else {
						currentEdge = new Edge(Integer.parseInt(match3.group(1)), Integer.parseInt(match3.group(2)));
						currentNode.add(currentEdge);
					}
				}
			}
			//boolean[] SPT = new boolean[list.size()];
			Node source = list.getNode(0);
			source.distance=0;
			list.updateNode(source);
			int treeLength = 0;
			while(!list.isEmpty()) {
				currentNode = list.extractMin();
				currentNode.seen = true;
				SPT[currentNode.value] = true;
				ArrayList<Edge> edgeList = currentNode.edges;
				for (int i = 0; i < edgeList.size(); i++) {
					currentEdge = edgeList.get(i);
					if (SPT[currentEdge.end] == false) {
						int calcDist = currentNode.distance + currentEdge.weight;
						if (calcDist < list.getNode(currentEdge.end).distance) {
							list.decreaseKey(list.getNode(currentEdge.end), calcDist);
						}
					}
				}
				System.out.println(currentNode.toString() + " with distance " +currentNode.distance);
				finalDistances.add(currentNode.distance);
			}
			for (int j = 0; j < finalDistances.size(); j++) treeLength+= finalDistances.get(j);
			System.out.println("Tree length is: "+treeLength);
		}
		
		//fail safe
		catch (Exception ex){
			System.out.println("Shit broke!");
		}
	}

}
