package delegation;

import java.util.Comparator;

public class TaskCountComparator implements Comparator<Employee>{
	
	public int compare(Employee a1, Employee a2) {
		return a2.getTaskCount()-a1.getTaskCount();
	}

}
