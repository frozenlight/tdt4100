package delegation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;
import java.util.Hashtable;
import java.util.function.BinaryOperator;

public class OfficeProgram {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String line;
		System.out.println("The Office Program");
		Hashtable<String,Employee> managers = new Hashtable<String,Employee>();
		Hashtable<String,BinaryOperator<Double>> operators = new Hashtable<String,BinaryOperator<Double>>();
		Collection<Employee> clerks = new ArrayList<Employee>();
		Collection<Employee> managerList;
		Printer printer = new Printer();
		operators.put("+", ((n1,n2) -> n1+n2));
		operators.put("-", ((n1,n2) -> n1-n2));
		operators.put("*", ((n1,n2) -> n1*n2));
		operators.put("/", ((n1,n2) -> n1/n2));
		for (int i = 0; i < 3; i++) {
			clerks.add(new Clerk(printer));
		}
		managers.put("steve",new Manager(clerks));
		Employee active = managers.get("steve");
		System.out.println("Created the manager steve with 3 clerks");
		
		while (true){
			
			System.out.println();
			System.out.println("\n1. UseManager | 2. DoCalculations | 3. PrintDocument | 4. DocumentHistory | 5. CreateManagerManager | 6.  | 7. CreateManager");
			line = scanner.nextLine();
			
			if (line.equals("1")) {
				try {
					System.out.println("What is the name of the Manager?");
					String name = scanner.nextLine();
					if (!managers.containsKey(name)) {
						System.out.println("How many Clerks should this manager have?");
						clerks = new ArrayList<Employee>();
						int input = Integer.parseInt(scanner.nextLine());
						for (int i = 0; i < input; i++) {
							clerks.add(new Clerk(printer));
						}
						managers.put(name,new Manager(clerks));
						System.out.println("Created the manager " + name + " with " + input + " clerks");
					}
					active = managers.get(name);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("2")) {
				try {
					System.out.println("Give the operand and the two number you want to calculate (ex: * 2 5");
					//String[] input2 = {"+", "2", "5"};
					String[] input = scanner.nextLine().split(" ");
					System.out.println(input[0] + input[1] + input[2]);
					
						System.out.print(active.doCalculations(operators.get(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("3")) {
				try {
					System.out.println("Type the contents of the document: ");
					active.printDocument(scanner.nextLine());
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("4")) {
				try {
					for (Employee thisEmployee : active.getEmployees()) {
						getHistory(thisEmployee,printer);
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("5")) {
				try {
					System.out.println("What is the name of the new ManagerManager?");
					String name = scanner.nextLine();
					if (!managers.containsKey(name)) {
						System.out.println("How many Managers should this Manager control?");
						managerList = new ArrayList<Employee>();
						int input = Integer.parseInt(scanner.nextLine());
						int count = 0;
						if (managers.size() < input) {
							input = managers.size();
							System.out.println("There are only " + managers.size() + " Managers in existance, and they were all added under this manager");
						}
						for (String key : managers.keySet()) {
							if (count >= input) {
								break;
							}
							managerList.add(managers.get(key));
							count++;
						}
						managers.put(name,new Manager(clerks));
						System.out.println("Created the manager " + name + " with " + active.getEmployees().size() + " Managers under it");
					}
					active = managers.get(name);
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
	private static void getHistory(Employee employee, Printer printer) {
		System.out.println("forEvery");
		if (!employee.getEmployees().isEmpty()) {
			System.out.print("getEmployee: ");
			for (Employee thisEmployee : employee.getEmployees()) {
				System.out.println("forEmployee");
				getHistory(thisEmployee,printer);
				
			}
		}
		else {
			System.out.print("getDocument: ");
			for (String document : printer.getPrintHistory(employee)) {
				System.out.println("[" + document + "]");
			}
		}
		
	}
}
