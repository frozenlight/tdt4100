package sudokuinterface;

import java.util.Scanner;

public class SudokuProgram {
	
	public static void main(String[] args)	{
			SudokuBoard board;
			Scanner scanner = new Scanner(System.in);
			String line;
			String game;
			System.out.println("Do you want to use the preset game (yes/empty/'else')");
			String answer = scanner.nextLine();
			if (answer.equals("yes")) {
				game = ".....2..38.273.45....6..87.9.8..5367..6...1..4513..9.8.84..3....79.512.62..8.....";
			}
			else if (answer.equals("empty")) {
				game = ".................................................................................";
			}
			else {
				System.out.print("input game start numbers");
				game = scanner.nextLine();
			}
			System.out.print("input game start numbers");
			board = new SudokuBoard(game);
			
			while (true)
			{
				System.out.println();
				System.out.println("\n1. printBoard | 2. placeNumber | 3. getStatus   | 4. undoMove     | 5. setDebug \n6. endGame    | 7. redoMove    | 8. printToFile | 9. loadFromFile | 10. stopGame");
				line = scanner.nextLine();
				
		
				if (line.equals("1")) {
					try {
						System.out.print(board.makeBoard());
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
				else if (line.equals("2")) {
					System.out.println("Where do you want to place the number (ex: 3d or 1c):");
					String loc = scanner.nextLine();
					System.out.println("Number you want to place (between 1 and 9):");
					String number = scanner.nextLine();
					try {
						board.takeInput(loc, number);
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
				else if (line.equals("3")) {
					try {
						System.out.println("Which SudokuTile do you want to see the status of (ex: 3d or 1c):");
						board.getStatus(scanner.nextLine());
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
				else if (line.equals("4")) {
					//board.undoMoveV2();
					board.undoMove();
				}
				else if (line.equals("5")) {
					System.out.println("makeBoardDebug | inputDebug | firstCheckDebug | checkEChainDebug | checkErrorDebug | endGameDebug");
					System.out.print("set a 1 for TRUE and a 0 for FALSE for all debug types, no spaces: ");
					board.setDebug(scanner.nextLine());
				}
				else if (line.equals("6")) {
					board.endGame(); 
				}
				else if (line.equals("7")) {
					//board.redoMoveV2();
					board.redoMove();
				}
				else if (line.equals("8")) {
					board.printToFile();
				}
				else if (line.equals("9")) {
					System.out.println("Stuff...");
					board.loadFromFile();
				}
				else if (line.equals("10")) {
					break;
				}
				else {
					System.out.println("Invalid input");
				}
			}
			scanner.close();	
		}
	}
