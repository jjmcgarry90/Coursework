//HW 5 written by Jessie McGarry
//Collaboration Statement: I discussed this with Drew Norvell

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  Implements a system that can do a phone number lookup for a given 
 *  name by utilizing the MyHashTable class.
 * @author Jessie McGarry
 *
 */
public class PhoneLookup {
	/**
	 * See above
	 * @param args - Command Line args - [0] should be the file with
	 * the phone numbers and everything else will be searched for in that file
	 * @throws FileNotFoundException - should the input file not exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File(args[0]);
		boolean ended = false;
		String lookup = "";
		int arg  = 1;
		while(!ended) {  //constructs a string from the command line args
			try{
				lookup += args[arg] + " ";
				arg++;
			}
			
			catch (Exception e) {
				ended = true;
			}
		}
		lookup = lookup.trim();
		lookup = lookup.toLowerCase();
		MyHashTable<String,String> table = new MyHashTable<String,String>(20);
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {  //adds each key,value pair to the table
			String line = scan.nextLine().toLowerCase();
			String key = line.split(" ", 2)[1];
			String value = line.split(" ", 2)[0];
			table.put(key, value);
		}
		System.out.println(table.getAll(lookup)); 
	}
}
