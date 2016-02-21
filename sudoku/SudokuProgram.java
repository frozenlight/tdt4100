package sudoku;

import java.util.Scanner;

public class SudokuProgram {
	
	public static void main(String[] args)	{
			SudokuBoard board;
			Scanner scanner = new Scanner(System.in);
			String line;
			System.out.print("input game start numbers");
			board = new SudokuBoard(scanner.nextLine());
			
			while (true)
			{
				System.out.println();
				System.out.println("1. printBoard\n2. placeNumber\n3. getStatus\n4. undoMove\n5. setDebug\n6. quit");
				line = scanner.nextLine();
				
		
				if (line.equals("1"))
				{
					try
					{
						System.out.print(board.makeBoard());
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
				}
				else if (line.equals("2"))
				{
					System.out.println("Where do you want to place the number (ex: 3d or 1c):");
					String loc = scanner.nextLine();
					System.out.println("Number you want to place (between 1 and 9):");
					String number = scanner.nextLine();
					try
					{
						board.takeInput(loc, number);
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if (line.equals("3"))
				{
					try {
						System.out.println("Which SudokuTile do you want to see the status of (ex: 3d or 1c):");
						board.getStatus(scanner.nextLine());
					}
					catch (Exception e) {
						System.out.println(e);
					}
				}
				else if (line.equals("4"))
				{
					board.undoMove();
				}
				else if (line.equals("5"))
				{
					System.out.println("makeBoardDebug | inputDebug | firstCheckDebug | checkErrorDebug | checkChainDebug");
					System.out.print("set a 1 for TRUE and a 0 for FALSE for all debug types, no spaces: ");
					board.setDebug(scanner.nextLine());
				}
				else if (line.equals("6")){
					break; 
				}
				else
				{
					System.out.println("Invalid input");
				}	
			}
			scanner.close();	
		}
	}
