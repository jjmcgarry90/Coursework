//Collaboration Statement: This is solely my work.

/**
 * This class represents a Splay Tree, which is a self-balancing binary
 * search tree that also keeps the most accessed data at the top of the tree
 * by 'splaying' those nodes.
 * 
 * @author Jessie McGarry
 *
 * @param <K> The type of the nodes held in the tree.
 */
public class SplayTree<K extends Comparable<K>> implements ISplayTree<K> {
	/**
	 * The top node of the tree.
	 */
	private INode<K> root;
	
	/**
	 * Empty Constructor creates empty tree;
	 */
	public SplayTree() {
		root = null;
	}
	
	/**
	 * Creates a tree with the root containing the specified value.
	 * @param init - the root's initial value.
	 */
	public SplayTree(K init) {
		root.setData(init);
	}
	
	/**
	 * @return the root node of the tree.
	 */
	public INode<K> getRoot() {
		return root;
	}
	
	public boolean isEmpty() {
		if (root == null || root.getData() == null)
			return true;
		else 
			return false;
	}
	
	/**
	 * Inserts a node containing the specified value, then splays that
	 * node to the top of the tree.
	 * @param - c - the value to be inserted into the tree.
	 */
	public void insert(K c) {
		SplayNode<K> newNode = new SplayNode<K>(c);
		if(this.isEmpty())
			root = newNode;
		else {
			insert(root, newNode);
			splay(newNode,root);
		}
	}
	/**
	 * Recusive helper method for the insert method above.
	 * @param current - the current node being processed
	 * @param newNode - the node to be inserted.
	 */
	private void insert(INode<K> current, INode<K> newNode) {
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
				current.setCount(current.getCount() + 1);
	}
	
	/**
	 * Removes the node containing the specified value from the tree,
	 * then splays that nodes former parent node, and returns true. 
	 * If the node is not found, the last touched node is splayed, 
	 * and false is returned.
	 */
	public boolean remove(K target) {
		boolean success = true;
		if (find(target)) {
			remove(root, new SplayNode<K>(target));
			return success;
		}
		else
			return !success;
	}
	/**
	 * Recursive helper method for the remove method above.
	 * @param current - current node being processed.
	 * @param target - the target node to be removed.
	 * @return - true if the removal was successful, false otherwise.
	 */
	private INode<K> remove(INode<K> current, INode<K> target) { 
		INode<K> temp; 
		INode<K> retval = null; 
		if (current != null) { 
			if (current.getData().compareTo(target.getData()) == 0)  {
				if(current.getCount() > 1) {// duplicate node
					current.setCount(current.getCount()-1);
					splay(current,root);
				}
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
						splay(current,root);
					}
					
					else { 
						temp = current.getLeftChild(); 
						while (temp.getRightChild().getRightChild() != null)
							temp = temp.getRightChild(); 
						current.setData(temp.getRightChild().getData()); 
						temp.setRightChild(temp.getRightChild().getLeftChild());
						splay(current,root);
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
	 * Looks up a value in the tree and, if found, splays that node. If
	 * the value is not found, the last node accessed is splayed, and false
	 * is returned.
	 */
	public boolean find(K value) {
		INode<K> current = root;
		boolean found = false, ended = false;
		while(!found && !ended) {
			if(current.getData().compareTo(value) > 0) {
				if(current.getLeftChild() == null) {
					ended = true;
					splay(current,root);
				}
				
				else
					current = (SplayNode<K>) current.getLeftChild();
			}
			
			else if (current.getData().compareTo(value) < 0) {
				if(current.getRightChild() == null) {
					ended = true;
					splay(current,root);
				}
				
				else
					current = (SplayNode<K>) current.getRightChild();
			}
			
			else {
				found = true;
				splay(current,root);
			}
		}
		
		return found;
	}
	
	/**
	 * Returns a string representative of all the nodes held in the tree
	 * in Left-Right-Parent order.
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
	public String postorder(INode<K> current, String nodes) {		
		if (current != null) {
			String freq = "";
			for(int i = 0; i < current.getCount(); i++)
				freq+= current.getData() + " ";
			nodes += postorder(current.getLeftChild(), nodes) +
					 postorder(current.getRightChild(), nodes) +
					 freq;
		}
	
	return nodes;
	}
	
	/**
	 * Transports a node to the top of the tree (root).
	 * @param target - the node to be splayed
	 * @param root - the top node in the tree.
	 */
	public void splay(INode<K> target, INode<K> root) {
		if(root == target)
			return;
		else if(root.getLeftChild() == target ||
				root.getRightChild() == target) {
			if (root.getLeftChild() == target) {
				rightRotation(root);
			}
			else
				leftRotation(root);
		}
		
	}
	
	/**
	 * Helper method for the above splay method. Performs a simple rotation
	 * of nodes within the tree.
	 * @param root - either the root of the tree or the root of the subtree
	 * being modified;
	 */
	private void rightRotation(INode<K> root) {
		INode<K> pivot = root.getLeftChild();
		root.setLeftChild(pivot.getRightChild());
		pivot.setRightChild(root);
		root = pivot;
	}
	
	/**
	 * Helper method for the above splay method. Performs a simple rotation
	 * of nodes within the tree.
	 * @param root - either the root of the tree or the root of the subtree
	 * being modified;
	 */
	private void leftRotation(INode<K> root) {
		INode<K> pivot = root.getRightChild();
		root.setRightChild(pivot.getLeftChild());
		pivot.setLeftChild(root);
		root = pivot;
	}
															
		
	/**
	 * Test main method
	 * @param args - command line arguments.
	 */
	public static void main(String[] args) {
		SplayTree<Integer> tree = new SplayTree<Integer>();
		System.out.println(tree.isEmpty());
		tree.insert(10);
		tree.insert(10);
		System.out.println(tree.postOrderTraversal());
		tree.insert(9);
		System.out.println(tree.find(9));
		System.out.println(tree.getRoot().getData());
		System.out.println(tree.getRoot().getLeftChild());
		System.out.println(tree.getRoot().getRightChild());

	}
}
