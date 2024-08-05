package com.petelevator;

import java.math.BigDecimal;
import java.util.Map;

public interface Billable {

    BigDecimal getBalanceDue(Map<String, BigDecimal> servicesRendered);

}
