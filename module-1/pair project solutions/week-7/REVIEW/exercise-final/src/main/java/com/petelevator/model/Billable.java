package com.petelevator.model;

import java.math.BigDecimal;
import java.util.Map;

public interface Billable {

    BigDecimal getBalanceDue(Map<String, BigDecimal> servicesRendered);

}
