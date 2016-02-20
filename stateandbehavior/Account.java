package stateandbehavior;

public class Account {

	//Fields
	public double balance;
	public double interestRate;
	
	//Methods
	public void deposit(double addBalance) {
		if (addBalance >= 0) {
			balance += addBalance;
		}
	}
	public void addInterest() {
		balance *= (1+interestRate);
	}
	//toString method
	public String toString() {
		return "The balance for the account is " + balance + " and the interest rate is " + interestRate;
	}
	//Main method
	public static void main(String[] args) {
		Account account = new Account();
        account.deposit(100);
        account.addInterest();
	}
}
