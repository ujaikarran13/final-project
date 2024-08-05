// TODO 3a: Create a custom exception class
package com.petelevator.model.hr;

import com.petelevator.model.Person;

public class UnqualifiedDepartmentHeadException extends Exception {
    private Person candidate;

    public UnqualifiedDepartmentHeadException(Person candidate) {
        super("The candidate must have a title that begins with 'Manager' or 'Director'");
        this.candidate = candidate;
    }

    public UnqualifiedDepartmentHeadException(Person candidate, String message) {
        super(message);
        this.candidate = candidate;
    }
}
