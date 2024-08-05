package com.petelevator.crm;

import com.petelevator.Billable;
import com.petelevator.Person;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer extends Person implements Billable {

    private String phoneNumber;
    private List<Pet> pets = new ArrayList<>();

    public Customer(String firstName, String lastName) {
        this(firstName, lastName, "");
    }

    public Customer(String firstName, String lastName, String phoneNumber) {
        super(firstName, lastName);
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getBalanceDue(Map<String, BigDecimal> servicesRendered) {
        BigDecimal owes = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> sr : servicesRendered.entrySet()) {
            owes = owes.add(sr.getValue());
        }

        return owes;
    }

    // getters and setters

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
