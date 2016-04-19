package encapsulation;

import java.util.Scanner;

public class AccountProgram
{
	
	public static void main(String[] args)	{
			Scanner scanner = new Scanner(System.in);
			String line;
			System.out.print("This is an Account");
			Account account; 
			account = new Account();
			
			while (true)
			{
				System.out.println();
				System.out.println("1. getBalance\n2. getInterestRate\n3. setInterestRate\n4 deposit\n5. withdraw\n6. quit");
				line = scanner.nextLine();
				
		
				if (line.equals("1"))	{	
					System.out.println(account.getBalance());
				}
				else if (line.equals("2"))	{
					System.out.println(account.getInterestRate());
				}
				else if (line.equals("3"))	{
					System.out.println("Set the interestrate of this Account:");
					double number = Double.valueOf(scanner.nextLine());
					try	{
						account.setInterestRate(number);
					}
					catch(Exception e)	{
						System.out.println(e);
					}
				}
				else if (line.equals("4"))	{
					System.out.println("How much would you like to deposit?");
					double add = Double.valueOf(scanner.nextLine());
					try	{
						account.deposit(add);
					}
					catch(Exception e)	{
						System.out.println(e);
					}
				}
				else if (line.equals("5"))	{
					System.out.println("How much would you like to withdraw?");
					double remove = Double.valueOf(scanner.nextLine());
					try	{
						account.withdraw(remove);
					}
					catch(Exception e)	{
						System.out.println(e);
					}
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
