package objectstructures;

import java.util.Scanner;
import java.util.Hashtable;
import java.beans.Introspector;

public class TwitterProgram {

	public static void main(String[] args) {
		
		TwitterAccount user;
		Scanner scanner = new Scanner(System.in);
		String line;
		System.out.println("Welcome to Kwitter");
		Hashtable<String,TwitterAccount> accounts = new Hashtable<String,TwitterAccount>();
		
		System.out.println("Create Kwitters first user! Write in a username:");
		String firstUserName = Introspector.decapitalize(scanner.nextLine());
		accounts.put(firstUserName,new TwitterAccount(firstUserName));
		user = accounts.get(firstUserName);
		
		while (true){
			
			System.out.println("Logged in as: " + user.getUserName());
			System.out.println("\n1. log in | 2. tweet | 3. retweet | 4. getFollowerCount | 5. follow | 6. unfollow | 7. checkFollowers");
			line = scanner.nextLine();
			
			if (line.equals("1")) {
				try {
					System.out.println("Write in your username: ");
					String tempUserName = Introspector.decapitalize(scanner.nextLine());
					if (accounts.contains(tempUserName) == false) {
						accounts.put(tempUserName,new TwitterAccount(tempUserName));
						user = accounts.get(tempUserName);
					}
					user = accounts.get(tempUserName);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("2")) {
				System.out.println("Write your tweet: ");
				String tweetText = scanner.nextLine();
				try {
					user.tweet(tweetText);
				}
				catch(Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("3")) {
				try {
					System.out.println("Which user do you want to retweet from? (username)");
					TwitterAccount retweetUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					System.out.println("Which of their tweets to you want to retweet? (int)");
					user.retweet(retweetUser.getTweet(Integer.parseInt(scanner.nextLine())));
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("4")) {
				try {
					System.out.println("Which user do you want to get the FollowerCount from? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					System.out.println(getUser.getFollowerCount());
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("5")) {
				try {
					System.out.println("Which user do you want to follow? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					user.follow(getUser);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("6")) {
				try {
					System.out.println("Which user do you want to unfollow? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					user.unfollow(getUser);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("7")) {
				try {
					System.out.println("Which user do you want to check the followers of? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					System.out.println("Which user do you want to check for? (username)");
					TwitterAccount otherUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					if (otherUser.isFollowing(getUser)) {
						System.out.println("True");
					}
					else {
						System.out.println("False");
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("7")) {
			}
			else if (line.equals("8")) {
				break;
			}
			else {
				System.out.println("Invalid input");
			}

		}
		scanner.close();	

	}

}
