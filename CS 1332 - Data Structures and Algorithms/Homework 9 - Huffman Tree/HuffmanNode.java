//Collaboration Statement: This is solely my work.

import java.util.PriorityQueue;

/**
 * This class represents a node to be used in a Huffman Tree, which
 * provides a method to compress data in a file. The tree will contain
 * all the necessary information to decode the file.
 * 
 * @author Jessie McGarry
 *
 */
public class HuffmanNode implements HuffmanNodeType {
	/**
	 * The Byte value stored in the node, which is present
	 * only in external leaf nodes.
	 */
	private Byte data;
	
	/**
	 * The frequency of either the data stored in the node
	 * (if data is present) or the combined frequency of 
	 * all data in the nodes in the subtrees of this node 
	 */
	private int useCount;
	
	/**
	 * A reference to the left child of this node.
	 */
	private HuffmanNodeType leftChild;
	
	/**
	 * A reference to the right child of this node.
	 */
	private HuffmanNodeType rightChild;
	
	/**
	 * Keeps track of how many nodes have been created
	 * for compareTo purposes.
	 */
	private static int number;
	
	/**
	 * Each node created takes on an index using the
	 * static int variable above for compareTo purposes.
	 */
	private int index;
	
	/**
	 * Constructor used for nodes that will actually
	 * contain Byte data. (Typically external leaf nodes.)
	 * @param data - the data to be stored in the node.
	 * @param frequency - how often the data appears in
	 * 					  a file.
	 */
	public HuffmanNode(char data, int frequency) {
		this.data = (byte) data;
		useCount = frequency;
		number++;
		index = number;
	}
	
	/**
	 * Constructor used to create placeholder nodes,
	 * which hold no data but references to children
	 * that may.
	 * @param child1 - to be stored as left child of new node.
	 * @param child2 - to be stored as right child of new node.
	 */
	public HuffmanNode(HuffmanNodeType child1, HuffmanNodeType child2) {
		data = null;
		useCount = child1.getUseCount() + child2.getUseCount();
		number++;
		index = number;
		leftChild = child1;
		rightChild = child2;
		
	}
	
	/**
	 * @return - the data stored in the node, possibly null.
	 */
	public Byte getData() {
		return data;
	}
	
	/**
	 * @return - either the number of times the data stored
	 * in the node occurs in a file, or the combined totals
	 * from the subtrees of this node. 
	 */
	public int getUseCount() {
		return useCount;
	}
	
	/**
	 * @return - the left child of this node, 
	 * 			leaf nodes return null.
	 */
	public HuffmanNodeType getLeftChild() {
		return leftChild;
	}
	
	/**
	 * @return - the right child of this node,
	 * 			 leaf nodes return null.
	 */
	public HuffmanNodeType getRightChild() {
		return rightChild;
	}
	
	/**
	 * Compares to nodes based on their frequency first. If
	 * the use counts are equal, then compares based on data.
	 * (Null data is first). If both data are null, then compares
	 * based on the order in which the nodes were created.
	 */
	public int compareTo(HuffmanNodeType that) {
		int retVal = 0;
		if(this.getUseCount() != that.getUseCount()) 
			if(this.getUseCount() < that.getUseCount()) 
				retVal = -1; 
			else 
				retVal = 1;
		
		else if(!(this.getData() == null || that.getData() == null))
			if(this.getData() < that.getData())
				retVal = -1;
			else
				retVal = 1;
		
		else if (this.getData() == null ^ that.getData() == null)
			if(this.getData() == null)
				retVal = -1;
			else
				retVal = 1;
		
		else
			if (this.getIndex() < that.getIndex())
				retVal = -1;
			else
				retVal = 1;

		return retVal;
	}
	
	/**
	 * Returns the order in which this node was created.
	 * If index = 1; then this was the first node created.
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Sets the index of the node, used perhaps for reordering.
	 */
	public void setIndex(int newIndex) {
		index = newIndex;
	}
	
	/**
	 * I used this method to trace the problem with my code back
	 * to the compareTo() method. I use this method below in the
	 * commented out queue.toString() call. 
	 */
	public String toString() {
		return "Frequency: " + this.getUseCount();
	}
	
	/**
	 * This method constructs a tree using an array of frequencies
	 * which become the useCounts in the nodes. The input array should
	 * have a size of 256, for every possible byte in the askii table.
	 * Each slot in the array should represent the number of times the
	 * byte at that index appears in the sample.
	 * @param frequencies - the frequency array for the characters
	 * 						in the sample.
	 * @return - the root node of a newly constructed Huffman Tree
	 * 			representing the data.
	 */
	public static HuffmanNodeType buildHuffmanTree(int[] frequencies) {
		PriorityQueue<HuffmanNodeType> queue = 
						new PriorityQueue<HuffmanNodeType>();
		
		for(int i = 0; i < frequencies.length; i++) 
			if(frequencies[i] > 0) 
				queue.add(new HuffmanNode((char) i, frequencies[i]));
				
		while(queue.size() > 1) {
			HuffmanNodeType node1 = queue.remove();
			HuffmanNodeType node2 = queue.remove();
			HuffmanNodeType newNode = new HuffmanNode(node1, node2);
			queue.add(newNode);
			//Notice that the frequencies are out of order if you make the
			//call below. If you can see the problem in my compareTo() 
			//method, please tell me. If I can fix it, I believe decoder
			//class will work fine. 
			//System.out.println(queue.toString());
		}
		return queue.remove();
	}
}
