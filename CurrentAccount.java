// CurrentAccount.java
// A current (cheque) account: may go negative, but only up to an overdraft limit.
public class CurrentAccount extends Account {

    private double overdraftLimit;   // how far below 0 the balance may go
    private double monthlyFee;       // fixed fee charged at month end

    public CurrentAccount(String accountNumber, double balance,
                          double overdraftLimit, double monthlyFee) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
        this.monthlyFee = monthlyFee;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: amount must be greater than zero.");
            return;
        }
        // Going below -overdraftLimit is not allowed
        if (balance - amount < -overdraftLimit) {
            System.out.println("Withdrawal of " + money(amount) + " REJECTED on " + accountNumber
                    + " (current): would exceed the overdraft limit of "
                    + money(overdraftLimit) + ". Current balance: " + money(balance));
            return;
        }
        balance = balance - amount;
        System.out.println("Withdrew " + money(amount) + " from " + accountNumber
                + " (current) | New balance: " + money(balance));
    }

    @Override
    public void endOfMonth() {
        balance = balance - monthlyFee;
        System.out.println("End of month on " + accountNumber + " (current): maintenance fee of "
                + money(monthlyFee) + " deducted | New balance: " + money(balance));
    }
}