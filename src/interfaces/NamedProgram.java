package interfaces;

import java.util.Scanner;
//import java.util.Hashtable;
//import java.beans.Introspector;
//import java.util.List;

public class NamedProgram {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String line;
		System.out.println("This program is called Named");
		//Hashtable<String,Person1> Example = new Hashtable<String,Person1>();
		
		System.out.println();
		
		while (true){
			
			System.out.println();
			System.out.println("\n1.| 2. | 3.  | 4.  | 5.  | 6.  | 7. ");
			line = scanner.nextLine();
			
			if (line.equals("1")) {
				try {
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("2")) {
				try {
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("3")) {
				try {	
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("4")) {
				try {
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("5")) {
				try {
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("6")) {
				try {
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("7")) {
				try {
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("8")) {
				break;
			}
			else {
				System.out.println("Invalid input");
			}

		}
		scanner.close();	

	}

}