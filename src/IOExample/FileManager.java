package IOExample;

import java.io.*;

public class FileManager {

	public static char[] readChars(String adress) throws IOException {
		Reader reader;
		reader = new FileReader(new File(adress));
		char[] buffer = new char[1000];
		
		int charCount = reader.read(buffer);
		System.out.println(charCount);
		reader.close();
		return buffer;
	}
	
	public void writeString(String outString, String adress) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(adress);
		writer.println(outString);
		writer.flush(); //Flushes the buffer
		writer.close();
	}

}
