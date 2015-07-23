//HW 4 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

/**
 * This class represents a single node to be used in a binary search
 * tree. The node type is generic, so the data held within can be any
 * type of object.
 * @author Jessie McGarry
 * @version 1.0
 * @param <T> the type of data held in the node
 */
public class BSTNode <T extends Comparable<T>> implements INode<T>  {
	/**
	 * The value stored within the node.
	 */
	private T data;
	/**
	 * The number of times this node is stored in the tree.
	 */
	private int frequency;
	/**
	 * The left child of this node.
	 */
	private INode<T> left;
	/**
	 * The right child of this node.
	 */
	private INode<T> right;
	
	/**
	 * Empty constructor creates a data-less node
	 */
	public BSTNode() {
		frequency = 1;
	}
	/**
	 * Creates and node and stores the given value within it.
	 * @param data - the value to be stored in the node.
	 */
	public BSTNode(T data) {
		this.data = data;
		frequency = 1;
	}
	
	/**
	 * @return - the data held in the Node.
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Sets the data held in the node to a new, given value.
	 * @param newValue - the value to be stored.
	 */
	public void setData(T newValue) {
		data = newValue;
	}
	
	/**
	 * @return the number of instances of this node in the tree.
	 */
	public int getFrequency() {
		return frequency;
	}
	/**
	 * Sets the frequency of the node to the specified frequency.
	 * @param - the frequency the node is to have
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	/**
	 * Increments the frequency by 1.
	 */
	public void incrementFrequency() {
		frequency++;
	}
	/**
	 * Decrements the frequency by 1.
	 */
	public void decrementFrequency() {
		frequency--;
	}
	/**
	 * Returns true if the node holds no data, false otherwise.
	 */
	public boolean isEmptyNode() {
		boolean isEmpty = false;
		if(data == null)
			isEmpty = true;
		return isEmpty;
	}
	/**
	 * @return the left child of the node.
	 */
	public INode<T> getLeftChild() {
		return left;
	}
	/**
	 * Setter for the left child of a node.
	 * @param the new left child of the node
	 */
	public void setLeftChild(INode<T> newLeftChild) {
		left = newLeftChild;	
	}
	/**
	 * @return the right child of the node
	 */
	public INode<T> getRightChild() {
		return right;
	}
	/**
	 * Setter for the right child of the node.
	 * @param the new right child of the node.
	 */
	public void setRightChild(INode<T> newRightChild) {
		right = newRightChild;
	}
}
