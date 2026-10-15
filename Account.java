// Account.java
// The parent (abstract) class. It holds everything that BOTH account types share.
public abstract class Account {

    // protected = the child classes can use these directly
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // CONCRETE method: same behaviour for every account type
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: amount must be greater than zero.");
            return;
        }
        balance = balance + amount;
        System.out.println("Deposited " + money(amount) + " into " + accountNumber
                + " | New balance: " + money(balance));
    }

    // CONCRETE method: same for every account type
    public double getBalance() {
        return balance;
    }

    // Extra small getter so BankDemo can print the account number nicely
    public String getAccountNumber() {
        return accountNumber;
    }

    // ABSTRACT methods: every child class MUST provide its own version
    public abstract void withdraw(double amount);

    public abstract void endOfMonth();

    // Small helper so money prints with 2 decimals instead of 714.0000000001
    protected String money(double amount) {
        return String.format("%.2f", amount);
    }
}