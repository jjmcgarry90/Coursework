//HW 5 written by Jessie McGarry
//Collaboration Statement: I collaborated with Drew Norvell

/**
 * Represents a generic key, value pair to be used with the
 * MyHashTable class.
 * @author Jessie McGarry
 */
public class MyHashEntry <K,V> implements IEntry <K,V> {
	/**
	 * the key of the element
	 */
	private K key;
	/**
	 * the value stored in the element
	 */
	private V value;
	
	/**
	 * Constructor
	 * @param key the key of the element
	 * @param value the value stored in the element
	 */
	public MyHashEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return the key of the element
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * @return the value of the element
	 */
	public V getValue() {
		return value;
	}
}
