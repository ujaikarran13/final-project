package com.techelevator;
public class SavingsAccount extends BankAccount {
    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }
    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }
    @Override
    public int withdraw (int amountToWithdraw){
        int currentBalance = this.balance - amountToWithdraw;

        if (amountToWithdraw <= 0) {
            System.out.println("Withdrawal must be greater than 0");
            return this.balance;
        } else if (currentBalance < 150){
            currentBalance -= 2;
            if (currentBalance <0) {
                System.out.println("Insufficient funds, withdrawal denied");
                return this.balance;
            }else {
                this.balance = currentBalance;
                System.out.println("A $2 Service charge is applied");
                return this.balance;
            }
            } else {
                this.balance = currentBalance;
                return this.balance;
            }
        }

    }




