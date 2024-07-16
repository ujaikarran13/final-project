package com.techelevator;

public class BankAccount implements Accountable{

    private final String accountIdentifier;
    private int balance;
    private String accountNumber;
    private String accountHolderName;

    public BankAccount(String accountIdentifier, int startingBalance) {
        this.accountIdentifier = accountIdentifier;
        balance = startingBalance;
    }

    public String getAccountIdentifier() {
        return accountIdentifier;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param amountToDeposit the amount to deposit
     */
    public void deposit(int amountToDeposit) {
        if (amountToDeposit > 0) {
            balance = balance + amountToDeposit;
        }
    }

    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param amountToWithdraw the amount to withdraw from the account
     * @return true if the withdrawal was successful, false otherwise
     */
    public boolean withdraw(int amountToWithdraw) {
        if (amountToWithdraw > 0 && balance - amountToWithdraw >= 0) {
            balance = balance - amountToWithdraw;
            return true;
        }
        return false;
    }
    public int transferFunds(BankAccount destinationAccount, int amountToTransfer){
        if (amountToTransfer <= 0){
            System.out.println("Transfer amount must be greater than 0");
        return this.balance;
        }
        if (this.balance < amountToTransfer){
            System.out.println("Account has Insufficent funds");
        return this.balance;
        }
        this.withdraw(amountToTransfer);
        destinationAccount.deposit(amountToTransfer);
        return this.balance;
    }

}
