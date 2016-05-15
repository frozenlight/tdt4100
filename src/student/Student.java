package student;

public class Student {
	public String name;
	public int favorittal = 7;
	
	public Student() {
		System.out.println("Lagde en ny Student");
	}
	
	public Student(String name) {
		System.out.println("Lagde en ny Student med navn " + name);
		this.name = name;
	}
	
	public void kont() {
		System.out.print("Møtes til høsten!");
	}
	public void sjekk() {
		System.out.println("Nothing wrong!");
	}
	
	public static void main(String[] args) {
		Student stud = new Student();
		Student aba = new Abakule();
		
		stud = (Abakule) stud;
		
		aba = (Student) aba;
		aba = (Abakule) aba;
		
	}
}
