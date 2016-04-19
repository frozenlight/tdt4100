package sudokuinterface;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Stack;
//import java.io.PrintWriter;
//import java.io.File;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.util.Collection;
import java.util.*;
//import java.io.*;

public class SudokuBoard {
	
	SudokuTile tile;				
	SudokuTranslate translate;
	SudokuSaveInterface save = new SudokuSave();
	private ArrayList<ArrayList<SudokuTile>> boardArray = new ArrayList<ArrayList<SudokuTile>>();
	//private int undoNum;								
	//private String undoCoord;
	private boolean makeBoardDebug = false;				
	private boolean inputDebug = false;					
	private boolean firstCheckDebug = false;			
	private boolean checkChainDebug = false;			
	private boolean checkErrorDebug = false;			
	private boolean endGameDebug = false;
	//private ArrayList<String> zeroNine = new ArrayList<String>();
	//private Stack<ArrayList<ArrayList<SudokuTile>>> arrStored;
	//private Stack<ArrayList<ArrayList<SudokuTile>>> arrDiStored;
	private Stack<String> prevTurns2 = new Stack<String>();
	private Stack<String> redoTurns2 = new Stack<String>();
	private Stack<ArrayList<String>> prevTurns = new Stack<ArrayList<String>>();
	private Stack<ArrayList<String>> redoTurns = new Stack<ArrayList<String>>();
	//private Stack<SudokuTile> diStored;
	//private String storedInText;
	//private PrintWriter writer;
	
	
	public ArrayList<ArrayList<SudokuTile>> getBoardArray() {				
		return boardArray;								
	}
	
	public SudokuBoard(String inputBoard) {				
		translate = new SudokuTranslate();
		/*for (int i = 0; i < 10; i++) {
			zeroNine.add(Integer.toString(i));
		}*/
		int counter = 0;								
		System.out.println(inputBoard.length());		
		if (inputBoard.length() == 81) {				
			String[] buildArray = new String [81];		
			buildArray = inputBoard.split("");			
			for (int i = 0; i < 9; i++) {
				boardArray.add(new ArrayList<SudokuTile>());
				for (int j = 0; j < 9; j++) {
					boardArray.get(i).add(new SudokuTile(buildArray[counter]));		
					counter++;						
					if (inputDebug) {					
						System.out.println("getValue er " + boardArray.get(i).get(j).getValue());
					}
				}
			}
		}
		else {											
			throw new IllegalArgumentException ("the board contains too few or too many feilds");
		}
	}
	
