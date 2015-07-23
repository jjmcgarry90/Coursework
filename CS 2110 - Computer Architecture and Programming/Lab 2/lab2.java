
public class lab2 {
	/**
	 * Sets a bit that is if the bit was 0 then after this call
	 *  it should be 1
	 * 
	 * i.e setBit(0x12345678, 0) //=> 0x12345679
	 *     setBit(0x12345678, 4) //=> 0x12345678
	 */
	public static int setBit(int num, int bit)
	{
		int retVal = num | (1<<bit);
		return retVal;
	}

	/**
	 * Toggles a bit that is if the bit was 1 then after this call
	 *  it should be 0 and vice versa
	 * 
	 * i.e toggleBit(0x12345678, 0) //=> 0x12345679
	 *     toggleBit(0x12345678, 4) //=> 0x12345668
	 */
	public static int toggleBit(int num, int bit)
	{
		int retVal = num ^ (1<<bit);
		return retVal;
	}
	
	/**
	 * Resets a bit that is if the bit was 1 then after this call
	 *  it should be 0
	 * 
	 * i.e resetBit(0x12345678, 0) //=> 0x12345678
	 *     resetBit(0x12345678, 4) //=> 0x12345668
	 */
	public static int resetBit(int num, int bit)
	{
		int retVal = num & ~(1<<bit);
		return retVal;
	}
	
	/**
	 * Sets a byte in the upper or lower 8 bits of a short
	 * 
	 * i.e. setByte(0x3456, 0x68, 0) //=> 0x3468
	 *      setByte(0x4925, 0xFF, 1) //=> 0xFF25
	 * 
	 * @param num short that will be modified (will always be a 16 bit number)
	 * @param b byte to insert into the short (will always be an 8 bit number)
	 * @param which if which is 0 then modify the last 8 bits otherwise the first 8 bits are modified
	 * @return the modified short
	 */
	public static int setByte(int num, int b, int which)
	{
		return 0;
	}
	
	/**
	 * Checks if the short passed in is negative
	 * 
	 * i.e. isNegative(0x8000) //=> 1 
	 *      isNegative(0xF000) //=> 1
	 *      isNegative(0x4455) //=> 0
	 *      
	 * @param num a short (num will always be a 2's complement 16 bit number)
	 * @return 1 if it is indeed negative 0 otherwise
	 */
	public static int isNegative(int num)
	{
		return (num>>15) & 1;
	}
	
	public static void main (String[] args) {
		int a = 8;
		System.out.println(lab2.isNegative(100));
		
	}
}
