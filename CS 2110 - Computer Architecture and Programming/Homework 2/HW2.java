/**
 * HW2 file.  Bitwise operators
 * @author Jessie McGarry
 *
 */
public class HW2 
{
	/**
	 * Sets a nibble (4 bits) in a short (16 bits)
	 * 
	 * i.e. setNibble(0x3456, 0x8, 0) //=> 0x3458
	 *      setNibble(0x4925, 0xF, 2) //=> 0x4F25
	 * 
	 * @param num short that will be modified (will always be a 16 bit number)
	 * @param b nibble to insert into the short (will always be an 4 bit number)
	 * @param which determines which nibble gets modified 0 for least significant nibble (see examples)
	 * @return the modified short
	 */
	public static int setNibble(int num, int b, int which)
	{
		int mask = 0xF<<(which*4); //mult. has nothing to do with mask, please accept
		mask = ~mask;
		int newNum = num & mask;
		int nibble = b<<(which*4);
		newNum = newNum | nibble;
		return newNum;
	}
	
	/**
	 * Packs four chars (8 bits) into an int (32 bits)
	 * 
	 * i.e. pack(0x34, 0x68, 0x77, 0x23) //=> 0x34687723
	 *      pack(0xBA, 0xAD, 0xBE, 0xEF) //=> 0xBAADBEEF
	 * 
	 * @param c1 most significant 8 bits (will always be an 8 bit number)
	 * @param c2 2nd byte (will always be an 8 bit number)
	 * @param c3 3rd byte (will always be an 8 bit number)
	 * @param c4 least significant 8 bits (will always be an 8 bit number)
	 * @return an int formatted like so c1c2c3c4
	 */
	public static int pack(int c1, int c2, int c3, int c4)
	{
		return (c1<<24) + (c2<<16) + (c3<<8) + c4;
	}
	
	/**
	 * Checks if the number (int) passed in is a power of two
	 * (Warning solutions making use of iteration will not recieve credit).
	 * 
	 * i.e. isPowerOfTwo(2)    //=> 1
	 *      isPowerOfTwo(9001) //=> 0
	 * 
	 * @param num number to check if its a power of 2
	 * @return 1 if it is indeed a power of 2 0 otherwise.
	 */
	public static int isPowerOfTwo(int num)
	{
		if(num>>31 == -1)
			return 0;
		else {
			if ((num & (num-1)) == 0)
				return 1;
			else
				return 0;
		}
	}
	
	/**
	 * Determines the absolute value of a given number
	 * Remember, the math library cannot be used for this part
	 * Also, direct multiplication (*) and direct division (/) cannot be used.
	 * i.e. bitwiseAbs(0x8000) //=> 0x8000 or -32768 (Think about why this is!)
	 *      bitwiseAbs(0xF000) //=> 0x1000
	 *      bitwiseAbs(0x4455) //=> 0x4455
	 *      
	 * @param num a short (num will always be a 2's complement 16 bit number)
	 * @return another short (16 bits)
	 */
	public static int bitwiseAbs(int num)
	{
		if(num >> 15 == -1) {
			return (~num + 1) & (0x0000FFFF);
			 }
		else
			return num;
	}
	
	/**
	 * Sign extends a char to a short.
	 * 
	 * @param num an 8 bit 2's complement number
	 * @return a 16 bit 2's complement number the sign extended value of num.
	 */
	public static int signExtend(int num)
	{
		int extension;
		if(num>>7==1) {
			extension = 0xFF00;
			extension = extension + num;
		}
		
		else {
			extension = 0x0000;
			extension = extension + num;
		}
		return extension;
	}
}
