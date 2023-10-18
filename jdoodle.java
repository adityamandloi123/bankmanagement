interface Bank {
    void deposit(double amount);
    void withdraw(double amount);
}

class Account implements Bank {
    private double balance;

    public Account(double initialBalance) {
        balance = initialBalance;
    }


    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Account myAccount = new Account(1000.0);

        myAccount.deposit(500.0);
        myAccount.withdraw(200.0);
        myAccount.withdraw(1500.0);

        System.out.println("Current Balance: $" + myAccount.getBalance());
    }
}
