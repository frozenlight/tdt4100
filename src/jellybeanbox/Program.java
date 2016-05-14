package jellybeanbox;

public class Program {
	
	private static void testBox() {
		JellyBeanBox box1 = new JellyBeanBox("Blueberry");
		
		JellyBeanBox box2 = new JellyBeanBox("Cotton Candy");
		
		System.out.println(box1.toString());
		System.out.println(box2.toString());
		
		box1.addBeans(15);
		box1.removeBeans(5);
		box2.addBeans(100);
		box1.addBeans(10);
		box2.removeBeans(20);
		
		System.out.println(box1.toString());
		System.out.println(box2.toString());
	}
	
	private static void testCustomer() {
		JellyBeanCustomer nils = new JellyBeanCustomer(1,"nils");
		JellyBeanCustomer ole = new JellyBeanCustomer(1,"ole");
		
		System.out.println(nils);
		System.out.println(ole);
		
		nils.orderBeans(2000);
		nils.orderBeans(600);
		ole.orderBeans(50);
		ole.orderBeans(50);
		ole.orderBeans(60);
		
		System.out.println(ole.getTotalBeansOrdered());
		System.out.println(ole.getAverageBeansOrdered());
		System.out.println(nils.getTotalBeansOrdered());
		System.out.println(nils.getAverageBeansOrdered());
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testBox();
		testCustomer();
	}

}
