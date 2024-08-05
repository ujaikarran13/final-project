package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

public class Exercise03 {

    /*
    Given a starting balance of an account, a list of deposits, and a list of withdrawals, return a boolean indicating if
    the account is overdrawn after all deposits and withdrawals have been made. In other words, return true if the balance is less than zero.
    Both lists contain positive numbers: deposits are added to the balance, withdrawals are subtracted from the balance.

    Examples:
    isAccountOverdrawn( 23.37, {15.0, 9.21}, {3.7, 4.62} )  ->  false
    isAccountOverdrawn( 109.29, {38.59, 242.7}, {393.83, 5.52} )  ->  true
    isAccountOverdrawn( 74.83, {107.1, 93.3}, {275.23} )  ->  false
    */
    public boolean isAccountOverdrawn(BigDecimal startingBalance, List<BigDecimal> deposits, List<BigDecimal> withdrawals) {
        BigDecimal currentBalance = startingBalance;
        for (BigDecimal deposit : deposits) {
            currentBalance = currentBalance.add(deposit);
        }
        for (BigDecimal withdrawal : withdrawals) {
            currentBalance = currentBalance.subtract(withdrawal);
        }
        return currentBalance.compareTo(BigDecimal.ZERO) < 0;
    }
}
