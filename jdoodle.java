import java.util.*;
class Bank Account
{
    private String accountOwner;
    private String accountNumber;
    private double balance;
    public Bank Account (String accountOwner,String accountNumber)
    {
        this.accountOwner = accountOwner;
        this.accountNumber = accountHolder;
        this.balance = balance;
        }
}
public void deposit(double amount)
{
    balance+=amount;
    System.out.println("Deposite"+ amount+"New balance")
}
public void withdraw(double amount)
{
    if(balance>=amount)
    {
        balance -=amount;
        System,out,println("Withdraw"+amount+"New balance")
    }
    else
    {
        System.out.println("Insufficent balance")
    }
}