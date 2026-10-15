Author
Divine Mark Makhomeya
H250333E Object Oriented Programming
Monday 12 October 2026


# Object-Oriented Programming in Java — Bank Assignment: Inheritance and Polymorphism

    This repository contains my solution for the **Bank Assignment** of the OOP in Java course.
    The assignment demonstrates inheritance, abstraction, method overriding, and runtime
    polymorphism using a simple bank account system.

    ## Assignment Objectives
    - Create an abstract class with both concrete and abstract methods.
    - Use inheritance to create two subclasses that share core behaviour but differ in specifics.
    - Override methods so each subclass provides its own version of `withdraw()` and `endOfMonth()`.
    - Use a single `List<Account>` to store a mix of account types.
    - Call methods through the `Account` reference only (no casting) to prove polymorphism works.



## Tasks

### Task 1: `Account` Class (Abstract)
    - Abstract class `Account` with protected fields `accountNumber` and `balance`.
    - Constructor to initialise both fields.
    - Concrete `deposit(double amount)` method that rejects non-positive amounts.
    - Concrete `getBalance()` method that returns the current balance.
    - Abstract `withdraw(double amount)` method (implemented by subclasses).
    - Abstract `endOfMonth()` hook method (implemented by subclasses).



### Task 2: `SavingsAccount` Class
    - Extends `Account`.
    - Has a fixed `minimumBalance` and an `interestRate`.
    - Overrides `withdraw()` to reject any withdrawal that would take the balance below the minimum,
      printing a clear rejection message instead.
    - Overrides `endOfMonth()` to apply the interest rate to the current balance.



### Task 3: `CurrentAccount` Class
    - Extends `Account`.
    - Has an `overdraftLimit` and a fixed `monthlyFee`.
    - Overrides `withdraw()` to allow the balance to go negative, but not past the overdraft limit.
    - Overrides `endOfMonth()` to deduct the monthly maintenance fee (instead of paying interest).



### Task 4: Polymorphic Demo in `BankDemo`
    - Creates a `List<Account>` containing a mix of `SavingsAccount` and `CurrentAccount` objects.
    - Loops through the list calling `withdraw()` and `endOfMonth()` through the `Account` reference only.
    - Does not cast to the subclass anywhere in the loop.
    - Proves that the correct overridden version runs for each object.



### Task 5: Edge Case Demonstrations
    - Shows a `SavingsAccount` withdrawal being **rejected** because it would fall below the minimum balance.
    - Shows a `CurrentAccount` going into **overdraft** while still within its limit.
    - Also shows a rejected overdraft withdrawal (past the limit) and a rejected negative deposit.

    **Expected Output:**
    ========== INITIAL STATE ==========
    SAV-001 | balance: 1000.00
    CUR-001 | balance: 200.00
    SAV-002 | balance: 600.00

    ========== POLYMORPHIC LOOP: withdraw 300.00 from every account ==========
    Withdrew 300.00 from SAV-001 (savings) | New balance: 700.00
    Withdrew 300.00 from CUR-001 (current) | New balance: -100.00
    Withdrawal of 300.00 REJECTED on SAV-002 (savings): balance would fall below the minimum of 500.00. Current balance: 600.00

    ========== POLYMORPHIC LOOP: endOfMonth on every account ==========
    End of month on SAV-001 (savings): interest of 14.00 added | New balance: 714.00
    End of month on CUR-001 (current): maintenance fee of 15.00 deducted | New balance: -115.00
    End of month on SAV-002 (savings): interest of 12.00 added | New balance: 612.00

    ========== EXTRA EDGE CASES ==========
    -- Savings withdrawal of 400.00 (would break the 500.00 minimum) --
    Withdrawal of 400.00 REJECTED on SAV-001 (savings): balance would fall below the minimum of 500.00. Current balance: 714.00

    -- Current withdrawal of 100.00 (still inside the 300.00 overdraft limit) --
    Withdrew 100.00 from CUR-001 (current) | New balance: -215.00

    -- Current withdrawal of 500.00 (would break the 300.00 overdraft limit) --
    Withdrawal of 500.00 REJECTED on CUR-001 (current): would exceed the overdraft limit of 300.00. Current balance: -215.00

    -- Non-positive deposit of -50.00 --
    Deposit rejected: amount must be greater than zero.

    ========== FINAL BALANCES ==========
    SAV-001 | balance: 714.00
    CUR-001 | balance: -215.00
    SAV-002 | balance: 612.00



## OOP Concepts Demonstrated
    - **Abstraction** — `Account` defines the shared shape but leaves `withdraw()` and `endOfMonth()` to subclasses.
    - **Inheritance** — `SavingsAccount` and `CurrentAccount` both extend `Account`.
    - **Polymorphism** — one `List<Account>` holds both types; the correct overridden method runs at runtime.
    - **Method Overriding** — each subclass provides its own `withdraw()` and `endOfMonth()`.
    - **Encapsulation** — fields are `protected` so subclasses can use them while still being hidden from other classes.



## Files
- `Account.java` — abstract parent class (Task 1)
- `SavingsAccount.java` — savings account subclass (Task 2)
- `CurrentAccount.java` — current account subclass (Task 3)
- `BankDemo.java` — polymorphic demo and edge cases (Tasks 4 & 5)
