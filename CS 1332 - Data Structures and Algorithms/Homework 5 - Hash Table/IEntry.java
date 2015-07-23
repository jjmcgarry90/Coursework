
/**
 * This interface should be implemented by MyHashEntry.java which
 * should server as a wrapper for a basic key,value pair to be
 * held in the hash table.
 * 
 * You should also implement a constructor that takes in a key of
 * type K and a value of type V and creates a hash entry containing
 * that key,value pair.
 * 
 * It is necessary to keep the key with the value it maps to even
 * after hashing takes place so that rehashing can occur if need be.
 *
 * @param <K> the type of the key
 * @param <V> the type of the values
 */
public interface IEntry<K, V> {
	/**
	 * Returns the key this hash entry represents.
	 * @return the key
	 */
	public K getKey();
	
	/**
	 * Returns the value this hash entry represents.
	 * @return the value
	 */
	public V getValue();
}
