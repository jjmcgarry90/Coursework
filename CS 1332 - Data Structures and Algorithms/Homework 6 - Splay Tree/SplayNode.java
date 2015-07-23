//Collaboration Statement: This is solely my work.

/**
 * This class represents a node to be used in a Splay Tree. It is 
 * structurally the same as a Binary Search Tree node.
 * 
 * @author Jessie McGarry
 * 
 * @param K - the object type stored in the node (i.e. String, Integer)
 */
public class SplayNode <K extends Comparable<K>> implements INode <K> {
	
	/**
	 * The Comparable value stored within the node.
	 */
	private K data;
	
	/**
	 * The number of times the value in the node is stored in the tree.
	 * A node which has no data has null count.
	 */
	private int count;
	
	/**
	 * A reference to the left child of this node.
	 */
	private INode<K> left;
	
	/**
	 * A reference to the right child of this node.
	 */
	private INode<K> right;
	
	/**
	 * A reference to the parent of this node.
	 */
	private INode<K> parent;

	/**
	 * Empty constructor creates empty node.
	 */
	public SplayNode() {
		data = null;
		count = 0;
	}
	
	/**
	 * Creates a node containing the specified value.
	 * @param data - the initial value to be stored in the node.
	 */
	public SplayNode(K data) {
		this.data = data;
		count = 1;
	}
	
	/**
	 * @return the data stored in this Node.
	 */
	public K getData() {
		return data;
	}
	
	/**
	 * Sets the data in the node to the specified value.
	 * @param - the value to be stored in the node.
	 */
	public void setData(K newValue) {
		data = newValue;
	}
	
	/**
	 * @return the count of this node.
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Sets the count of the node to the specified value
	 * @param - the new count of the node
	 */
	public void setCount(int newCount) {
		count = newCount;
	}
	
	/**
	 * Returns true if the node contains no data, and false otherwise.
	 */
	public boolean isEmptyNode() {
		if (data == null)
			return true;
		else 
			return false;
	}
	
	/**
	 * @return the left child of this node (can be null)
	 */
	public INode<K> getLeftChild() {
		return left;
	}
	
	/**
	 * @return the right child of this node (can be null)
	 */
	public INode<K> getRightChild() {
		return right;
	}
	
	/**
	 * Sets the left child of this node to the specified value.
	 * @param the new value of the left child node.
	 */
	public void setLeftChild(INode<K> newLeftChild) {
		left = newLeftChild;
	}
	
	/**
	 * Sets the right child of this node to the specified value.
	 * @param the new value of the right child node.
	 */
	public void setRightChild(INode<K> newRightChild) {
		right = newRightChild;
	}
 }
