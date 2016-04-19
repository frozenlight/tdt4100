package objectstructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.Hashtable;

public class TwitterAccount {
	
	private final String userName;
	
	private Collection<TwitterAccount> following = new ArrayList<TwitterAccount>();
	private Collection<TwitterAccount> followers = new ArrayList<TwitterAccount>();

	private List<Tweet> tweetList = new ArrayList<Tweet>();
	
	public TwitterAccount(String username) {
		this.userName = username;
	}
		
	public String toString() {
		return "[Username: " + getUserName() + " - " + getTweetCount() + " tweets]";
	}

	public String getUserName() {
		return userName;
	}
	
	public void follow(TwitterAccount account) {
		if (this == account) {
			System.out.println("You can't follow yourself!");
		}
		if (! following.contains(account)) {
			following.add(account);
			account.followers.add(this);
		}
	}
	
	public void unfollow(TwitterAccount account) {
		if (following.contains(account)) {
			following.remove(account);
			account.followers.remove(this);
		}
	}

	public boolean isFollowing(TwitterAccount account) {
		return following.contains(account);
	}
	
	public boolean isFollowedBy(TwitterAccount account) {
		return followers.contains(account);
	}
	
	public void tweet(String text) {
		tweetList.add(new Tweet(this, text));
	}
	
	public void retweet(Tweet tweet) {
		tweetList.add(new Tweet(this, tweet));
	}
	
	public Tweet getTweet(int i) {
		return tweetList.get(tweetList.size() - i);
	}
	
	public int getTweetCount() {
		return tweetList.size();
	}

	public int getRetweetCount() {
		int tweetCount = 0;
		for (Tweet tweet : tweetList) {
			tweetCount += tweet.getRetweetCount();
		}
		return tweetCount;
	}
	public int getFollowerCount(){
		return followers.size();
	}
}