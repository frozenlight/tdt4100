package sudoku;

public class SudokuBoard {
	SudokuTile tile;									// Creates the SudokuTile named tile
	SudokuTranslate translate;							// Creates the SudokuTranslate named translate
	private SudokuTile[][] boardArray;					// Creates boardArray as a field, makes it universally accessible for this Class/Object
	private int undoNum;								// Creates prevBoardArray, this is used to contain a copy of the board to be able to reset to it's previous state
	private String undoCoord;
	private boolean makeBoardDebug = false;				// Debug value for makeBoard, initially set to FALSE
	private boolean inputDebug = false;					// Debug value for Constructor SudokuBoard, initially set to FALSE
	private boolean firstCheckDebug = false;			// Debug value for check, initially set to FALSE
	private boolean checkChainDebug = false;			// Debug value for checkChain, initially set to FALSE
	private boolean checkErrorDebug = false;			// Debug value for checkError, initially set to FALSE
	private boolean endGameDebug = false;				// Debug value for endGame, initially set to FALSE
	
	public SudokuTile[][] getBoardArray() {				// Public method to return "boardArray" to any Class/Object
		return boardArray;								// Returns "boardArray"
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
				else {
					boardString += " " + Integer.toString(tempArray[i][j]) + " ";
				}
				counter += 1;							// Adds one to the counter, so that walls are printed correctly
				
				if (counter%3 == 0) {					// For every third time the for loop iterates:
                    boardString += "|";						// Add a wall "baordString" | WHY NOT USE FOR LOOP "j+1" FOR THIS?
                }
			}
		rowCounter += 1;								// Adds one to the rowCounter  for every iteration the the first for loop
	    if (rowCounter % 3 == 0)						// For every third iteration of the first for loop:
	    	boardString += line + spacer;				// Adds a new line and a spacer to "boardString"
	    	boardString += line;						// Adds a new line to "boardString"
		}
		boardString += "   " + "+ a  b  c + d  e  f + g  h  i +";		// Adds the bottom x-indices to "boardString"
		return boardString;												// Returns "boardString", the string containing the whole printable board
	}
	
	public void takeInput(String coordinates, String number) {
		String[] tempCoord = coordinates.split("");
		if (inputDebug) {
			System.out.println("taking coordinates y: " + tempCoord[0] + " and x: " + tempCoord[1]);
		}
		int x = translate.stringInt(tempCoord[1]);
		int y = (Integer.parseInt(tempCoord[0]))-1;
		int num = Integer.parseInt(number);
		undoNum = boardArray[y][x].getValue();
		undoCoord = coordinates;
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
	
	private void placeNumber(int y, int x, int num) {
		int prevNum = boardArray[y][x].getValue();
		boolean tempBool1 = false;
		boolean tempBool2 = false;
		boolean tempBool3 = false;
		boardArray[y][x].setValue(num);
		boardArray[y][x].setValidity(true);
		if (checkRowBuffer(x,num,y)) {
			boardArray[y][x].setValidity(false);
			tempBool1 = true;
		}
		if (checkColBuffer(y,num,x)) {
			boardArray[y][x].setValidity(false);
			tempBool2 = true;
		}
		if (checkGrid(y,x,findGrid(y),findGrid(x),num)) {
			boardArray[y][x].setValidity(false);
			tempBool3 = true;
		}
		if (tempBool1 == false && tempBool2 == false && tempBool3 == false) {
			boardArray[y][x].setValidity(true);
		}
		if (prevNum != 0 && prevNum != boardArray[y][x].getValue()) {
			checkNumber(y,x,prevNum);
		}
	}
	
	private boolean validNum(int num) {
		if (num < 10 && num > -1) {
			if (inputDebug) {
				System.out.print("TRUE!");
			}
			return true;
		}
		else {
			if (inputDebug) {
				System.out.print("FALSE!");
			}
			return false;
		}
	}
	private boolean validInput(int num) {
		if (num < 9 && num > -1) {
			if (inputDebug) {
				System.out.print("TRUE!");
			}
			return true;
		}
		else {
			if (inputDebug) {
				System.out.print("FALSE!");
			}
			return false;
		}
	}
	
	private boolean checkRow(int i, int j, int num) {
		int locNum = boardArray[i][j].getValue();
		if (firstCheckDebug && locNum == num) {
			System.out.println("locNum: " + locNum + " num: " + num);
		}
		if (locNum == num) {
			if (firstCheckDebug) {
				System.out.println("Setting validity of " + locNum + " to FALSE, because: " + num);
			}
			boardArray[i][j].setValidity(false);
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkRowBuffer(int j, int num, int oV) {
		boolean sendBool = false;
		boolean tempBool1 = false;
		for (int i = 0; i < 9; i++) {
			if (firstCheckDebug) {
				System.out.println("Sending y: " + i + " x: " + oV + " (" + (i+1) + (translate.intString(oV)) + ") " + " and num: " + num + " into checkRow");
			}
			if (i != oV) {
				tempBool1 = checkRow(i,j,num);
			}
			if (tempBool1) {
				sendBool = true;
			}
			
		}
		if (sendBool) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkColBuffer(int j, int num, int oV) {
		boolean sendBool = false;
		boolean tempBool2 = false;
		for (int i = 0; i < 9; i++) {
			if (firstCheckDebug) {
				System.out.println("Sending y: " + oV + " x: " + i + " (" + (oV+1) + (translate.intString(i)) + ") " + " and num: " + num + " into checkRow");
			}
			if (i != oV) {
				tempBool2 = checkRow(j,i,num);
			}
			if (tempBool2) {
				sendBool = true;
			}
			
		}
		if (sendBool) {
			return true;
		}
		else {
			return false;
		}
	}
	private boolean checkGrid(int y, int x, int eY, int eX, int num) {
		boolean sendBool = false;
		boolean tempBool = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (firstCheckDebug) {
					System.out.println("Sending y: " + (i+eY) + " x: " + (j+eX) + " (" + (i+eY+1) + (translate.intString(j+eX)) + ") " + " and num: " + num + " into checkRow");
				}
				if (firstCheckDebug) {
					System.out.print("i+eY: " + (i+eY) + " y: " + y + ", i+eX: " + (j+eX) + " x: " + x + ", ");
				}
				if (i+eY != y && j+eX != x) {
					if (firstCheckDebug) {
						System.out.println(" PASSED");
					}
					tempBool = checkRow(i+eY,j+eX,num);
					if (tempBool) {
						sendBool = true;
					}
				}
				else {
					if (firstCheckDebug) {
						System.out.println(" BLOCKED");
					}
				}
			}
		}
		if (sendBool) {
			return true;
		}
		else {
			return false;
		}
	}
	private int findGrid(int i) {
		if (i < 3) {
			return 0;
		}
		else if (i < 6) {
			return 3;
		}
		else if (i < 9) {
			return 6;
		}
		else {
			System.out.println("findGrid can't decide because of number: " + i);
			return 0;
		}
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
		takeInput(undoCoord,Integer.toString(undoNum));
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
		checkChainDebug = translate.stringBoolean(debugSplit[3]);
		checkErrorDebug = translate.stringBoolean(debugSplit[4]);
		endGameDebug = translate.stringBoolean(debugSplit[5]);
	}
	
	public void endGame() {
		if (endGameCheck()){
			System.out.println(" __     __                               _ ");
			System.out.println(" \\ \\   / /                              | |");
			System.out.println("  \\ \\_/ /__  _   _  __      _____  _ __ | |");
			System.out.println("   \\   / _ \\| | | | \\ \\ /\\ / / _ \\| '_ \\| |");
			System.out.println("    | | (_) | |_| |  \\ V  V / (_) | | | |_|");
			System.out.println("    |_|\\___/ \\__,_|   \\_/\\_/ \\___/|_| |_(_)");
			System.out.println("                                           ");
			System.out.println("                                           ");
		}
		else {
			System.out.println("Continue, the game is not finished or has the wrong numbers!");
		}
	}
	
	private boolean endGameCheck() {
		boolean end = true;
		for (int i = 0; i < 9; i++) {
			if (endGameRow(i) == false || endGameCol(i) == false || endGameValidity(i) == false) {
				end = false;
			}
		}
		return end;
	}
	private boolean endGameRow(int x) {
		int boardSum = 0;
		for (int y = 0; y < 9; y++) {
			boardSum += boardArray[y][x].getValue();
		}
		if (endGameDebug) {
			System.out.print("RowBoardSum: " + boardSum + "/45");
		}
		if (boardSum == 45) {
			if (endGameDebug) {
				System.out.println("Col" + x + " PASSED");
			}
			return true;
		}
		else {
			if (endGameDebug) {
				System.out.println("Col" + x + " FAILED");
			}
			return false;
		}
	}
	private boolean endGameCol(int y) {
		int boardSum = 0;
		for (int x = 0; x < 9; x++) {
			boardSum += boardArray[y][x].getValue();
		}
		if (endGameDebug) {
			System.out.print("ColBoardSum: " + boardSum + "/45");
		}
		if (boardSum == 45) {
			if (endGameDebug) {
				System.out.println("Col" + y + " PASSED");
			}
			return true;
		}
		else {
			if (endGameDebug) {
				System.out.println("Col" + y + " FAILED");
			}
			return false;
		}
	}
	private boolean endGameValidity(int y) {
		int validSum = 0;
		for (int i = 0; i < 9; i++) {
			if (boardArray[y][i].getValidity() && boardArray[y][i].getValue() != 0) {
				validSum += 1;
			}
		}
		if (endGameDebug) {
			System.out.print("validSum: " + validSum + "/9");
		}
		if (validSum == 9) {
			if (endGameDebug) {
				System.out.println("Col" + y + " PASSED");
			}
			return true;
		}
		else {
			if (endGameDebug) {
				System.out.println("Col" + y + " FAILED");
			}
			return false;
		}
		
	}
}
