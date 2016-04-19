package objectstructures;

public class Tweet {
	
	private TwitterAccount owner;
	private String text;
	private Tweet originalTweet = null;
	private int retweetCount = 0;
	
	public Tweet(TwitterAccount owner, String text) {
		this.owner = owner;
		this.text = text;
		System.out.print(getInfo());
	}
	
	public Tweet(TwitterAccount owner, Tweet inputTweet) {
		Tweet tempTweet = inputTweet.getOriginalTweet();
		if (inputTweet.getOwner() == owner || (tempTweet != null && tempTweet.getOwner() == owner)) {
			throw new IllegalArgumentException("A tweet cannot be a retweet from the same author");
		}
		this.owner = owner;
		this.originalTweet = (tempTweet != null ? tempTweet : inputTweet);
		this.originalTweet.retweetCount++;
		this.text = this.originalTweet.getText();
		System.out.print(getInfo());
	}
	
	public String getInfo() {
		return "[" + owner.getUserName() + ": " + getText() + (originalTweet == null ? "" : " - retweet of " + originalTweet.getOwner().getUserName()) + "]";
	}	
	
	public String getText() {
		return text;
	}
	
	public TwitterAccount getOwner() {
		return owner;
	}
	
	public Tweet getOriginalTweet() {
		return originalTweet;
	}
	
	public int getRetweetCount() {
		return retweetCount;
	}
}