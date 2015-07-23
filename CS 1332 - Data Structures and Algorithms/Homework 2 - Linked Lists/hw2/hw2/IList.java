/**
 * This is the interface that your CircularDoublyLinkedList must
 * implement. It defines all of the methods you are required
 * to implement in your CircularDoublyLinkedList implementation.
 * 
 * Please thoroughly read the documentation for each method
 * before attempting to implement them.
 * 
 * DO NOT change/edit/alter any of the method signatures included in
 * this file. You MAY add additional methods if you feel the need.
 * 
 * @param <T> The generic type held by each node of the linked list
 * 
 * @author Craig Campbell
 */
public interface IList<T> {
	
	/**
	 * Returns the data held by the node at the specified index in the linked list.
	 * The list should not be changed as a result of this method.
	 * 
	 * Throw an IllegalArgumentException if the index passed in is less than zero or
	 * greater than or equal to the number of nodes in the list.
	 * @param index the index of the node to return the data of
	 * @return the data held by the node at the specified index
	 */
	public abstract T getAt(int index);
	
	/**
	 * Adds a node containing the item passed in to the front of the list.
	 */
	public abstract void add(T item);
	
	/**
	 * Adds a node containing the item at the given index (and shifts other 
	 * nodes as necessary).  If the index is less than or equal to zero, add 
	 * a node with the item to the beginning of the list.  If the index is 
	 * greater than or equal to the number of nodes in the list, add a node 
	 * with the item to the end of the list.  Otherwise, add a node with the 
	 * item to the appropriate place in the list based on index.
	 * @param item the item to add a node with
	 * @param index the index to add at
	 */
	public abstract void addAt(T item, int index);
	
	/**
	 * Adds a node containing the newItem passed in directly AFTER the node
	 * containing the data itemToAddAfter. If multiple nodes in your list have
	 * data itemToAddAfter, only add a node containing newItem after the first
	 * instance. If itemToAddAfter does not exist in the list, then do not add
	 * a node containing newItem to the list.
	 * @param newItem the item to add to the list
	 * @param itemToAddAfter the data to add after
	 */
	public abstract void addAfter(T newItem, T itemToAddAfter);
	
	/**
	 * Adds a node containing the newItem passed in directly BEFORE the node
	 * containing the data itemToAddAfter. If multiple nodes in your list have
	 * data itemToAddAfter, only add a node containing newItem before the first
	 * instance. If itemToAddAfter does not exist in the list, then do not add
	 * a node containing newItem to the list.
	 * @param newItem the item to add to the list
	 * @param itemToAddAfter the data to add before
	 */
	public abstract void addBefore(T newItem, T itemToAddBefore);
	
	/**
	 * Removes all nodes from the linked list where the node's data is equal to
	 * the item. If the list does not have nodes with that data, or the list is empty,
	 * then simply do nothing.
	 * @param item the data item (NOT THE NODE) to completely remove from the list
	 */
	public abstract void removeAllInstances(T item);
	
	/**
	 * Removes the node at the specified index from the list and returns the data
	 * held by the removed node. The remaining nodes should be shifted as needed.
	 * 
	 * Throw an IllegalArgumentException if the index passed in is less than zero or
	 * greater than or equal to the number of nodes in the list.
	 * @param index the index of the node to remove from the list
	 * @return the data held by the node that was removed
	 */
	public abstract T removeAt(int index);
	
	/**
	 * Removes the first node in the list (the one closest to the head) with data equal
	 * to the item passed in. If no nodes contain data equal to the item, or the list
	 * is empty, then do nothing.
	 * @param item the data item (NOT THE NODE) to remove the first instance of from
	 * the list
	 */
	public abstract void removeFirstInstance(T item);
	
	/**
	 * Returns the number of nodes currently held in the list.
	 */
	public abstract int size();
	
	/**
	 * Returns a boolean indicating whether or not the list contains
	 * any elements.
	 * @return false if the list has at least one element, false otherwise
	 */
	public abstract boolean isEmpty();
	
	/**
	 * Creates and returns a String representation of the list. For the circular
	 * implementation, simply start with the head node and end at the last node in
	 * the list. The data of each node should be printed with a space to act as a
	 * delimiter. If the list is empty then simply output an empty string, "".
	 * @return the string representation
	 */
	public abstract String toString();
}
