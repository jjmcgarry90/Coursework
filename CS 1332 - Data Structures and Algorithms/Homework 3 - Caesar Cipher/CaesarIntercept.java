//HW 3 written by Jessie McGarry
//Collaboration Statement: I discussed the frequency array used
// 						    in this class with Drew Norvell.

import java.io.*;
import java.util.Scanner;

/**
 * The Caesar Cipher is rather easily cracked using frequency
 * analysis. This class does just that, cracking a given file
 * through user input and saving it to a new file.
 * @author jjmcgarry90
 * @version 1.0
 */
public class CaesarIntercept {
	
	/**
	 * This method returns the index of the maximum
	 * value in an array of ints. It is useful in 
	 * doing the frequency analysis of the main method.
	 * 
	 * @param num - an array of ints
	 * @return - the index of the max value in the array
	 */
	public static int intArrayMax(int[] num) {
		int max = 0, index = 0;
		for(int i = 0; i < num.length; i++) {
			if (num[i] > max) {
				max = num[i];
				index = i;
			}
		}
		return index;
	}
	
	//I realize the following code is far from elegant, my apologies.
	/**
	 * This method creates a new cracked file from the given 
	 * encrypted file in the command line arguments. The user is
	 * prompted to tell whether the crack is correct. If not, a
	 * different approach is attempted to crack the original file.
	 * Once cracked, the new file is saved in the same directory.
	 * "Encoded" is removed from the filename and replaced with
	 * "Cracked".
	 * 
	 * @param args - a single string containing the name of the
	 * 					encoded file.
	 * @throw IO Exception - should there be any errors creating 
	 * 						the new file or in reading/writing
	 */
	public static void main (String[] args) throws IOException {
		
		File origFile = new File(args[0]);
		File newFile = new File(args[0].substring(0,
								args[0].indexOf('E')) + "Cracked.txt");
		PrintWriter output = new PrintWriter(
								new BufferedWriter(
									new FileWriter(newFile)));
		
		Scanner scanner = new Scanner(origFile);
		int[] frequencyArr = new int[128];
		
		for (int i = 0; i < frequencyArr.length; i++)
			frequencyArr[i] = 0; //fills freq array with zeros
			
		int index = 0;
		
		//finds the unicode index of the most common character
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			char[] charArr = line.toCharArray();
						
			for (int i = 0; i < charArr.length; i++) {
				if(charArr[i] != ' ') {
					index = (int) charArr[i];
					frequencyArr[index]++;
				} //if
			} //for	
		} //while
		
		index = CaesarIntercept.intArrayMax(frequencyArr); //most common
		int shift = Math.abs(index - 101);// 101 is the index of 'e'
		Scanner scanner2 = new Scanner(System.in);
		boolean correct = false;
		int attempt = 0;
		
		while(!correct) {
			Scanner scanner3 = new Scanner(origFile);
			output.println("Attempt" + " " + (attempt + 1) + ":");
			int count = 0; //line counter
			while(scanner3.hasNext()) {
				String line = scanner3.nextLine();
				String newLine = CaesarDecode.decode(line, shift);
				if(count < 15) //print no more than 15 lines
					System.out.println(newLine);
				output.println(newLine);
				count++;
			}
			
			System.out.println();
			System.out.println("Is this correct?" +
								" (Enter 1 for YES, 2 for NO)");
			int response = scanner2.nextInt();
			if(response == 1)
				correct = true;
			else {
				attempt++;
				switch (attempt) {
					case 1:
						output.println();
						shift *= -1; 
						break;
					case 2:
						output.println();
						//116 is the index of 't'
						shift = Math.abs(index - 116);
						break;
					case 3:
						output.println();
						shift *= -1; 
						break;
					case 4:
						output.println();
						//97 is the index of 'a'
						shift = Math.abs(index - 97);
						break;
					case 5:
						correct = true;
						output.println();
						output.println("Crack Failed.");
						break;
				} //switch
			} //else
		} //while	
		output.close();
	} //main
}
