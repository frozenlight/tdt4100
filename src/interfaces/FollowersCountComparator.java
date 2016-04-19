package interfaces;


import java.util.Comparator;

public class FollowersCountComparator implements Comparator<TwitterAccount>{
	//Sorterer twitteraccountene basert p� antall f�lgere.
	//Accounten med flest f�lgere vil havne f�rst.
	public int compare(TwitterAccount a1, TwitterAccount a2) {
		return a2.getFollowerCount()-a1.getFollowerCount();
	}

}
