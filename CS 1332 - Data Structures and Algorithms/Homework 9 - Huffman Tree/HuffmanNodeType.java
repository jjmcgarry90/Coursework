
public interface HuffmanNodeType extends Comparable<HuffmanNodeType> {
    /**
     * Returns the byte represented by this leaf node.  Internal nodes
     * return null.
     * 
     * @return a Byte or null
     */
    public Byte getData();

    /**
     * Returns the number of times this byte is used.  Internal nodes
     * return the sum of the useCount of all their children.  This value
     * should NEVER be zero.
     *
     * @return useCount as an integer
     */
    public int getUseCount();

    /**
     * Returns the node to the left of this one.  Leaf nodes return null
     * here.
     *
     * @return the node to the left (possibly null)
     */
    public HuffmanNodeType getLeftChild();

    /**
     * Returns the node to the right of this one.  Leaf nodes return null
     * here.
     *
     * @return the node to the right (possibly null)
     */
    public HuffmanNodeType getRightChild();

    /**
     * Used to compare this node to another.  The ordering is based on the frequency (useCount), 
     * and then on the data if frequencies are equal, and then on the indices if frequencies 
     * are equal and both data are null.  Ordering by useCount is clear; if the useCount of the 
     * node is less than that of the node passed in, return -1 since the node is considered less than 
     * the node passed in.  When basing the ordering on the data, if one data is null and the other 
     * isn't, we know that the node with null data should be considered less.  If neither data is null, 
     * compare the data.  If both data are null, then compare the indices to see which node was created 
     * first.  In this Huffman tree, a node created second is considered to be less than a node created 
     * first. 
     * @param that another object to compare this to
     * @return an integer, -1 if the node is less than the node passed in, 1 if greater
     */
    public int compareTo(HuffmanNodeType that);

    /**
     * Returns the index, the counter for the order in which the node was created and added 
     * to the data structure of your choice.
     * 
     * @return the int index
     */
	public int getIndex();

	/**
	 * Sets the index, the counter for the order in which the node was created and added to the 
	 * data structure of your choice.
	 * 
	 * @param count, what the index should be set to
	 */
	public void setIndex(int count);
	

}

