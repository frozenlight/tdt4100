package stateandbehavior;

public class UpOrDownCounter {
	//Fields
	public int number;
	public int end;
	public int direction;
	
	//Methods
	UpOrDownCounter(int start, int end) throws IllegalArgumentException {
		if (start > end) {
			direction = -1;
		}
		else if (start < end) {
			direction = 1;
		}
		else if (start == end) {
			throw new IllegalArgumentException("Start and end values have to be diffrent from each other");
		}
		this.number = start;
		this.end = end;
	}
	int getCounter() {
		return number;
	}
	boolean count() {
		if (number == end) {
			return false;
		}
		else {
			number += direction;
			return true;
		}
	}	
}
