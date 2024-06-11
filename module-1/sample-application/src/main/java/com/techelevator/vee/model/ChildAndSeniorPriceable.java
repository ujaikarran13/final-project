package com.techelevator.vee.model;

import java.math.BigDecimal;

/**
 * The ChildAndSeniorPriceable interface is implemented by a class that can get and set costs
 * for children and seniors.
 */

public interface ChildAndSeniorPriceable {

    BigDecimal getCostPerChild();
    void setCostPerChild(BigDecimal costPerChild);

    BigDecimal getCostPerSenior();
    void setCostPerSenior(BigDecimal costPerSenior);

}
