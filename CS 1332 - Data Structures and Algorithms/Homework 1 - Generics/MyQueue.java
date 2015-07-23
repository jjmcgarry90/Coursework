//HW 1 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

/**
 * This class provides a service similar to that of an ArrayList. 
 * An array is created from the constructor (of type Object by default)
 * and manipulated using the methods.
 * 
 * @author Jessie McGarry
 * @param <T>  - the type of the elements in the array (i.e. String)
 */
public class MyQueue<T>{
	
	/**
	 * The array to be manipulated.
	 */
	private T[] queue;
	
	/**
	 * This default consructor creates an empty array.
	 */
	public MyQueue() {
		queue = (T[])(new Object[]{});
	}
	
	/**
	 * Creates the array with a single element using the given value.
	 * @param value - the value of the first element to be in the array
	 */
	public MyQueue(T value) {
		queue = (T[])(new Object[] {value});
	}
	
	/**
	 * 'Adds' the given element to the back of the array. This method
	 * does so by creating a slightly larger array and then copying
	 * the values from the initial array.
	 * @param value - the value to be added to the array
	 */
	public void enqueue(T value) {
		int size = queue.length + 1;
		T[] newQueue = (T[])(new Object[size]);
		
		newQueue[newQueue.length-1] = value;
		for (int i = 0; i < newQueue.length-1; i++)
			newQueue[i] = queue[i];
		
		queue = newQueue;
	}
	
	/**
	 * 'Removes' the first element in the array and returns it. This
	 * method does so by creating a smaller array and then copying the
	 * value from the initial array.
	 * @return - the first element in the initial array
	 */
	public T dequeue() {
		if (queue.length == 0)
			return null;
		T element = queue[0];
		T[] newQueue = (T[])(new Object[queue.length-1]);
		
		for(int i = 0; i < newQueue.length; i++) 
			newQueue[i] = queue[i+1];
		queue = newQueue;
		
		return element;
	}
	
	/**
	 * 'Clears' the array by creating a new empty array.
	 */
	public void purge() {
		T[] newQueue = (T[])(new Object[]{});
		queue = newQueue;
	}
	
	/**
	 * Checks to see if the array is empty.
	 * @return - boolean value representing if the string is empty(true)
	 * or contains elements (false)
	 * 
	 */
	public boolean isEmpty() {
		try {
			T test = queue[0];
			return false;
		}
		
		catch (Exception e) {
			return true;
		}
		
	}
	
	/**
	 * Sets up a string of the all the elements contained in the
	 * array with a space in between them. If there are no elements,
	 * this method returns an empty string.
	 * @return - the string to be printed
	 */
	public String printQueue() {
		String elements = "";
		for(int i = 0; i < queue.length; i++) {
			elements+= queue[i] + " ";
		}
		return elements; 
	}
	
	/**
	 * @return - the size of the array as an int.
	 */
	public int getSize() {
		return queue.length;
	}
	
	/**
	 * Tests each of the constructors and every method in this class.
	 * @param args - Command Line Arguments
	 */
	public static void main (String[] args) {
		
		// first constructor tests
		MyQueue queue = new MyQueue<Integer>();
		System.out.println(queue.isEmpty());
		System.out.println(queue.getSize());
		System.out.println(queue.dequeue());
		queue.enqueue(25);
		queue.enqueue(50);
		System.out.println(queue.printQueue());
		queue.purge();
		System.out.println(queue.printQueue()); // prints empty line
		
		// second constructor tests
		MyQueue queue2 = new MyQueue<String>("Jessie");
		queue2.enqueue("James");
		queue2.enqueue("McGarry");
		System.out.println(queue2.getSize());
		System.out.println(queue2.dequeue());
		System.out.println(queue2.isEmpty());
		System.out.println(queue2.printQueue());
		queue2.purge();
		System.out.println(queue2.printQueue()); //empty line
	}
}
