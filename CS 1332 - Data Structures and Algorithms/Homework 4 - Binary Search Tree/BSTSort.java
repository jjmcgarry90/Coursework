//HW 4 written by Jessie McGarry
//Collaboration Statement: This is solely my work.

import java.io.*;
import java.util.Scanner;

/**
 * This class sorts the words of a specified file into a binary search tree.
 * The nodes in the tree are then written to an ouput file in alphabetical
 * order. 
 * @author Jessie McGarry
 * @version 1.0
 */
public class BSTSort {
	/**
	 * (See above) The output file is created from the name of the input file
	 *  - xxx.txt - becomes the output file xxxsorted.txt
	 * @param args - the input file containing text to be sorted.
	 * @throws IOException - should there be any errors reading/writing files.
	 */
	public static void main (String [] args) throws IOException {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[0].substring(0, args[0].indexOf(".txt"))
																+ "sorted.txt");
		PrintWriter output = new PrintWriter(new BufferedWriter(
											 new FileWriter(outputFile)));
		
		BST<String> bst = new BST<String>();
		Scanner scan = new Scanner(inputFile);
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			char[] array = word.toCharArray();
			for (int i = array.length-1; i >= 0; i--) {
				//caps : 65 - 90   lower: 97 - 123
				if (!((int)(array[i]) > 64 && (int)(array[i]) < 91) &&
					!((int)(array[i]) > 96 && (int)(array[i]) < 124))  {
					word = word.replace(array[i], ' ');
					word = word.trim(); 
				}
			}
			bst.insert(word);
		}
		String list = bst.inOrderTraversal();
		String[] elements = list.split(" ");
		for (int i = 0; i < elements.length; i++) {
			output.println(elements[i]);
		}
		output.close();
	}
}
