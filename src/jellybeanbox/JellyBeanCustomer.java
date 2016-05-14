package jellybeanbox;

//import java.util.*;

public class JellyBeanCustomer {
	private int id;
	private String name;
	//private List<Integer> orders = new ArrayList<Integer>();
	public int numberOfOrders;
	private int totalBeansOrdered;
	
	public JellyBeanCustomer(int id, String name) {
		this.id = id;
		this.name = name;
		this.numberOfOrders = 0;
	}
	
	public void orderBeans(int number) {
		if (number <= 1) {
			throw new IllegalArgumentException("Can't order less than one jellybean");
		}
		this.numberOfOrders++;
	}
	public int getTotalBeansOrdered() {
		return totalBeansOrdered;
	}
	
	public double getAverageBeansOrdered() {
		if (this.numberOfOrders == 0) {
			return 0;
		}
		else {
			return getTotalBeansOrdered() / (double) this.numberOfOrders;
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
