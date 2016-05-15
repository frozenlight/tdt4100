package testing;

public class Subject {
	String name;
	String instituteCode;
	int code;
	double points;
	int numberOfYears;
	
	public Subject(String name, String instituteCode, int code, double points, int numberOfYears) {
		super();
		this.name = name;
		this.instituteCode = instituteCode;
		this.code = code;
		this.points = points;
		this.numberOfYears = numberOfYears;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInstituteCode() {
		return instituteCode;
	}
	public void setInstituteCode(String instituteCode) {
		this.instituteCode = instituteCode;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public int getNumberOfYears() {
		return numberOfYears;
	}
	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}
	
	public int compareTo(Subject subject) {
		return this.name.compareTo(subject.getName());
	}


	@Override
	public String toString() {
		return "Subject [name=" + name + ", institudeCode=" + instituteCode + ", code=" + code + ", points=" + points
				+ ", numberOfYears=" + numberOfYears + "]";
	}
	
	
	
}
