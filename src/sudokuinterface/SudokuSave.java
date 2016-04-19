package sudokuinterface;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SudokuSave implements SudokuSaveInterface{
	private SudokuTranslate translate;
	
	public SudokuSave() {
		translate = new SudokuTranslate();
	}
	public void printToFile(ArrayList<ArrayList<SudokuTile>> board) {
		PrintWriter writer;
		try {
			String home = System.getProperty("user.home");
			File file = new File(home,"SudokuSave.txt");
			writer = new PrintWriter(file);
			writer.print(storeBoard(board));
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
		}
	}
	public ArrayList<ArrayList<SudokuTile>> loadFromFile() {
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
				return new ArrayList<ArrayList<SudokuTile>>(redoBoard(line));
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
		}
		return new ArrayList<ArrayList<SudokuTile>>();
	}
	public ArrayList<ArrayList<SudokuTile>> redoBoard(String textInput) {
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
	}
	public String storeBoard(ArrayList<ArrayList<SudokuTile>> theBoard) {
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
	}
}
