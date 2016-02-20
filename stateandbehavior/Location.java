package stateandbehavior;

public class Location {
	
	//Fields 
	public int x;
	public int y;
	
	//Methods
	public void up() {
		y -= 1;
	}
	public void down() {
		y += 1;
	}
	public void left() {
		x -= 1;
	}
	public void right() {
		x += 1;
	}
	//toString method
	public String toString() {
		return "Location is " + x + " , " + y;
	}
	//Main method
	public static void main(String[] args) {
		Location location = new Location();
		location.up();
		location.down();
		location.left();
		location.right();
	}
}
