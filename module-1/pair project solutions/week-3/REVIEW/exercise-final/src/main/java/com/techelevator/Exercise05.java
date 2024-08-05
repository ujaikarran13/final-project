package com.techelevator;

import java.util.Map;

public class Exercise05 {

    /*
    In the given Map, if a key named 'total' exists, return the value associated with that key.
    If the key doesn't exist, return the sum of all the values in the Map.

    Examples:
    getTotal({"total": 29, "item1": 12, "item2": 17}) -> 29
    getTotal({"Alice": 37, "Bob": 51, "Charlie": 22}) -> 110
    getTotal({"pizza": 15, "wings": 10, "bread": 7, "total": 32}) -> 32
     */
    public int getTotal(Map<String, Integer> costs) {
        int costTotal = 0;
        if (costs.containsKey("total")) {
            costTotal = costs.get("total");
        } else {
            for (Map.Entry<String, Integer> cost : costs.entrySet()) {
                costTotal += cost.getValue();
            }
        }

        return costTotal;
    }
}
