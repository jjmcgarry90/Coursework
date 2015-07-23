//HW 3 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

import java.io.*;
import java.util.Scanner;

/**
 * This class decodes a given file using Caesar Cipher, a very
 * basic encryption, and produces a new file containing that
 * decrypted text. 
 * 
 * @author Jessie McGarry
 * @version 1.0
 *
 */
public class CaesarDecode {
	
	/**
	 * This method decodes a given string of text using
	 * a specified shift value. Each character within the
	 * string is changed to a new character using unicode
	 * indexes.
	 * @param line - the string to be decoded.
	 * @param shift - the difference in unicode index of
	 * 				 the old and new characters
	 * @return - a new string with decoded characters.
	 */
	public static String decode(String line, int shift) {
		String newLine = "";  
		char[] charArr = line.toCharArray();
		int value = 0;  //represents each char's int value

		for (int i = 0; i < charArr.length; i++) {
			if (charArr[i] != ' ') {
			// each char's int value is decremented
				value = (int) charArr[i];
				value -= shift; 
				if (value < 0)
					value = 128 + value;
				if (value >= 128)
					value = value % 128;
				charArr[i] = (char) value;
			}  //if
		newLine += charArr[i];
		}
		return newLine;
	}
	
	/**
	 * Reads from the the file specified in the command line
	 * arguments and shifts each letter in the file by some
	 * value (the shift key). It writes each new line to an
	 * output file to be saved in the same directory as the
	 * given file. "Encoded" is dropped from the given file
	 * name and replaced with "Decoded".
	 * 
	 * @param args - args[0] is the file, args[1] is the shift key
	 * @throws IOException - should there be any errors creating 
	 * 						the new file or in reading/writing
	 */
	public static void main (String[] args) throws IOException {
		
		File origFile = new File(args[0]);
		File newFile = new File(args[0].substring(0,
								args[0].indexOf('E')) + "Decoded.txt");
		PrintWriter output = new PrintWriter(
								new BufferedWriter(
									new FileWriter(newFile)));
		
		Scanner scan = new Scanner(origFile);
	
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String newLine = CaesarDecode.decode(line, Integer.parseInt(
														args[1]));
			output.println(newLine);
		} //while
		
		output.close();
	} //main
	
	
} //class
