package testing;

import java.util.Comparator;

public class SubjectComparator implements Comparator<Subject>{
	
	@Override
	public int compare(Subject sub1, Subject sub2) {
		if (sub1.getInstituteCode() == sub2.getInstituteCode()) {
			return sub1.getCode()-(sub2.getCode());
		}
		return sub1.getInstituteCode().compareTo(sub2.getInstituteCode());
	}

}
