package encapsulation;

import java.util.Scanner;

public class NimProgram
{
	
	public static void main(String[] args)	{
			Scanner scanner = new Scanner(System.in);
			String line;
			System.out.print("Choose pile size:");
			Nim nim; 
			nim = new Nim(Integer.valueOf(scanner.nextLine()));
			
			while (true)
			{
				System.out.println();
				System.out.println("1. isValidMove\n2. removePieces\n3. isGameOver\n4. getPile\n5. toString\n6. quit");
				line = scanner.nextLine();
				
		
				if (line.equals("1"))
				{
					System.out.println("Number of bricks you wish to draw:");	
					int number = Integer.valueOf(scanner.nextLine());
					System.out.println("Pile you wish to draw from:");
					int targetPile = Integer.valueOf(scanner.nextLine());
					try
					{
						System.out.println(nim.isValidMove(number, targetPile));
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
				}
				else if (line.equals("2"))
				{
					System.out.println("Number of bricks you wish to draw:");
					int number = Integer.valueOf(scanner.nextLine());
					System.out.println("Pile you wish to draw from:");
					int targetPile = Integer.valueOf(scanner.nextLine());
					try
					{
						nim.removePieces(number, targetPile);
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if (line.equals("3"))
				{
					System.out.println(nim.isGameOver());
				}
				else if (line.equals("4"))
				{
					System.out.println("Pile you wish to inspect:");
					int targetPile = Integer.valueOf(scanner.nextLine());
					System.out.println(nim.getPile(targetPile));
				}
				else if (line.equals("5"))
				{
					System.out.println(nim.toString());
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