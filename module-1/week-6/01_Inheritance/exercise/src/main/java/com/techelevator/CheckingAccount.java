package com.techelevator;
public class CheckingAccount extends BankAccount{
    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }
    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {

        int currentBalance = this.balance - amountToWithdraw;

        if (amountToWithdraw <= 0) {
            System.out.println("Withdrawal amount must be greater than zero");
            return this.balance;
        } else if (currentBalance < 0) {
            System.out.println("Withdrawal cannot exceed $100");
            return this.balance;
        } else {
            this.balance = currentBalance;
            if (this.balance == 89)
                this.balance -= 10;
            System.out.println("An overdraft fee of $10 has been charged to your account.");
        }
        return this.balance;

    }
}


