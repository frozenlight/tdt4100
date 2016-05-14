package jellybeanbox;

public class JellyBeanBox {
	private final String flavour;
	private int beanCount;
	
	public JellyBeanBox(String flavour, int number) {
		this.flavour = flavour;
		addBeans(number);
	}
	
	public JellyBeanBox(String flavour) {
		this.flavour = flavour;
	}
	
	public void addBeans(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Can't add less than 0 beans!");
		}
		this.beanCount += number;
	}
	
	public void removeBeans(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("Can't remove less than 0 beans!");
		}
		else if (number > this.beanCount) {
			throw new IllegalArgumentException("Can't remove move beans than this box conatins!");
		}
		this.beanCount -= number;
	}
	
	public String getFlavour() {
		return this.flavour;
	}
	
	public int getBeanCount() {
		return this.beanCount;
	}
	
	public String toString() {
		return (this.flavour + " " + this.beanCount);
	}
}
