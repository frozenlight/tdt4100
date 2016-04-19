package interfaces;

import java.util.Scanner;
import java.util.Hashtable;
import java.beans.Introspector;
import java.util.List;

public class TwitterProgram {

	public static void main(String[] args) {
		
		UserNameComparator byNames = new UserNameComparator();
		FollowersCountComparator byFollowers = new FollowersCountComparator();
		TweetsCountComparator byTweets = new TweetsCountComparator();
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
			
			System.out.println();
			System.out.println("\n1. log in | 2. tweet | 3. retweet | 4. getFollowerCount | 5. follow | 6. checkFollower | 7. getFollowers");
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
					getUser.getFollowerCount();
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
					System.out.println("Which user do you want to check the followers of? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					System.out.println("Which user do you want to check for? (username)");
					TwitterAccount otherUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					getUser.isFollowing(otherUser);
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else if (line.equals("7")) {
				try {
					System.out.println("Which user do you want to get the followers of? (username)");
					TwitterAccount getUser = accounts.get(Introspector.decapitalize(scanner.nextLine()));
					System.out.println("\n1. byNames | 2. byFollowers | 3. byTweets");
					String newLine = scanner.nextLine();
					List<TwitterAccount> sorted; 
					if (newLine.equals("1")) {
						sorted = getUser.getFollowers(byNames);
					}
					else if (newLine.equals("2")) {
						sorted = getUser.getFollowers(byFollowers);
					}
					else if (newLine.equals("3")) {
						sorted = getUser.getFollowers(byTweets);
					}
					else {
						System.out.println("derp...");
						sorted = null;
					}
					String printString = "String ++: " + sorted.size() + sorted.get(1).getUserName();
					for (int i = 0; i < sorted.size(); i++) {
						printString += "\n" + sorted.get(i).getUserName();
					}
					System.out.println(printString);
				}
				catch (Exception e) {
					System.out.println(e);
				}
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
