package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;

public class Exercise06 {

    /*
    Given a subtotal and state name, calculate the total with sales tax for an order.
    Use the provided SALES_TAX_RATES Map to get the sales tax rate for the state.
    Sales tax is calculated by multiplying the subtotal by the sales tax rate. Add that value to the subtotal and return it.
    If a state is not in the list, assume no sales tax and return the subtotal.

    NOTE: you don't need to round the value before returning it. You can assume the calling method will handle extra decimal places.
     */

    private final Map<String, BigDecimal> SALES_TAX_RATES = Map.of(
            "Arizona", new BigDecimal("0.056"),
            "California", new BigDecimal("0.0725"),
            "Colorado", new BigDecimal("0.029"),
            "Hawaii", new BigDecimal("0.04"),
            "Idaho", new BigDecimal("0.06"),
            "Nevada", new BigDecimal("0.0685"),
            "Utah", new BigDecimal("0.0485"),
            "Washington", new BigDecimal("0.065"),
            "Wyoming", new BigDecimal("0.04")
    );

    public BigDecimal calculateTotalWithTax(BigDecimal subtotal, String state) {
        if (SALES_TAX_RATES.containsKey(state)) {
            BigDecimal salesTax = subtotal.multiply(SALES_TAX_RATES.get(state));
            return subtotal.add(salesTax);
        } else {
            return subtotal;
        }
    }
}
