import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

interface Bank {
    void deposit(double amount);
    void withdraw(double amount);
    void calculateInterest();
    String getAccountInfo();
}

class Account implements Bank, Serializable {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private double interestRate;

    public Account(String accountNumber, String accountHolder, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.interestRate = interestRate;
    }

  
    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " deposited. New balance: " + balance);
    }

 
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(amount + " withdrawn. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

  
    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest calculated. New balance: " + balance);
    }

 
    public String getAccountInfo() {
        return "Account Number: " + accountNumber +
                "\nAccount Holder: " + accountHolder +
                "\nBalance: " + balance +
                "\nInterest Rate: " + interestRate + "%";
    }
}

public class BankManagementSystem {
    private static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        loadAccountsFromFile(); // Load accounts from file on startup

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create Account\n2. Deposit\n3. Withdraw\n4. Calculate Interest\n5. Show Account Info\n6. Save Accounts to File\n7. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    calculateInterest(scanner);
                    break;
                case 5:
                    showAccountInfo(scanner);
                    break;
                case 6:
                    saveAccountsToFile();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accountNumber = scanner.next();
        System.out.println("Enter Account Holder Name: ");
        String accountHolder = scanner.next();
        System.out.println("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        System.out.println("Enter Interest Rate: ");
        double interestRate = scanner.nextDouble();

        Account account = new Account(accountNumber, accountHolder, balance, interestRate);
        accounts.add(account);
    }

    private static void deposit(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accNumDeposit = scanner.next();
        System.out.println("Enter Amount to Deposit: ");
        double amountDeposit = scanner.nextDouble();

        for (Account acc : accounts) {
            if (acc.getAccountInfo().contains(accNumDeposit)) {
                acc.deposit(amountDeposit);
                break;
            }
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accNumWithdraw = scanner.next();
        System.out.println("Enter Amount to Withdraw: ");
        double amountWithdraw = scanner.nextDouble();

        for (Account acc : accounts) {
            if (acc.getAccountInfo().contains(accNumWithdraw)) {
                acc.withdraw(amountWithdraw);
                break;
            }
        }
    }

    private static void calculateInterest(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accNumInterest = scanner.next();

        for (Account acc : accounts) {
            if (acc.getAccountInfo().contains(accNumInterest)) {
                acc.calculateInterest();
                break;
            }
        }
    }

    private static void showAccountInfo(Scanner scanner) {
        System.out.println("Enter Account Number: ");
        String accNumShowInfo = scanner.next();

        for (Account acc : accounts) {
            if (acc.getAccountInfo().contains(accNumShowInfo)) {
                System.out.println(acc.getAccountInfo());
                break;
            }
        }
    }

    private static void saveAccountsToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("accounts.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(accounts);
            out.close();
            fileOut.close();
            System.out.println("Accounts saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadAccountsFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("accounts.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            accounts = (ArrayList<Account>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Accounts loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

