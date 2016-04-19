package sudokuinterface;

public class SudokuTranslate {
	private boolean exists;
	public int stringInt(String letter) {
		if (letter.charAt(0) == 'a') {
			return 0;
		}
		else if (letter.charAt(0) == 'b') {
			return 1;
		}
		else if (letter.charAt(0) == 'c') {
			return 2;
		}
		else if (letter.charAt(0) == 'd') {
			return 3;
		}
		else if (letter.charAt(0) == 'e') {
			return 4;
		}
		else if (letter.charAt(0) == 'f') {
			return 5;
		}
		else if (letter.charAt(0) == 'g') {
			return 6;
		}
		else if (letter.charAt(0) == 'h') {
			return 7;
		}
		else if (letter.charAt(0) == 'i') {
			return 8;
		}
		else {
			return 9;
		}
	}
	public String intString(int number) {
		if (number == 0) {
			return "a";
		}
		else if (number == 1) {
			return "b";
		}
		else if (number == 2) {
			return "c";
		}
		else if (number == 3) {
			return "d";
		}
		else if (number == 4) {
			return "e";
		}
		else if (number == 5) {
			return "f";
		}
		else if (number == 6) {
			return "g";
		}
		else if (number == 7) {
			return "h";
		}
		else if (number == 8) {
			return "i";
		}
		else {
			return "z";
		}
	}
	public boolean stringBoolean(String num) {
		if (num.charAt(0) == '1') {
			System.out.print(" true");
			return true;
		}
		else {
			System.out.print(" false");
			return false;
		}
	}
	public SudokuTranslate() {
		exists = true;
	}
	public boolean getExistance() {
		return exists;
	}
	public int boolInt(boolean value) {
		if (!value) {
			return 0;
		}
		else {
			return 1;
		}
	}
	public boolean intBool(int number) {
		if (number == 0) {
			return false;
		}
		else {
			return true;
		}
	}
}