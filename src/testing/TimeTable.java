package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TimeTable implements Iterable<Subject>{
	
	List<Subject> timetable = new ArrayList<>();
	
	public TimeTable(Subject... sub) {
		Arrays.asList(sub);
		for (Subject emne: sub) {
			this.timetable.add(emne);
		}
	}
	
	public List<Subject> getTimetable() {
		
		return this.timetable;
	}
	
	public void sortTimetable() {
		Collections.sort(this.timetable,new SubjectComparator());
	}
	
	public void sortTimeTable() {
		Collections.sort(this.timetable,((a, b) -> a.getName().compareTo(b.getName())));
	}
	
	public List<Subject> getSubjectFromInstitute(String institute) {
		//List<Subject> list = new ArrayList<>();
		
		//Could use for loop to check for every object, but.............
		
		return this.timetable.stream().filter(sub -> sub.getInstituteCode() == institute).collect(Collectors.toList());
	}
	
	/*public List<Subject> getSubjectFromInstitute(Predicate pred) {
		return this.timetable.stream().filter(pred).collect(Collectors.toList());
	}*/
	
	public double sumPoints() {
		return this.timetable.stream().mapToDouble(s -> s.getPoints()).sum();
	}
	
	public void setPoints() {
		this.timetable.stream().forEach(s -> s.setPoints(7.5));
	}
	
	public boolean doesInstituteExist(String institute) {
		return this.timetable.stream().anyMatch(s -> s.getInstituteCode() == institute);
	}
	
	public static void main(String[] args) {
		Subject java = new Subject("Objectorientert programmering","TDT",4100,7.5,18);
		Subject matte1 = new Subject("Matematikk 1","TMA",4100,7.5,100);
		Subject krets = new Subject("Krets og digitalteknikk","TFE",4100,7.5,28);
		Subject samfunn = new Subject("Samfunnsskit","SOK",4100,10.0,50);
		
		TimeTable table = new TimeTable(java,matte1,krets,samfunn);
		System.out.println(table.getTimetable());
		
		Iterator<Subject> it = table.iterator();
		while (it.hasNext()) {
			Subject sub = it.next();
			if (sub == samfunn) {
				it.remove();
			}
		}
	}
	
	public Iterator<Subject> iterator() {
		return this.timetable.iterator();
	}
}
