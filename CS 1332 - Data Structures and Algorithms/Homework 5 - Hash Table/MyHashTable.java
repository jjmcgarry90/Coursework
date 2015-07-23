//HW 5 written by Jessie McGarry
//Collaboration statement: I collaborated with Drew Norvell.

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a hash table which handles collisions using linked lists.
 * Each entry is generic so that, in theory, any type of object could be
 * stored.
 * @author Jessie McGarry
 *
 * @param <K> the type of the key used to store the value
 * @param <V> the type of the value being stored in the hash table
 */
public class MyHashTable <K,V> implements ITable<K,V> {
	/**
	 * The maximum capacity of the hash table.
	 */
	private int maxSize;
	/**
	 * A reference to the array of linked lists which make up the table.
	 */
	private LinkedList<MyHashEntry<K,V>>[] table;
	
	/**
	 * Constructor takes in a size which represents the default number 
	 * of buckets in the table.
	 * @param size default number of linked lists in the table.
	 */
	public MyHashTable(int size) {
		table = new LinkedList[size];
		for(int i = 0; i < size; i++)
			table[i] = new LinkedList<MyHashEntry<K,V>>();
		maxSize = size;
	}
	
	/**
	 * Clears the table leaving the number of slots intact.
	 */
	public void clear() {
		for(int i = 0; i < maxSize; i++) {
			table[i].clear();
		}
	}
	
	/**
	 * Returns true if the specified key is found anywhere in the hash
	 * table, and false otherwise.
	 */
	public boolean containsKey(Object key) {
		boolean found = false;
		int count = 0;
		while(!found && count < maxSize) {
			if(table[count].get(0).getKey() == key)
				return true;
			count++;
		}
		return false;
	}
	
	/**
	 * Returns true if the specified value is found anywhere in the hash
	 * table, and false otherwise.
	 */
	public boolean containsValue(Object value) {
		boolean found = false;
		int count = 0;
		while(!found && count < maxSize) {
			for(int i = 0; i < table[count].size(); i++)
				if(table[count].get(i).getValue() == value)
					return true;
			count++;
		}
		return false;
	}
	
	/**
	 * @return A stack containing all the elements within the table that
	 * have the specified key.
	 */
	public List<V> getAll(Object key) {
		if(key == null)
			return null;
		
		List<V> stack = new Stack<V>();
		for(int i = 0; i < maxSize; i++) {
			try {
				for(int j = 0; j < table[i].size(); j++)
					if(table[i].get(j).getKey().equals(key))
						stack.add(table[i].get(j).getValue());
			}	
			catch (Exception e) {} //throws an exception if the slot is empty
		}
		if (stack.isEmpty())
			return null;	
		return stack;
	}
	
	/**
	 * Returns true if the table contains no values, and false otherwise.
	 */
	public boolean isEmpty() {
		if (this.size() == 0)
			return true;
		else
			return false;
	}
	
	/**
	 * Inserts a key,value pair into the table using the specified values.
	 * The index of insertion is calculated by adding all the characters in 
	 * the string representation of the key.
	 * @param key the key of the element to be inserted
	 * @param value the value of the element to be inserted
	 */
	public boolean put(K key, V value) {
		try {
			MyHashEntry<K,V> entry = new MyHashEntry<K,V>(key,value);
			String keyString = key.toString();
			int code = 0;
			for (int i = 0; i < keyString.length(); i++) 
				code += (int) keyString.charAt(i);
			if (code > maxSize)
				code = code % maxSize;
			if (this.size()/(double)(this.maxSize) > .75) 
				this.reHash();
			table[code].add(entry);
			return true;
		}
		catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * This method is only called by the put method in this class when the
	 * load factor of the table is greater than .75 (when the ratio of filled
	 * slots to slot is greater than 3/4). It creates a new table double the 
	 * size of the original and rewrites the values to the new table.
	 */
	public void reHash() {
		LinkedList<MyHashEntry<K,V>>[] newTable = new LinkedList[maxSize * 2];
		for(int i = 0; i < table.length; i++)
			newTable[i] = table[i];
		for(int i = 0; i < newTable.length; i++) 
			if (newTable[i] == null)
				newTable[i] = new LinkedList<MyHashEntry<K,V>>();
		table = newTable;
		maxSize *= 2;
	}

	/**
	 * @return num - the total number of elements in the hash table, including
	 * collisions.
	 */
	public int size() {
		int num = 0;
		for (int i = 0; i < maxSize; i++)
			num += table[i].size();
		return num;
	}
	
	/**
	 * @return The number of linked lists in the table.
	 */
	public int getMaxSize() {
		return maxSize;
	}
	
	/**
	 * Creates a string representation of the hash table and
	 * return it. It should print the index of the hash table first and
	 * then list the key, value pairs held at that index.
	 * @return hastTable string representation of table
	 */
	public String toString() {
		String hashTable = "";
		for(int i = 0; i < maxSize; i++) {
			hashTable += (i + ": ");
			for(int j = 0; j < table[i].size(); j++)
				hashTable += ("(" + table[i].get(j).getKey() + ", " +
						table[i].get(j).getValue() + ") ");
			hashTable += "\n";
		}
		return hashTable;
	}
	
	/**
	 * Test main method tests each method in the class.
	 * @param args - Command line arguments
	 */
	public static void main (String[] args) {
		MyHashTable<String, Integer> table = new MyHashTable<String, Integer>(10);
		for(int i = 0; i < 15; i++) {
			table.put(String.valueOf(i),(Integer)i); //rehash will be called
		}
		table.put(String.valueOf(11), 11);
		System.out.println(table.toString());
		System.out.println(table.containsValue(11));
		System.out.println(table.containsValue(100));
		System.out.println(table.size());
		table.clear();
		System.out.println(table.isEmpty());
		table.put(String.valueOf(1), 0);
		table.put(String.valueOf(1), 1);
		table.put(String.valueOf(1), 2);
		System.out.println(table.getAll(String.valueOf(1)));
		System.out.println(table.getAll(String.valueOf(10)));
	}
}
