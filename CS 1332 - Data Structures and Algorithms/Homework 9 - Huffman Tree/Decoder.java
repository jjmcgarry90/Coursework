//Collaboration Statement: This is solely my work.

import java.io.*;
/**
 * Decodes a specific type of file (ext. ".cf") compressed using 
 * Huffman's method.
 * 
 * @author - Jessie McGarry
 */
public class Decoder {
	/**
	 * This method will create a Huffman tree and use it to write the decoded 
	 * text to a new file less the '.cf' extension.
	 * @param args - the name of the compressed file.
	 * @throws IOException - when there is no file or an error reading.
	 */
	public static void main(String[] args) throws IOException {
		
		int[] header = new int[772];
		int Iindex = 0;
		DataInputStream filestream = null;
		
		try {
			filestream = new DataInputStream(
						new FileInputStream( new File(args[0])));
			while ((filestream.available()>0) && (Iindex<772)) {
				header[Iindex] = filestream.readUnsignedByte();
				Iindex++;
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		int[] frequencies = new int[256];
		for (int i = 0; i < 768; i+=3) 
			frequencies[i/3] = (int)(header[i+4] + header[i+5] + header[i+6]);
		HuffmanNodeType root = HuffmanNode.buildHuffmanTree(frequencies);
		HuffmanNodeType cursor = root;
		
		File output  = new File(args[0].substring(0, args[0].length() - 3));
		BufferedWriter writer = new BufferedWriter(new FileWriter(output));
		int byteValue;
		int direction;
		try {
			while(filestream.available() > 0) {
				byteValue = filestream.readUnsignedByte();
				if(0 < byteValue && byteValue < 256 ){
					for(int i = 7; i >-1; i--) {
						direction = (byteValue>>i) & 1;
						if (direction == 1 ) {
							cursor = cursor.getRightChild();
							if(cursor.getData() != null) {
								writer.write((char)cursor.getData().intValue());
								cursor = root;
							}
						}
						
						if (direction == 0) {
							cursor = cursor.getLeftChild();
							if(cursor.getData() != null) {
								writer.write((char)cursor.getData().intValue());
								cursor = root;
							} //if
						} //if
					} //for
				} //if
			} //while
		} //try
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
