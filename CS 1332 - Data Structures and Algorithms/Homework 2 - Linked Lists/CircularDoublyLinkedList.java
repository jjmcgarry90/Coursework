//HW 2 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

/**
* This class represents a generic doubly linked list in which the head 
* and tail nodes hold a reference to each other. The generic types are
* stored within nodes within the list. The class also provides various 
* services for the list.
* 
* @author Jessie McGarry
* @version 1.0
*/
public class CircularDoublyLinkedList <T> implements IList <T>{
	/**
	 * Instance data for head, tail, and size (number of nodes)
	 * of the list
	 */
	private Node<T> head;	
	private Node<T> tail;
	private int size;
	
	/**
	 * Constructor creates empty list.
	 */
	public CircularDoublyLinkedList() {
		head = null;
		size = 0;      
	}
	
	/**
	 * Finds the data stored in the node at the specified index. This
	 * method will throw an exception if the index is less than zero
	 * or greater than the last index in the list.
	 * 
	 * @param the index from which to retrieve data.
	 * @return the data stored in the node at the specified index.
	 */
	public T getAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		if (head == null)
			return null;
		
		Node<T> target;
		if(index == 0)
			target = head;
		else {
			target = head;
			for(int i = 0; i < index; i++) 
				target = target.getNext();		
		}  //else
		return target.getData();		
	}
	
	/**
	 * Creates a new node which stores the data given and adds the
	 * node to the front of list (as the head).
	 * 
	 * @param item - the data to be stored in the node
	 */
	public void add(T item) {
		Node<T> newNode = new Node<T>(item);
		if(head == null) {
			head = newNode;
			tail = newNode;
			head.setNext(tail);
			head.setPrev(tail);
			size++;
		}
		
		else {
			newNode.setNext(head);
			newNode.setPrev(tail);
			head.setPrev(newNode);
			tail.setNext(newNode);
			head = newNode;
			size++;	
		} //else	
	} //method
	
	/**
	 * Creates a new node from item and stores it at the specified 
	 * index. If the index is 0, the new node is the head. If the
	 * index is greater than the last index in the list, the new node
	 * will be the tail. Otherwise, the new node is inserted into the 
	 * middle of the list, in which case the preexisting node and its 
	 * followers are moved back an index.
	 * 
	 * @param item - the item to be added to the list
	 * @param index - the index to add the item at
	 */
	public void addAt(T item, int index) {
		Node<T> newNode = new Node<T>(item);
		if (index <= 0)  
			add(item);  //inserts at head
		
		else { 
			if (index >= size) {    //inserts at tail
				newNode.setNext(head);
				newNode.setPrev(tail);
				tail.setNext(newNode);
				head.setPrev(newNode);
				tail = newNode;
				size++;
			}
			else {					//inserts in middle
				Node<T> current = head;
				for (int i = 0; i < index; i++)
				current = current.getNext();
				newNode.setNext(current);
				newNode.setPrev(current.getPrev());
				newNode.getPrev().setNext(newNode);
				current.setPrev(newNode);
				size++;
			} //else
		} //else
	} //method
	
	/**
	 * Creates a node from the new item and adds it after the specified
	 * item in the list. If itemToAddAfter does not exist within the
	 * list, the newItem will not be added.
	 * 
	 * @param newItem the item to be added to the list
	 * @param itemToAddAfter the item after which to add the new item
	 */
	public void addAfter(T newItem, T itemToAddAfter) {
		boolean found = false;
		int count = 0;
		Node<T> current = tail;
		
		while(!found && count < size) {
			current = current.getNext();
			if (current.getData() == itemToAddAfter)
				found = true;
			count++;
		} 
		
		if (found) {
			Node<T> newNode = new Node<T>(newItem);
			newNode.setNext(current.getNext());
			newNode.setPrev(current);
			newNode.getNext().setPrev(newNode);
			if(current.getNext() == tail.getNext())  //new tail in this case
				tail = newNode;
			current.setNext(newNode);
			size++;	
		} //if	
	} //method
	
	/**
	 * Creates a node from the new item and adds it before the specified
	 * item in the list. If itemToAddBefore does not exist within the
	 * list, the newItem will not be added.
	 * 
	 * @param newItem the item to be added to the list
	 * @param itemToAddAfter the item before which to add the new item
	 */
	public void addBefore(T newItem, T itemToAddBefore) {
		boolean found = false;
		int count = 0;
		Node<T> current = tail;
		
		while(!found && count < size) {
			current = current.getNext();
			if (current.getData() == itemToAddBefore)
				found = true;
			count++;
		}
		
		if (found) {
			Node<T> newNode = new Node<T>(newItem);
			newNode.setNext(current);
			newNode.setPrev(current.getPrev());
			current.getPrev().setNext(newNode);
			current.setPrev(newNode);
			if(current.getNext() == head.getNext()) //new head this case
				head = newNode;
			size++;
		} //if
	} //method
	
	/**
	 * Removes all instances of data equal to the item specified in the
	 * list. The indices are adjusted as necessary. If the item does
	 * not exist in the list, nothing is changed.
	 * 
	 * @param - item - the item(s) to be removed from the list.
	 */
	public void removeAllInstances(T item) {
		Node<T> current = head;
		for(int i = 0; i <= size; i++) {
			if (current.getData() == item) {
				if(current == head) 
					head = current.getNext();
				if(current == tail)
					tail = current.getPrev();
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
				size--;
			} //if
			current = current.getNext();
		} //for	
		
		if(size == 0)  //empty list
			head = tail = new Node<T>();
	} //method
	
	/**
	 * Removes the data at the specified index and adjust the indices
	 * accordingly. If the specified index is less than zero or greater
	 * than the last index in the list, an IllegalArgumentException is 
	 * thrown.
	 * 
	 * @param the index of the data to be removed
	 * @return the data stored in the removed node
	 */
	public T removeAt(int index) {
		if (index < 0 || index >= size)
			throw new IllegalArgumentException();
		if (head == null)
			return null;
		
		Node<T> target;
		if(index == 0) {
			target = head;
			target.getPrev().setNext(target.getNext());
			target.getNext().setPrev(target.getPrev());
			head = target.getNext();
		}		
		else {
			target = head;
			for(int i = 0; i < index; i++) 
				target = target.getNext();
			target.getPrev().setNext(target.getNext());
			target.getNext().setPrev(target.getPrev());
			if (target.getNext() == head)
				tail = target.getPrev();
		}  //else
		
		size--;
		if(size == 0)    //empty list
			head = tail = new Node<T>();
		
		return target.getData();
	}
	
	/**
	 * Removes the first occurrence in the list of the specified data.
	 * If the data does not exist within the list, nothing is changed.
	 * 
	 * @param data - the data to be removed from the list.
	 */
	public void removeFirstInstance(T data) {
		boolean found = false;
		int count = 0;
		
		Node<T> current = tail;
		while(!found && count < size) {
			current = current.getNext();
			if (current.getData() == data)
				found = true;
			count++;
		} //while
		if (found) {
			if(current == head)
				head = current.getNext();
			if(current == tail)
				tail = current.getPrev();
			current.getPrev().setNext(current.getNext());
			current.getNext().setPrev(current.getPrev());
			size--;
		} //if
		
		if(size == 0)     //empty list
			head = tail = new Node<T>(); 
	} //method
	
	/**
	 * @return size - the size of the linked list
	 */
	public int size() {
		return size;
	} 
	
	/**
	 * @return empty - true if the list contains no nodes, and
	 * 			false otherwise
	 */
	public boolean isEmpty() {
		boolean empty = false;
		if (size == 0)
			empty = true;
		return empty;
	}
	
	/**
	 * Returns a string of each element in the list, with a space in 
	 * between them. If the list is empty, returns "Empty List."
	 * 
	 * @return data - a string representation of the elements in
	 * 			the list.
	 */
	public String toString() {
		String data = "";
		
		if (head.getData() == null) 
			data += "Empty List";
		else {
			for(int i = 0; i < size; i++)
			data+= getAt(i) + " ";
		}
		
		return data;
	}
	
	/**
	 * @return - the data stored in the head node.
	 */
	public T getHeadValue() {
		return head.getData();
	}
	
	/**
	 * @return the data stored in the tail node.
	 */
	public T getTailValue() {
		return tail.getData();
	}
	
	/**
	 * test main method
	 * @param args - the command line arguments
	 */
	public static void main (String[] args) {
		CircularDoublyLinkedList<Integer>list = 
							new CircularDoublyLinkedList<Integer>();
		
		System.out.println("Empty List: " + list.isEmpty());
		System.out.println();
		
		list.add(5);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
			
		list.addAt(3,1);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.addAfter(4,3);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.addBefore(1,2);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.add(5);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.removeAllInstances(5);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.removeAt(0);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();
		
		list.removeFirstInstance(4);
		System.out.println(list.toString());
		System.out.println("head: " + list.getHeadValue());
		System.out.println("tail: " + list.getTailValue());
		System.out.println();

		System.out.println("final size: " + list.size());	
	} //end test main
} 
