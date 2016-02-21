package sudoku;

public class SudokuTile {
	private int value;
	private int prevValue;
	private boolean isValid;
	private boolean isStatic;
	
	public SudokuTile(String inputValue) {
		if (initDot(inputValue)) {
			value = 0;
			setType(false);
			setValidity(true);
		}
		else {
			value = Integer.parseInt(inputValue);
			setType(true);
			setValidity(true);
		}
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int inputValue) {
		prevValue = value;
		value = inputValue;
	}
	public int getPrevValue() {
		return prevValue;
	}
	public boolean getType() {
		return isStatic;
	}
	public void setType(boolean type) {
		isStatic = type;
	}
	public boolean getValidity() {
		return isValid;
	}
	public void setValidity(boolean validity) {
		if (value != 0) {
			isValid = validity;
		}
	}
	private boolean initDot(String inputValue) {
		if (getChar(inputValue) == '.') {
			return true;
		}
		else {
			return false;
		}
	}
	private char getChar(String inputString) {
		return inputString.charAt(0);
	}
	public void status() {
		System.out.println("Value: " + value + " isValid: " + isValid + " isStatic: " + isStatic);
	}
}
