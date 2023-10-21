import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void applyInterest(double rate) {
        balance += (balance * rate / 100);
    }
}

public class BankManagementSystem {
    private ArrayList<BankAccount> accounts;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BankManagementSystem bank = new BankManagementSystem();

        // Create accounts and add them to the bank
        BankAccount account1 = new BankAccount(1001, "John Doe", 1000);
        BankAccount account2 = new BankAccount(1002, "Jane Doe", 2000);

        bank.addAccount(account1);
        bank.addAccount(account2);

        // Deposit and withdraw from accounts
        account1.deposit(500);
        account2.withdraw(300);

        // Apply interest
        account1.applyInterest(5); // 5% interest rate
        account2.applyInterest(5); // 5% interest rate

        // Print account information
        System.out.println("Account Number: " + account1.getAccountNumber());
        System.out.println("Account Holder: " + account1.getAccountHolder());
        System.out.println("Balance: $" + account1.getBalance());

        System.out.println("Account Number: " + account2.getAccountNumber());
        System.out.println("Account Holder: " + account2.getAccountHolder());
        System.out.println("Balance: $" + account2.getBalance());
    }
}
