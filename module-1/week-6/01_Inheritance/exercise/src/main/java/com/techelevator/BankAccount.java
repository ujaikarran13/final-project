package com.techelevator;
public class BankAccount {

    protected String accountHolderName;
    protected String accountNumber;
    protected int balance;
    public BankAccount(String accountHolderName, String accountNumber){
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }
    public BankAccount(String accountHolderName, String accountNumber, int balance){
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = balance;

    }

    public String getAccountHolderName() {
        return this.accountHolderName;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    public int getBalance(){
        return this.balance;
    }

    public int deposit(int amountToDeposit) {
        if (amountToDeposit > 0){
            this.balance += amountToDeposit;
        }
            return this.balance;
    }

    public int withdraw(int amountToWithdraw){
        if (amountToWithdraw > 0 && amountToWithdraw <= this.balance){
            this.balance -= amountToWithdraw;
        }
        return this.balance;

    }
}
