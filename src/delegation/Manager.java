package delegation;

import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.ArrayList;

public class Manager implements Employee{
	private Collection<Employee> employees;
	private int taskCount;
	
	public Manager(Collection<Employee> employees) {
		if (employees.isEmpty()) {
			throw new IllegalArgumentException("There are no employees for the manager!");
		}
		else {
			this.employees = employees;
		}
	}
	
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		this.taskCount++;
		return delegate().doCalculations(operation, value1, value2);
	}
	
	public void printDocument(String document) {
		delegate().printDocument(document);
		this.taskCount++;
	}
	
	public int getTaskCount() {
		return this.taskCount;
	}
	
	public int getResourceCount() {
		int count = 1;
		for (Employee employee : this.employees) {
			count += employee.getResourceCount();
		}
		return count;
	}
	public Employee delegate() {
		//System.out.println("DELEGATE");
		ArrayList<Employee> list = new ArrayList<Employee>(this.employees);
		Employee tempEmployee = list.get(0);
		//System.out.println(list.size());
		for (int i = 1; i < list.size(); i++) {
			//System.out.println("This is an iteration");
			if (list.get(i).getTaskCount() < list.get(i-1).getTaskCount()) {
				tempEmployee = list.get(i);
				if (list.get(i).getTaskCount() == 0) {
					return tempEmployee;
				}
			}
		}
		return tempEmployee;
	}
	public Collection<Employee> getEmployees() {
		return this.employees;
	}
}
