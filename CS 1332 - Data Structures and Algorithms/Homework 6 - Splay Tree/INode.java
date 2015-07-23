/**
* INode, a binary search tree node interface, that will be implemented by SplayNode (format will be 
* 'implements INode<K>').  Note that in the SplayNode class, you will want the reference variable (the 
* stuff before the equals sign when you create a new object) to be the name of the implemented interface 
* (ie, INode<K> in this case).
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
	 * Returns the count at this node.
	 * The count variable begins at 1 for a new node with data (an empty node has a 
	 * count of 0).  As duplicate values are added to the tree, the count of the 
	 * corresponding value in the tree is incremented.
	 */
	public int getCount();
	
	/**
	 * Sets the count held by this node.
	 * The count variable begins at 1 for a new node with data (an empty node has a 
	 * count of 0).  As duplicate values are added to the tree, the count of the 
	 * corresponding value in the tree is incremented.
	 * @param newCount the new count of this node
	 */
	public void setCount(int newCount);
	
	/**
	 * Is this an "empty" node?  A node is considered "empty" if it has no data.  Note 
	 * that if a node doesn't have data, then it shouldn't have any children.
	 * @return true if yes, no if no
	 */
	public boolean isEmptyNode();
	
	/**
	 * Returns the left child of this node or null if there is not one.
	 * @return an INode or null
	 */
	public INode<K> getLeftChild();
	
	/**
	 * Sets the left child.  Can be null for no left child.
	 * @param newLeftChild a INode or null
	 */
	public void setLeftChild(INode<K> newLeftChild);
	
	/**
	 * Returns the right child of this node or null if there is not one.
	 * @return a INode or null
	 */
	public INode<K> getRightChild();
	
	/**
	 * Sets the right child.  Can be null for no right child.
	 * @param newRightChild an INode or null
	 */
	public void setRightChild(INode<K> newRightChild);
}