	public String makeBoard() {							
		String line = "\n";								
		String spacer = "   " + "+---------+---------+---------+";				
		String boardString = line + spacer + line;		
		int rowCounter = 0;								
		int[][] tempArray = new int[9][9];				
		for (int i = 0; i < 9; i++) {					
			boardString += " " + (i+1) + " |";			
			int counter = 0;							
			for (int j = 0; j < 9; j++) {				
				SudokuTile nTile = boardArray.get(i).get(j);	
				tempArray[i][j] = nTile.getValue();		
				if (makeBoardDebug) {					
					System.out.print(i);					
					System.out.println(j);					
					System.out.print(nTile.getValue());		
					System.out.print(nTile.getType());		
				}
				if (tempArray[i][j] == 0){				
					boardString += " . ";				
				}
				else if (nTile.getValidity() == false) {							
					boardString += " " + Integer.toString(tempArray[i][j]) + "*";	
				}
				else if (nTile.getType()) {											
					boardString += "(" + Integer.toString(tempArray[i][j]) + ")";	
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
		ArrayList<String> prevBoard = new ArrayList<String>();
		prevBoard.add(coordinates);
		prevBoard.add(Integer.toString(boardArray.get(x).get(y).getValue()));
		prevTurns.add(prevBoard);
		prevTurns2.add(save.storeBoard(boardArray));
		if (inputDebug) {
			System.out.println("Sending coords y: " + y + ", x: " + x + " (" + (y+1) + (translate.intString(x)) + ") " + " and number: " + num + "into placeNumber if valid");
		}
		if (validInput(y) && validInput(x) && validNum(num)) {
			if (boardArray.get(y).get(x).getType() == false) {
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
		int prevNum = boardArray.get(y).get(x).getValue();
		boolean tempBool1 = false;
		boolean tempBool2 = false;
		boolean tempBool3 = false;
		boardArray.get(y).get(x).setValue(num);
		boardArray.get(y).get(x).setValidity(true);
		if (checkRowBuffer(x,num,y)) {
			boardArray.get(y).get(x).setValidity(false);
			tempBool1 = true;
		}
		if (checkColBuffer(y,num,x)) {
			boardArray.get(y).get(x).setValidity(false);
			tempBool2 = true;
		}
		if (checkGrid(y,x,findGrid(y),findGrid(x),num)) {
			boardArray.get(y).get(x).setValidity(false);
			tempBool3 = true;
		}
		if (tempBool1 == false && tempBool2 == false && tempBool3 == false) {
			boardArray.get(y).get(x).setValidity(true);
		}
		if (prevNum != 0 && prevNum != boardArray.get(y).get(x).getValue()) {
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
		int locNum = boardArray.get(i).get(j).getValue();
		if (firstCheckDebug && locNum == num) {
			System.out.println("locNum: " + locNum + " num: " + num);
		}
		if (locNum == num) {
			if (firstCheckDebug) {
				System.out.println("Setting validity of " + locNum + " to FALSE, because: " + num);
			}
			boardArray.get(i).get(j).setValidity(false);
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
			boardArray.get(y).get(x).status();
		}
	}
	/*public void undoMove() {
		takeInput(undoCoord,Integer.toString(undoNum));
	}*/
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
		if (boardArray.get(i).get(j).getValue() == num && boardArray.get(i).get(j).getType() == false) {
			if (getError(i,j,num)) {
				if (checkChainDebug) {
					System.out.println("Validity of " + (i+1) + (translate.intString(j)) + " num: " + num + " set to FALSE");
				}
				boardArray.get(i).get(j).setValidity(false);
			}
			else {
				if (checkChainDebug) {
					System.out.println("Validity of " + (i+1) + (translate.intString(j)) + " num: " + num + " set to TRUE");
				}
				boardArray.get(i).get(j).setValidity(true);
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
		if (boardArray.get(i).get(j).getValue() == num && boardArray.get(i).get(j).getType() == false) {
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
			boardSum += boardArray.get(y).get(x).getValue();
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
			boardSum += boardArray.get(y).get(x).getValue();
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
			if (boardArray.get(y).get(i).getValidity() && boardArray.get(y).get(i).getValue() != 0) {
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
	/*private String storeBoard(ArrayList<ArrayList<SudokuTile>> theBoard) {
		String textFile = "";
		for (ArrayList<SudokuTile> subArray : theBoard) {
			for (SudokuTile tile : subArray) {
				String value1 = Integer.toString(tile.getValue());
				String value2 = Integer.toString(translate.boolInt(tile.getValidity()));
				String value3 = Integer.toString(translate.boolInt(tile.getType()));
				
				textFile += value1 + value2 + value3 + " ";
			}
		}
		return textFile;
	}*/
	/*private ArrayList<ArrayList<SudokuTile>> redoBoard(String textInput) {
		ArrayList<ArrayList<SudokuTile>> board = new ArrayList<ArrayList<SudokuTile>>();
		ArrayList<String> tempText = new ArrayList<String>(Arrays.asList(textInput.split(" ")));
		int count = 0;
		for (int i = 0; i < 9; i++) {
			ArrayList<SudokuTile> subBoard = new ArrayList<SudokuTile>();
			for (int j = 0; j < 9; j++) {
				
				ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(tempText.get(count).split("")));
				subBoard.add(new SudokuTile(tempList));
				count++;
			}
			board.add(subBoard);
		}
		return board;
	}*/
	public void undoMove() {
		if (!prevTurns.empty()) {
			ArrayList<String> turn = prevTurns.pop();
			takeInput(turn.get(0),turn.get(1));
			redoTurns.add(turn);
		}
		else {
			System.out.println("UNDO attempted, but no previous state of the board is stored");
		}
	}
	public void undoMoveV2() {
		if (!prevTurns2.empty()) {
			String turn = prevTurns2.pop();
			boardArray = new ArrayList<ArrayList<SudokuTile>>(save.redoBoard(turn));
			redoTurns2.add(turn);
		}
		else {
			System.out.println("UNDO attempted, but no previous state of the board is stored");
		}
	}
	public void redoMove() {
		if (!redoTurns.empty()) {
			ArrayList<String> turn = redoTurns.pop();
			takeInput(turn.get(0),turn.get(1));
			prevTurns.add(turn);
		}
		else {
			System.out.println("REDO attempted, but no future state of the board is stored");
		}
	}
	public void redoMoveV2() {
		if (!redoTurns2.empty()) {
			String turn = redoTurns2.pop();
			boardArray = new ArrayList<ArrayList<SudokuTile>>(save.redoBoard(turn));
			prevTurns2.add(turn);
		}
		else {
			System.out.println("REDO attempted, but no future state of the board is stored");
		}
	}
	public void printToFile(){
		save.printToFile(boardArray);
	}
	/*public void printToFile() {
		PrintWriter writer;
		try {
			String home = System.getProperty("user.home");
			File file = new File(home,"SudokuSave.txt");
			writer = new PrintWriter(file);
			writer.print(storeBoard(boardArray));
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
		}
	}*/
	public void loadFromFile(){
		boardArray = new ArrayList<ArrayList<SudokuTile>>(save.loadFromFile());
	}
	/*public void loadFromFile(){
		Scanner reader;
		try {
			String home = System.getProperty("user.home");
			File file = new File(home,"SudokuSave.txt");
			reader = new Scanner(file);
			String line = "";
			while (reader.hasNext()) {
				line = reader.nextLine();
			}
			reader.close();
			if (!line.equals("")) {
				boardArray = new ArrayList<ArrayList<SudokuTile>>(redoBoard(line));
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
		}
	}*/
	
	/*private boolean verifySave(ArrayList<String> list) {
		System.out.println(list.size());
		if (list.size() == 81) {
			System.out.println("PASS");
			boolean nofail = false;
			for (String string : list) {
				ArrayList<String> innerList = new ArrayList<String>(Arrays.asList(string.split("")));
				System.out.println(innerList.size() + " " + innerList);
				if (innerList.size() == 3) {
					System.out.print("1");
					nofail = true;
				}
				else {
					nofail = false;
				}
				if (zeroNine.contains(innerList.get(0))) {
					System.out.print("2");
					nofail = true;
				}
				else {
					nofail = false;
				}
				if (zeroNine.subList(0,1).contains(innerList.get(1))) {
					System.out.print("3");
					nofail = true;
				}
				else {
					nofail = false;
				}
				if (zeroNine.subList(0,1).contains(innerList.get(2))) {
					System.out.print("4");
					nofail = true;
				}
				else {
					nofail = false;
				}
				return nofail;
			}
		}
		return false;
	}*/
}
