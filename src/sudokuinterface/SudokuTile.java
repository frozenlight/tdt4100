package sudokuinterface;

import java.util.ArrayList;

public class SudokuTile {
	private SudokuTranslate translate = new SudokuTranslate();
	private int value;
	//private int prevValue;
	private boolean isValid;
	private final boolean isStatic;
	
	public SudokuTile(String inputValue) {
		if (initDot(inputValue)) {
			value = 0;
			isStatic = false;
			setValidity(true);
		}
		else {
			value = Integer.parseInt(inputValue);
			isStatic = true;
			setValidity(true);
		}
	}
	public SudokuTile(ArrayList<String> object) {
		value = Integer.parseInt(object.get(0));
		isValid = translate.intBool(Integer.parseInt(object.get(1)));
		isStatic = translate.intBool(Integer.parseInt(object.get(2)));
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int inputValue) {
		//prevValue = value;
		value = inputValue;
	}
	/*public int getPrevValue() {
		return prevValue;
	}*/
	public boolean getType() {
		return isStatic;
	}
//	public void setType(boolean type) {
//		isStatic = type;
//	}
	public boolean getValidity() {
		return isValid;
	}
	public void setValidity(boolean validity) {
		if (value != 0 && isStatic == false) {
			isValid = validity;
		}
		else {
			isValid = true;
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
