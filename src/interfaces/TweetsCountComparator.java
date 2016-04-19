package interfaces;

import java.util.Comparator;

public class TweetsCountComparator implements Comparator<TwitterAccount>{
	
	//Sorterer twitteraccountene basert p� antall tweets. 
	//Accounten med flest tweets vil havne f�rst i listen.
	public int compare(TwitterAccount a1, TwitterAccount a2) {
		return a2.getTweetCount()-a1.getTweetCount();
	}
}
