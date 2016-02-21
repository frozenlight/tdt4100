package sudoku;

//import java.util.ArrayList;

public class SudokuBoard {
	SudokuTile tile;
	SudokuTranslate translate;
	private SudokuTile[][] boardArray;
	private SudokuTile[][] prevBoardArray;
	private boolean makeBoardDebug = false;
	private boolean inputDebug = false;
	private boolean firstCheckDebug = false;
	private boolean checkErrorDebug = false;
	private boolean checkChainDebug = false;
	
	public SudokuTile[][] getBoardArray() {
		return boardArray;
	}
	
	public SudokuBoard(String inputBoard) {				// SudokuBoard -> Constructor
		translate = new SudokuTranslate();				// Creates the SudokuTranslate Class as "translate"
		int counter = 0;								// Creates counter
		System.out.println(inputBoard.length());		// Prints out the length of the board to tell the user if it's right
		if (inputBoard.length() == 81) {				// Checks is the board is of correct length
			String[] buildArray = new String [81];		// Creates buildArray with 81x indices
			boardArray = new SudokuTile[9][9];			// Creates boardArray with 9y * 9x indices, this array is used to contain all off the playing board
			buildArray = inputBoard.split("");			// Splits the user input board(String) into an Array
			for (int i = 0; i < 9; i++) {				// To access the indices of the 2d array "boardArray" correctly, we need two for loops
				for (int j = 0; j < 9; j++) {
					boardArray[i][j] = new SudokuTile(buildArray[counter]);		// Creates a SudokuTile Class/Object for each index of "boardArray", with the input of it's value
					counter += 1;						// Since buildArray is 1d, and boardArray is 2d, we need a counter to input the correct index to the 1d array
					if (inputDebug) {					// Debug enabler, to toggle between easy debugging and a clean console output on demand
						System.out.println("getValue er " + boardArray[i][j].getValue());		// Prints out the value of the newly created Class/Object
					}
				}
			}
		}
		else {											// If the board the user entered is not of correct length, an IllegelArgumentException is thrown
			throw new IllegalArgumentException ("the board contains too few or too many feilds");
		}
	}
	
	public String makeBoard() {							// MakeBoard, used be input one from "SudokuProgram", prints out the board to the console
		String line = "\n";								// Creates line, changes line in the console
		String spacer = "   " + "+---------+---------+---------+";				//Creates spacer, separator of the vertical grids
		String boardString = line + spacer + line;		// Creates boardString, the string containing the board in a printable format
		int rowCounter = 0;								// Creates rowCounter, used to select when to print the spacer
		int[][] tempArray = new int[9][9];				// Creates tempArray with 9y * 9x indices, used to store the values of each of the boards tiles for easier access
		for (int i = 0; i < 9; i++) {					// Starts a for loop to cycle trough "boardArray" correctly to fetch out the values of each tile
			boardString += " " + (i+1) + " |";			// Adds a bit of padding, an index number and the left outer wall of the board to the printable board for each y index of the board
			int counter = 0;							// Creates counter, used to select when to add a new wall to the printable wall
			for (int j = 0; j < 9; j++) {				// Starts the second part of the for loop that cycles trough "boardArray" and "tempArray"
				SudokuTile nTile = boardArray[i][j];	// Creates nTile, contains a copy of the Class/Object of the tile we are operating with right now in the for loop
				tempArray[i][j] = nTile.getValue();		// Adds the value of the current tile to the corresponding index of "tempArray"
				if (makeBoardDebug) {					// Debug output:
					System.out.print(i);					// Prints the current "i" value of the for loop
					System.out.println(j);					// Prints the current "j" value of the for loop
					System.out.print(nTile.getValue());		// Prints the value of the current tile
					System.out.print(nTile.getType());		// Prints the type (static true/false) if the current tile
				}
				if (tempArray[i][j] == 0){				// If the value of the tile is 0, it's represented on the board as a point (.)
					boardString += " . ";				// Adds the point representing the tiles value = 0 to "boardString"
				}
				else if (nTile.getValidity() == false) {							// If a tiles validity is false (it crashes with another number), it will be printed with an asterisk to symbolize that
					boardString += " " + Integer.toString(tempArray[i][j]) + "*";	// Adds the value of the tile (0 <= value <= 9) and the asterisk to "boardString"
				}
				else if (nTile.getType()) {											// If the tile is static (cannot be changed), it will be printed with parentheses to symbolize that
					boardString += "(" + Integer.toString(tempArray[i][j]) + ")";	// Adds the value of the Tile (0 <= value <= 9) and the parentheses to "boardString
				}
				counter += 1;							// Adds one to the counter, so that walls are printed correctly
				
				if (counter%3 == 0) {					// For every third time the for loop iterates:
                    boardString += "|";						// Add a wall "baordString" | WHY NOT USE FOR LOOP "j+1" FOR THIS?
                }
			}
		rowCounter += 1;
	    if (rowCounter % 3 == 0)
	    	boardString += line + spacer;
	        boardString += line;
		}
		boardString += "   " + "+ a  b  c + d  e  f + g  h  i +";
		return boardString;
	}
	
