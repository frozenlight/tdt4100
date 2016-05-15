package student;

public class Abakule extends Student {
	
	public Abakule() {
		System.out.println("Lagde en ny kule");
	}
	
	@Override
	public void sjekk() {
		if (4 != 5) {
			System.out.println("Noe er galt!");
		}
	}
	
	public static void main(String[] args) {
		Abakule aba = new Abakule();
		aba.sjekk();
		
	}
}
