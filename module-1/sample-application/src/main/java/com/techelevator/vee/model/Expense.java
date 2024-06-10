package com.techelevator.vee.model;

import java.math.BigDecimal;

/**
 * The Expense interface is implemented by a class that can provide a total cost and a
 * string that explains how that cost was calculated.
 */

public interface Expense {

    BigDecimal getTotalCost();

    String getCostDetails();

}
