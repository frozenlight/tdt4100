package sudokuinterface;

import java.util.ArrayList;

public interface SudokuSaveInterface {
	
	public void printToFile(ArrayList<ArrayList<SudokuTile>> board);
	
	public ArrayList<ArrayList<SudokuTile>> loadFromFile();
	
	public ArrayList<ArrayList<SudokuTile>> redoBoard(String textInput);
	
	public String storeBoard(ArrayList<ArrayList<SudokuTile>> theBoard);
	
}
