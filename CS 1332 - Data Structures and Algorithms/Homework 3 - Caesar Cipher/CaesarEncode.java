//HW 3 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

import java.io.*;
import java.util.Scanner;

/**
 * This class encodes a given file using Caesar Cipher, a very
 * basic encryption, and produces a new file containing that
 * encryption. 
 * 
 * @author Jessie McGarry
 * @version 1.0
 *
 */
public class CaesarEncode {
	
	/**
	 * Reads from the the file specified in the command line
	 * arguments and shifts each letter in the file by some
	 * value (the shift key). It writes each new line to an
	 * output file to be saved in the same directory as the
	 * given file and called "(given filename)Encoded.txt".
	 * 
	 * 
	 * @param args - args[0] is the file, args[1] is the shift key
	 * @throws IOException - should there be any errors creating 
	 * 						the new file or in reading/writing
	 */
	public static void main (String[] args) throws IOException {
		
		File origFile = new File(args[0]);
		File newFile = new File(args[0].substring(0,
								args[0].indexOf('.')) + "Encoded.txt");
		PrintWriter outFile = new PrintWriter(
								new BufferedWriter(
									new FileWriter(newFile)));
		
		Scanner scan = new Scanner(origFile);
		
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String newLine = "";  
			char[] charArr = line.toCharArray();
			int value = 0;  //represents each char's int value
			
			for (int i = 0; i < charArr.length; i++) {
				if (charArr[i] != ' ') {
					// each char's int value is incremented
					value = (int) charArr[i];
					value += Integer.parseInt(args[1]);
					if (value < 0)
						value = 128 + value;
					if (value >= 128)
						value = value % 128;
					charArr[i] = (char) value;
				}  //if
				
				newLine += charArr[i];
			} //for
			
			outFile.println(newLine);
		} //while
		
		outFile.close();
	} //main
}
