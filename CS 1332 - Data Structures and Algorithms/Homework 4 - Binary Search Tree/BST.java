//HW 4 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

/**
 * This class represents a binary search tree, which can be manipulated 
 * to store any kinds of object.
 * @author Jessie McGarry
 * @version 1.0
 * @param <T> the type of objects stored in the tree
 */
public class BST <T extends Comparable<T>> implements IBST<T>{
	/**
	 * A reference to the root (top) node of the tree.
	 */
	private INode<T> root;
	
	/**
	 * Empty constructor creates an empty tree.
	 */
	public BST() {
		root = new BSTNode<T>();
	}
	/**
	 * Creates a tree with a single node holding the specified value.
	 * @param rootValue - the value to be stored in the tree.
	 */
	public BST(T rootValue) {
		root = new BSTNode<T>(rootValue);
	}
	/**
	 * Creates a tree holding all the values specified.
	 * @param valueArr - the values to be inserted into the tree.
	 */
	public BST(T ... valueArr) {
		root = new BSTNode<T> (valueArr[0]);
		for (int i = 1; i < valueArr.length; i++)
			this.insert(valueArr[i]);
	}
	/**
	 * @return the root of the tree.
	 */
	public INode<T> getRoot() {
		return root;
	}
	/**
	 * Returns true if the tree is empty, and false otherwise.
	 */
	public boolean isEmpty() {
		boolean isEmpty = false;
		if(root.getData() == null)
			isEmpty = true;
		return isEmpty;
	}
	/**
	 * Inserts a node holding the specified value into the tree by
	 * calling the recursive helper method below.
	 * @param data - the value to be stored in the tree
	 */
	public void insert(T data) {
		INode<T> newNode = new BSTNode<T>(data);
		if(root.getData() == null)
			root = newNode;
		else
			insert(root, newNode);
	}
	/**
	 * Recusive helper method for the insert method above.
	 * @param current - the current node being processed
	 * @param newNode - the node to be inserted.
	 */
	private void insert(INode<T> current, INode<T> newNode) {
		if (current.getData().compareTo(newNode.getData()) > 0) {
			if (current.getLeftChild() == null)
				current.setLeftChild(newNode);
			else
				insert(current.getLeftChild(), newNode);
		}
		
		else
			if (current.getData().compareTo(newNode.getData()) < 0) {
				if (current.getRightChild() == null)
					current.setRightChild(newNode); 
				else
				   insert(current.getRightChild(), newNode); 
			}
			else
				current.incrementFrequency();
	}
	
	/**
	 * Removes a node containing the target value from the tree 
	 * by calling the recursive method below. If the node has a 
	 * frequency greater than one, the frequency is simply decremented
	 * by one.
	 * @param the target value to be removed from the tree.
	 */
	public boolean remove(T target) {
		boolean success = true;
		if (!this.contains(target))
			return !success;
		else {
			remove(root, new BSTNode<T>(target));
			return success;
		}
	}
	/**
	 * Recursive helper method for the remove method above.
	 * @param current - current node being processed.
	 * @param target - the target node to be removed.
	 * @return - true if the removal was successful, false otherwise.
	 */
	private INode<T> remove(INode<T> current, INode<T> target) { 
		INode<T> temp; 
		INode<T> retval = null; 
		if (current != null) { 
			if (current.getData().compareTo(target.getData()) == 0)  {
				if(current.getFrequency() > 1) // duplicate node
					current.decrementFrequency();
				else {
					
				if (current.getLeftChild() == null && 
						current.getRightChild() == null)  // no children 
					retval = null; 
				if (current.getLeftChild() != null && 
						current.getRightChild() == null) // left child only 
					retval = current.getLeftChild(); 
				if (current.getLeftChild() == null && 
						current.getRightChild() == null) // Right child only 
					retval = current.getRightChild(); 
				if (current.getLeftChild() != null && 
						current.getRightChild() != null) {// Node to delete has
					retval = current; 						// two children 
					if (current.getLeftChild().getRightChild() == null) { 
						current.setData(current.getLeftChild().getData()); 
						current.setLeftChild(current.getLeftChild().
															getLeftChild()); 
					}
					
					else { 
						temp = current.getLeftChild(); 
						while (temp.getRightChild().getRightChild() != null)
							temp = temp.getRightChild(); 
						current.setData(temp.getRightChild().getData()); 
						temp.setRightChild(temp.getRightChild().getLeftChild());
					} //else
				} //if
				} //else
			} //if
			else if (current.getData().compareTo(target.getData()) > 0) {
				current.setLeftChild(remove(current.getLeftChild(), target)); 
				retval = current; 
				}
			else { 
				current.setRightChild(remove(current.getRightChild(),
							 										target)); 
				retval = current; 
			 } //else
		} //if
		return retval;   
	} 
			


