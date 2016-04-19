package encapsulation;

public class Account {

    private double balance;
    private double interestRate;
    
    public Account(double amount, double interestRate) {
        if (amount > 0 && interestRate> 0) {
            this.balance = amount;
            this.interestRate = interestRate;
        }
        else {
            throw new IllegalArgumentException("Amount/Interest rate cannot be less than 0!");
        }
    }

    public double getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate>0) {
            this.interestRate = interestRate;
        }
        else {
            throw new IllegalArgumentException("Cannot set interest rate less than 0!");
        }
    }

    void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        else {
           throw  new IllegalArgumentException("Cannot deposit less than 0.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= this.balance) {
                this.balance -= amount;
            }
            else {
                throw new IllegalStateException(String.format("Cannot withdraw, too low on funds.\nBalance: %s, withdraw: %s", this.balance, amount));
            }
        }
        else {
            throw new IllegalArgumentException("Cannot withdraw less than 0.");
        }
    }
    public Account() {
    	balance = 100;
    	interestRate = 5;
    }
}