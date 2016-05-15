package IOExample;

import java.util.Scanner;

public class ScannerExample {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		print("Write your name here: ");
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			print("Hei: " + line);
			if (line.equals("lukk")) {
				break;
			}
			print("Write your name here: ");
		}
		scanner.close();
		
	}
	
	static private void print(String string) {
		System.out.println(string);
	}
}
