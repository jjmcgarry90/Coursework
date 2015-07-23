/**
 * This class represents a doubly-linked Node capable of
 * holding a piece of data of the type specified by the
 * generic. Do not change or alter this class in any way.
 * 
 * <T> the type of the data this node should hold.
 * 
 * @author Craig Campbell
 */
public class Node<T> {
	/** The previous node in the list. */
	private Node<T> prev;
	
	/** The next node in the list. */
	private Node<T> next;
	
	/** The data this node holds. */
	private T data;
	
	/**
	 * Empty constructor which simply sets the prev,
	 * next, and data instance variables to null.
	 */
	public Node() {
		prev = null;
		next = null;
		data = null;
	}
	
	/**
	 * Constructor that takes in a piece of data for the
	 * constructed node to hold.
	 * @param data the data the new node should hold.
	 */
	public Node(T data) {
		prev = null;
		next = null;
		this.data = data;
	}
	
	/**
	 * Returns the previous node of this node in the
	 * linked list.
	 * @return the previous node
	 */
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
	 * Sets the previous node of this node.
	 * @param prev the node to make the previous of this node
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * Returns the next node of this node in the linked list.
	 * @return the next node
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * Sets the next node of this node.
	 * @param next the node to set to the next node
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	/**
	 * Returns the data that this node is holding.
	 * @return the data held by the node
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Sets the data held by this node to the passed in data.
	 * @param data the new data that this node should contain.
	 */
	public void setData(T data) {
		this.data = data;
	}
}
