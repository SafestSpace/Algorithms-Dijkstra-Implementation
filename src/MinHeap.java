public class MinHeap {
	private int capacity;
	private int currentSize;
	private Node[] heap;
	private int[] locations;
	//private HashMap<Integer, Integer> locations = new HashMap<>();
	
	public MinHeap(int capacity) {
		this.capacity = capacity;
		heap = new Node[capacity];
		locations = new int[capacity];
		//heap[0] = new Node(-1);
		//heap[0].distance = Integer.MIN_VALUE;
		currentSize = 0;
	}
	private int getParent(int index) {
		return (index-1)/2;
	}
	private int getLeftChild(int index) {
		return index*2+1;
	}
	private int getRightChild(int index) {
		return index*2+2;
	}
	private void swap(int a, int b) {
		Node temp = heap[a];
		heap[a] = heap[b];
		heap[b] = temp;
		//maybe bug
		
		
	}
	Node extractMin() {
		Node min = heap[0];
		Node last = heap[currentSize-1];
		swap (0,currentSize-1);
		locations[last.value] = 0;
		locations[min.value] = -1;
		currentSize--;
		heapifyDown(0);
		System.out.println("heap size: "+currentSize);
		return min;
	}
	void heapifyDown(int index) {
		int currentIndex = index;
		int leftIndex = getLeftChild(currentIndex);
		int rightIndex = getRightChild(currentIndex);
		if (leftIndex < currentSize && heap[currentIndex].distance > heap[leftIndex].distance) {
			currentIndex = leftIndex;
		}
		if (rightIndex < currentSize && heap[currentIndex].distance > heap[rightIndex].distance) {
			currentIndex = rightIndex;
		}
		if (currentIndex != index) {
			Node newTop = heap[currentIndex];
			Node oldTop = heap[index];
			
			locations[newTop.value] = index;
			locations[oldTop.value] = currentIndex;
			swap(index,currentIndex);
			heapifyDown(currentIndex);
		}
	}
	void heapifyUp(int index) {
		int parentIndex = getParent(index);
		int currentIndex = index;
		Node currentNode = heap[currentIndex];
        Node parentNode = heap[parentIndex];
		while (currentIndex > 0 && heap[parentIndex].distance > heap[currentIndex].distance) {
			System.out.println("Swapped: "+heap[getParent(currentIndex)].toString()+" That has a distance of: "+heap[getParent(currentIndex)].distance+ " With: "+heap[currentIndex]+" Which has a distance of: "+heap[currentIndex].distance);
			swap(parentIndex,currentIndex);
			locations[currentNode.value] = parentIndex;
			locations[parentNode.value] = currentIndex;
			currentIndex = parentIndex;
			parentIndex = getParent(parentIndex);
			
			System.out.println("min: "+heap[0].toString());
		}
	}
	public void decreaseKey(Node node, int distance) {
		int location = locations[node.value];
		heap[location].distance = distance;
		heapifyUp(location);
	}
	public void insert(Node node) {
		//currentSize++;
		int index = currentSize;
		heap[currentSize] = node;
		locations[node.value] = currentSize;
		currentSize++;
		heapifyUp(index);
	}
	public boolean contains(int node) {
		return locations[node] != 0 && locations[node] != -1;
	}
	public boolean isEmpty() {
		return currentSize==0;
	}
	public Node getNode(Node node) {
		return heap[locations[node.value]];
	}
	public Node getNode(int nodeValue) {
		return heap[locations[nodeValue]];
	}
	public void updateNode(Node node) {
		heap[locations[node.value]] = node;
	}
	public void print() {
		for (int i = 0; i < currentSize; i++) {
			System.out.println(heap[i].toString());
		}
	}
	public Node peek() {
		return heap[0];
	}
	public int size() {
		return currentSize;
	}
}
