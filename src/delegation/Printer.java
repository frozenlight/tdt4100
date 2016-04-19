package delegation;

//import java.util.List;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Printer {
	//private Collection<String> empty;
	private Hashtable<Employee,Collection<String>> history = new Hashtable<Employee,Collection<String>>();
	
	public Printer() {
		//empty.add("There is no record of this clerk ever printing any documents");
	}
	
	void printDocument(String document, Employee employee) {
		if (! history.containsKey(employee)) {
			history.put(employee, new ArrayList<String>());
		}
		history.get(employee).add(document);
		System.out.println(document);
	}
	
	Collection<String> getPrintHistory(Employee employee) {
		if (! history.containsKey(employee)){
			return Collections.emptyList();
		}
		return new ArrayList<String>(history.get(employee));
	}
}
