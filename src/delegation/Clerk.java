package delegation;

//import java.util.ArrayList;
import java.util.function.BinaryOperator;
import java.util.Collection;
import java.util.Collections;

public class Clerk implements Employee{
	private int taskCount;
	private Printer printer;
	
	public Clerk(Printer printer) {
		this.taskCount = 0;
		this.printer = printer;
	}
	
	public double doCalculations(BinaryOperator<Double> operation, double value1, double value2) {
		this.taskCount++;
		return operation.apply(value1,value2);
	}
	
	public void printDocument(String document) {
		this.taskCount++;
		this.printer.printDocument(document, this);
	}
	
	public int getTaskCount() {
		return this.taskCount;
	}
	
	public int getResourceCount() {
		return 1;
	}
	public Collection<String> getPrintHistory() {
		return printer.getPrintHistory(this);
	}
	public Collection<Employee> getEmployees() {
		return Collections.emptyList();
	}
}