	/**
	 * Finds the node holding the minimum value in the tree
	 * (on a compareTo scale.)
	 * @return the node containing the minimum value.
	 */
	public INode<T> findMin(INode<T> c) {
		INode<T> current = root;
		boolean found = false;
		if (!this.contains(c.getData())) {
			System.out.println(c.getData() + " is not in the tree.");
			return null;
		}
		
		else {
			while(!found) {
				if (current.getData().compareTo(c.getData()) > 1) 
					current = current.getRightChild(); 
				else {
					if (current.getData().compareTo(c.getData()) > 1)
						current = current.getLeftChild();
					else 
						found = true; 
				}
				
			}	
		INode<T> min = current;
		while (min.getLeftChild() != null)
			min = min.getLeftChild();
		return min;
		}
				
		
	}
	
	/**
	 * @return true if there is a node in the tree containing the
	 * specified value, and false otherwise.
	 */
	public boolean contains(T value) {
		INode<T> current = root;
		boolean found = false, ended = false;
		while(!found && !ended) {
			if(current.getData().compareTo(value) > 0) {
				if(current.getLeftChild() == null)
					ended = true;
				else
					current = current.getLeftChild();
			}
			
			else if (current.getData().compareTo(value) < 0) {
				if(current.getRightChild() == null)
					ended = true;
				else
					current = current.getRightChild();
			}
			
			else
				found = true;
		}
		
		return found;
	}
	/**
	 * Constructs a string the data stored in the tree in a 
	 * 'pre order' style. (Parent-Left-Right) Uses the recursive helper
	 * method below
	 * @return String containing the data in the tree in a pre order 
	 * traversal.
	 */
	public String preOrderTraversal() {
		return preorder(root,"");
	}
	
	/**
	 * Recursive helper method for pre order traversal. Concatenates to
	 * the representative string with each call.
	 * @param current - the current node being processed.
	 * @param nodes - the string of data in the nodes.
	 * @return - the string of data in the nodes.
	 */
	public String preorder(INode<T> current, String nodes) {
		if (current != null) {
			nodes += current.getData() + "-" + current.getFrequency() + " " +
					 preorder(current.getLeftChild(), nodes) +
					 preorder(current.getRightChild(), nodes);
		}
		
		return nodes;
	}
	/**
	 * Constructs a string the data stored in the tree in a 
	 * 'post order' style. (Left-Right-Parent) Uses the recursive helper
	 * method below
	 * @return String containing the data in the tree in a post order 
	 * traversal.
	 */
	public String postOrderTraversal() {
		return postorder(root,"");
	}
	/**
	 * Recursive helper method for post order traversal. Concatenates to
	 * the representative string with each call.
	 * @param current - the current node being processed.
	 * @param nodes - the string of data in the nodes.
	 * @return - the string of data in the nodes.
	 */
	public String postorder(INode<T> current, String nodes) {
		if (current != null) {
			nodes += postorder(current.getLeftChild(), nodes) +
					 postorder(current.getRightChild(), nodes) +
					 current.getData() + "-" + current.getFrequency() + " ";
		}
	
	return nodes;
	}
	/**
	 * Constructs a string the data stored in the tree in a 
	 * 'in order' style. (Left-Parent-Right) Uses the recursive helper
	 * method below
	 * @return String containing the data in the tree in a post order 
	 * traversal.
	 */
	public String inOrderTraversal() { 
		return inorder(root,"");
	} 
	/**
	 * Recursive helper method for an in order traversal. Concatenates to
	 * the representative string with each call.
	 * @param current - the current node being processed.
	 * @param nodes - the string of data in the nodes.
	 * @return - the string of data in the nodes.
	 */
	private String inorder(INode<T> current, String nodes) { 
	
		if (current != null) { 
			nodes += inorder(current.getLeftChild(), nodes) +
					 current.getData() + "-" + current.getFrequency() + " " +
					 inorder(current.getRightChild(), nodes); 
		} 
		return nodes;
		
		
	
	} 
	/**
	 * Test main method for the binary tree class.
	 * @param args - command line args (no purpose)
	 */
	public static void main (String[] args) {
		BST<Integer> bst = new BST<Integer>(3,2,4,1,5);
		BSTNode<Integer> a = new BSTNode<Integer>(3);
		System.out.println(bst.findMin(a).getData());
		System.out.println(bst.remove(5));
		System.out.println(bst.contains(6));
		System.out.println(bst.inOrderTraversal());
		System.out.println(bst.postOrderTraversal());
		System.out.println(bst.preOrderTraversal());
		
		
		
	}
	
}
