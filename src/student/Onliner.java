package student;

public class Onliner extends Student {
	
	private String name;
	
	public Onliner(String name) {
		this.name = name;
		System.out.println(this.name);
	}
	
	public static void main(String[] args) {
		Onliner one = new Onliner("Ole");
		one.kont();
		System.out.println(one.favorittal);
		
	}
}
