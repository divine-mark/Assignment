import java.util.ArrayList;
import java.util.List;

public class BankDemo {

    public static void main(String[] args) {

        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 500.00, 0.02));
        accounts.add(new CurrentAccount("CUR-001", 200.00, 300.00, 15.00));
        accounts.add(new SavingsAccount("SAV-002", 600.00, 500.00, 0.02));

        printBalances(accounts, "INITIAL STATE");

        System.out.println("========== POLYMORPHIC LOOP: withdraw 300.00 from every account ==========");
        for (Account acc : accounts) {
            acc.withdraw(300.00);   // the correct overridden version runs automatically
        }
        System.out.println();

        System.out.println("========== POLYMORPHIC LOOP: endOfMonth on every account ==========");
        for (Account acc : accounts) {
            acc.endOfMonth();
        }
        System.out.println();


        System.out.println("========== EXTRA EDGE CASES ==========");
        Account savings = accounts.get(0);   // SAV-001
        Account current = accounts.get(1);   // CUR-001

        System.out.println("-- Savings withdrawal of 400.00 (would break the 500.00 minimum) --");
        savings.withdraw(400.00);
        System.out.println();

        System.out.println("-- Current withdrawal of 100.00 (still inside the 300.00 overdraft limit) --");
        current.withdraw(100.00);
        System.out.println();

        System.out.println("-- Current withdrawal of 500.00 (would break the 300.00 overdraft limit) --");
        current.withdraw(500.00);
        System.out.println();

        System.out.println("-- Non-positive deposit of -50.00 --");
        savings.deposit(-50.00);
        System.out.println();

        printBalances(accounts, "FINAL BALANCES");
    }

    // Prints every account's number and balance
    private static void printBalances(List<Account> accounts, String title) {
        System.out.println("========== " + title + " ==========");
        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " | balance: " + money(acc.getBalance()));
        }
        System.out.println();
    }

    // Formats a double as money with 2 decimals
    private static String money(double amount) {
        return String.format("%.2f", amount);
    }
}