/**
* ISplayTree, a binary search tree interface that will be implemented by 
* the SplayTree class you write for this homework (format will be 'implements 
* ISplayTree<K>').
*/
public interface ISplayTree<K extends Comparable<K>> {

	/**
	 * Returns the root node of the tree, or null if none.  This method should not
	 * remove the root.
	 * @return an INode or null
	 */
	public INode<K> getRoot();
	
	/**
	 * Is this an empty tree?
	 * @return true if so, false if not
	 */
	public boolean isEmpty();
	
	/**
	 * Inserts a new value into the tree, and then splays the node containing this value to 
	 * the root.
	 * 
	 * If the value to be inserted is not already present in the tree, then a new node is 
	 * created with this value and inserted into the tree.  If the value to be inserted is already 
	 * present in the tree, then increment the count of the node in the tree containing this value. 
	 * In the case where the tree is empty, a node containing the value should be inserted as 
	 * the root node.
	 * 
	 * @param c value to insert
	 */
	public void insert(K c);
	
	/**
	 * Removes the value c from the tree, and splays as needed.
	 * If the count of the node was previously 1, then the entire node should be removed from the tree 
	 * (you may not just decrement the count of the node to 0) and the removed node's parent should be 
	 * splayed to the root.  If the count of the node was previously greater than 1, you should merely 
	 * decrement the count of the node and splay the node to the root.
	 * @param c value to remove
	 * @return true if the removal was successful (ie, the node was removed or the count was 
	 * decremented), false is not (ie, if the value to remove was not present in the tree)
	 */
	public boolean remove(K c);

	/**
	 * Look up a value in the tree.
	 * If a value which is equal (i.e., compareTo is 0) exists in the tree, we return true and splay the node 
	 * containing this value to the root.  Otherwise, we return false and splay the node at which the search
	 * terminated unsuccessfully.
	 * @param c value to find
	 * @return true if the value was found, false if not
	 */
	public boolean find(K c);

	/**
	 * Performs a post-order traversal of the tree, putting the data of each node in a 
	 * string to return (separated by spaces) as it is passed.  For nodes with count 
	 * greater than 1, the value should be printed count times (ie, a node with value 3 
	 * and count 5 would be printed as '3 3 3 3 3').
	 * @return the string of elements resulting from the post-order traversal, or an 
	 * empty string if the tree is empty
	 */
	public String postOrderTraversal();
}