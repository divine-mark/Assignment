// SavingsAccount.java
// A savings account: cannot go below a minimum balance, and earns interest.
public class SavingsAccount extends Account {

    private double minimumBalance;   // fixed minimum the balance may not drop below
    private double interestRate;     // e.g. 0.02 = 2%

    public SavingsAccount(String accountNumber, double balance,
                          double minimumBalance, double interestRate) {
        super(accountNumber, balance);   // call the Account constructor first
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be greater than zero.");
            return;
        }
        if (balance - amount < minimumBalance) {
            System.out.println("Withdrawal of " + money(amount) + " REJECTED on " + accountNumber
                    + " (savings): balance would fall below the minimum of "
                    + money(minimumBalance) + ". Current balance: " + money(balance));
            return;
        }
        balance = balance - amount;
        System.out.println("Withdrew " + money(amount) + " from " + accountNumber
                + " (savings) | New balance: " + money(balance));
    }

    @Override
    public void endOfMonth() {
        double interest = balance * interestRate;
        balance = balance + interest;
        System.out.println("End of month on " + accountNumber + " (savings): interest of "
                + money(interest) + " added | New balance: " + money(balance));
    }
}