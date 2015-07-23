/**
* INode, a binary search tree node interface, that will be implemented by BSTNode (format will be 
* 'implements INode<K>').  Note that in the BSTNode class, you will want the reference variable (the 
* stuff before the equals signs when you create a new object) to be the name of an implemented interface 
* (ie, INode<K> in this case).
* 
*/
public interface INode<K extends Comparable<K>> {

	/**
	 * Returns the data held by this node.  Returns NULL for empty nodes.
	 * @return data held here or null
	 */
	public K getData();
	
	/**
	 * Sets the data held by this node.
	 * @param newValue the new value of this node
	 */
	public void setData(K newValue);
	
	/**
	 * Returns the number of times the data held by this node
	 * has been 'inserted' into the tree. If the data has
	 * been inserted once then it should return 1. If the data
	 * has been inserted twice then it should be 2, etc. 
	 * Please see the javadoc of the insert method in IBST
	 * for more information.
	 * @return the number of times the data has been 'inserted'
	 */
	public int getFrequency();
	
	/**
	 * Sets the frequency of the node to the value
	 * passed in.
	 * @param the new frequency of the node
	 */
	public void setFrequency(int frequency);
	
	/**
	 * Increments the frequency of the node by 1.
	 */
	public void incrementFrequency();
	
	/**
	 * Decrements the frequency of the node by 1.
	 */
	public void decrementFrequency();
	
	/**
	 * Is this an "empty" node?  A node is considered "empty" if it has no data.  Note 
	 * that if a node doesn't have data, then it shouldn't have any children.
	 * @return true if yes, false if no
	 */
	public boolean isEmptyNode();
	
	/**
	 * Returns the left child of this node or null if there is not one.
	 * @return an INode or null
	 */
	public INode<K> getLeftChild();
	
	/**
	 * Sets the left child.  Can be null for no left child.
	 * @param newLeftChild an INode or null
	 */
	public void setLeftChild(INode<K> newLeftChild);
	
	/**
	 * Returns the right child of this node or null if there is not one.
	 * @return an INode or null
	 */
	public INode<K> getRightChild();
	
	/**
	 * Sets the right child.  Can be null for no right child.
	 * @param newRightChild a INode or null
	 */
	public void setRightChild(INode<K> newRightChild);
}