	public void takeInput(String coordinates, String number) {
		prevBoardArray = boardArray;
		String[] tempCoord = coordinates.split("");
		System.out.println("taking coordinates y: " + tempCoord[0] + "and x: " + tempCoord[1]);
		int x = translate.stringInt(tempCoord[1]);
		int y = (Integer.parseInt(tempCoord[0]))-1;
		int num = Integer.parseInt(number);
		if (inputDebug) {
			System.out.println("Sending coords y: " + y + ", x: " + x + " (" + (y+1) + (translate.intString(x)) + ") " + " and number: " + num + "into placeNumber if valid");
		}
		if (validInput(y) && validInput(x) && validNum(num)) {
			if (boardArray[y][x].getType() == false) {
				placeNumber(y,x,num);
			}
			else {
				System.out.println("You can't change a static tile!");
			}
		}
		else {
			System.out.println("Invalid arguements!");
		}
	}
	
	private boolean validNum(int num) {
		if (num < 10 && num > -1) {
			System.out.print("TRUE!");
			return true;
		}
		else {
			return false;
		}
	}
	private boolean validInput(int num) {
		if (num < 9 && num > -1) {
			System.out.print("TRUE!");
			return true;
		}
		else {
			return false;
		}
	}
	
	private void checkRow(int i, int j, int num) {
		int locNum = boardArray[i][j].getValue();
		if (locNum == num && boardArray[i][j].getType() == false) {
			if (firstCheckDebug) {
				System.out.println("Setting validity of " + locNum + " to FALSE, because: " + num);
			}
			boardArray[i][j].setValidity(false);
		}
	}
	private void checkGrid(int eY, int eX, int num) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (firstCheckDebug) {
					System.out.println("Sending y: " + (i+eY) + " x: " + (j+eX) + " (" + (i+eY+1) + (translate.intString(j+eX)) + ") " + " and num: " + num + " into checkRow");
				}
				checkRow(i+eY,j+eX,num);
			}
		}
	}
	private int findGrid(int i) {
		if (i < 3) {
			return 0;
		}
		else if (i < 6) {
			return 3;
		}
		else if (i < 8) {
			return 6;
		}
		else {
			System.out.println("findGrid can't decide because of number: " + i);
			return 0;
		}
	}
	private void placeNumber(int y, int x, int num) {
		int prevNum = boardArray[y][x].getValue();
		//boardArray[y][x].setValue(num);
		//checkNumber(y,x,prevNum);
		for (int i = 0; i < 9; i++) {
			if (firstCheckDebug) {
				System.out.println("Sending y: " + i + " x: " + x + " (" + (i+1) + (translate.intString(x)) + ") " + " and num: " + num + " into checkRow");
			}
			checkRow(i,x,num);
			if (firstCheckDebug) {
				System.out.println("Sending y: " + y + " x: " + i + " (" + (y+1) + (translate.intString(i)) + ") " + " and num: " + num + " into checkRow");
			}
			checkRow(y,i,num);
		}
		checkGrid(findGrid(y),findGrid(x),num);
		boardArray[y][x].setValue(num);
		checkNumber(y,x,prevNum);
	}
	public void getStatus(String location) {
		String[] tempCoord = location.split("");
		System.out.println("taking coordinates y: " + tempCoord[0] + " and x: " + tempCoord[1]);
		int x = translate.stringInt(tempCoord[1]);
		int y = (Integer.parseInt(tempCoord[0]))-1;
		System.out.println("Sending coords y: " + y + ", x: " + x + " (" + (y+1) + (translate.intString(x)) + ") " + " into status() if valid");
		if (validNum(y) && validNum(x)) {
			boardArray[y][x].status();
		}
	}
	public void undoMove() {
		boardArray = prevBoardArray;
	}
	private void checkNumber(int y, int x, int num) {
		for (int i = 0; i < 9; i++) {
			if (checkChainDebug) {
				System.out.println("Sending y: " + i + " x: " + x + " (" + (i+1) + (translate.intString(x)) + ") " + " and num: " + num + " into checkChainRow");
			}
			checkChainRow(i,x,num);
			if (checkChainDebug) {
				System.out.println("Sending y: " + y + " x: " + i + " (" + (y+1) + (translate.intString(i)) + ") " + " and num: " + num + " into checkChainRow");
			}
			checkChainRow(y,i,num);
		}
		checkChainGrid(findGrid(y),findGrid(x),num);
	}
	
	private void checkChainGrid(int eY, int eX, int num) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (checkChainDebug) {
					System.out.println("Sending y: " + (i+eY) + " x: " + (j+eX) + " (" + (i+eY+1) + (translate.intString(j+eX)) + ") " +" and num: " + num + " into checkChainRow");
				}
				checkChainRow(i+eY,j+eX,num);
			}
		}
	}
	private void checkChainRow(int i, int j, int num) {
		if (boardArray[i][j].getValue() == num && boardArray[i][j].getType() == false) {
			if (getError(i,j,num)) {
				if (checkChainDebug) {
					System.out.println("Validity of " + (i+1) + (translate.intString(j)) + " num: " + num + " set to FALSE");
				}
				boardArray[i][j].setValidity(false);
			}
			else {
				if (checkChainDebug) {
					System.out.println("Validity of " + (i+1) + (translate.intString(j)) + " num: " + num + " set to TRUE");
				}
				boardArray[i][j].setValidity(true);
			}
		}
	}
	private boolean getError(int x, int y, int num) {
		for (int i = 0; i < 9; i++) {
			if (checkErrorDebug) {
				System.out.println("Sending y: " + i + " x: " + x + " (" + (i+1) + (translate.intString(x)) + ") " + " and num: " + num + " into checkChainRow");
			}
			if (getErrorRow(i,x,num)) {
				return true;
			}
			if (checkErrorDebug) {
				System.out.println("Sending y: " + y + " x: " + i + " (" + (y+1) + (translate.intString(i)) + ") " + " and num: " + num + " into checkChainRow");
			}
			if (getErrorRow(y,i,num)) {
				return true;
			}
		}
		return getErrorGrid(findGrid(y),findGrid(x),num);
	}
	private boolean getErrorGrid(int eY, int eX, int num) {
		boolean tempBool = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (checkErrorDebug) {
					System.out.println("Sending y: " + (i+eY) + " x: " + (j+eX) + " (" + (i+eY+1) + (translate.intString(j+eY)) + ") " + " and num: " + num + " into getErrorRow");
				}
				tempBool = getErrorRow(i+eY,j+eX,num);
				if (tempBool) {
					if (checkErrorDebug) {
						System.out.println("getErrorGrid returns TURE(error) for i: " + i + " j: " + j + " (" + (i+1) + (translate.intString(j)) + ") " + " num: " + num);
					}
					break;
				}
			}
		}
		if (tempBool) {
			return true;
		}
		else {
			if (checkErrorDebug) {
				System.out.println("getErrorGrid returns FALSE(noError) for extraY: " + eY + " extraX: " + eX + " num: " + num);
			}
			return false;
		}
	}
	private boolean getErrorRow(int i, int j, int num) {
		if (boardArray[i][j].getValue() == num && boardArray[i][j].getType() == false) {
			if (checkErrorDebug) {
				System.out.println("getErrorRow returns TRUE(error) for i: " + i + " j: " + j + " (" + (i+1) + (translate.intString(j)) + ") " + " num: " + num);
			}
			return true;
		}
		else {
			if (checkErrorDebug) {
				System.out.println("getErrorRow returns FALSE(noError) for i: " + i + " j: " + j + " (" + (i+1) + (translate.intString(j)) + ") " + " num: " + num);
			}
			return false;
		}
	}
	
	public void setDebug(String debugValues) {
		String[] debugSplit = debugValues.split("");
		makeBoardDebug = translate.stringBoolean(debugSplit[0]);
		inputDebug = translate.stringBoolean(debugSplit[1]);
		firstCheckDebug = translate.stringBoolean(debugSplit[2]);
		checkErrorDebug = translate.stringBoolean(debugSplit[3]);
		checkChainDebug = translate.stringBoolean(debugSplit[4]);
	}
}
