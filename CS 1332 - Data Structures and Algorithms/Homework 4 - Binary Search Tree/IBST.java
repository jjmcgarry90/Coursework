/**
* IBST, a binary search tree interface, that will be implemented by 
* the BST class that you will write for this homework (format will be 
* 'implements IBST<K>').
* 
*/
public interface IBST<K extends Comparable<K>> {

	/**
	 * Returns the root node of the tree, or null if none.
	 * @return an INode or null
	 */
	public INode<K> getRoot();
	
	/**
	 * Is this an empty tree?
	 * @return true if so, false if not
	 */
	public boolean isEmpty();
	
	/**
	 * Inserts a new value (hence creating a new node) into the tree.  In
	 * the case where the tree is empty, a node containing the value should
	 * be inserted as the root node.  Duplicate values should not be inserted but
	 * instead increment the frequency of the node that contains the duplicate data.
	 * @param c value to insert
	 */
	public void insert(K c);
	
	/**
	 * Either removes the value (and the node) from the tree or decrements the frequency
	 * of the node containing the value depending on the current frequency of the node. If
	 * the frequency of the node is 1 then the node should simply be removed from the tree.
	 * If the frequency of the node is > 1, then the frequency of the node should just be
	 * decremented by 1.
	 * @param c value to remove
	 * @return true if the removal was successful, 
	 * false if not (ie, if the item to remove was not present in the tree)
	 */
	public boolean remove(K c);
	
	/**
	 * Finds the min data value in the subtree of the passed in node (the
	 * data value that has been deemed the "min" by the compareTo method). 
	 * The node passed in should be considered a part of the subtree.
	 * @param c the root node of the subtree to search
	 * @return the node that contains the min data value in the subtree
	 */
	public INode<K> findMin(INode<K> c);

	/**
	 * Look up an element in the tree.  If an object which is equal
	 * (i.e., compareTo returns 0) exists in the tree, we return true.
	 * Otherwise, we return false.
	 * @param c value to find
	 * @return true if the element was found, false if not
	 */
	public boolean contains(K c);
	
	/**
	 * Performs a pre-order traversal of the tree, putting the data of each node and its
	 * frequency in a string to return (separated by spaces) as it is passed. The data
	 * and frequency of each node should be separated by a '-'. An example of a valid
	 * return string would be:
	 * "hop-4 bat-1 zebra-3"
	 * if hop has a frequency of 4, bat has 1, and zebra has 3.
	 * 
	 * If the tree is empty then return "".
	 * @return the string of elements resulting from the pre-order traversal, or an 
	 * empty string if the tree is empty
	 */
	public String preOrderTraversal();

	/**
	 * Performs a post-order traversal of the tree, putting the data of each node and its
	 * frequency in a string to return (separated by spaces) as it is passed. The data
	 * and frequency of each node should be separated by a '-'. An example of a valid
	 * return string would be:
	 * "bat-1 zebra-3 hop-4"
	 * if hop has a frequency of 4, bat has 1, and zebra has 3.
	 * 
	 * If the tree is empty then return "".
	 * @return the string of elements resulting from the post-order traversal, or an 
	 * empty string if the tree is empty
	 */
	public String postOrderTraversal();
	
	/**
	 * Performs a in-order traversal of the tree, putting the data of each node and its
	 * frequency in a string to return (separated by spaces) as it is passed. The data
	 * and frequency of each node should be separated by a '-'. An example of a valid
	 * return string would be:
	 * "bat-1 hop-4 zebra-3"
	 * if hop has a frequency of 4, bat has 1, and zebra has 3.
	 * 
	 * If the tree is empty then return "".
	 * @return the string of elements resulting from the in-order traversal, or an 
	 * empty string if the tree is empty
	 */
	public String inOrderTraversal();
}