package com.petelevator.model.hr;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private int departmentId;
    private String departmentName;
    private Manager departmentHead;
    private List<Employee> departmentEmployees = new ArrayList<>();

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public void transferEmployeeIn(Employee employee) {
        employee.getDepartment().getDepartmentEmployees().remove(employee);
        employee.setDepartment(this);
        this.departmentEmployees.add(employee);
    }

    // getters and setters

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Manager getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Manager manager) {
        // TODO Step 5b: Add a custom exception class (UnqualifiedDepartmentHeadException) and throw
        //  here if the candidate doesn't have the correct title
        if (manager.getTitle().startsWith("Manager") || manager.getTitle().startsWith("Director")) {
            this.departmentHead = manager;
        }
    }

    public List<Employee> getDepartmentEmployees() {
        return departmentEmployees;
    }

    public void setDepartmentEmployees(List<Employee> departmentEmployees) {
        this.departmentEmployees = departmentEmployees;
    }

    @Override
    public String toString() {
        return "Department " + departmentId + " " + departmentName;
    }

}
