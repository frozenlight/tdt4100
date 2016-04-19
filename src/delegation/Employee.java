package delegation;

import java.util.function.BinaryOperator;
import java.util.Collection;

public interface Employee {
	
	double doCalculations(BinaryOperator<Double> operation, double value1, double value2);
	
	void printDocument(String document);
	
	int getTaskCount();
	
	int getResourceCount();
	
	Collection<Employee> getEmployees();
}
