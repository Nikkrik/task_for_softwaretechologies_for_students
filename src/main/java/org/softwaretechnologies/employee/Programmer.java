package org.softwaretechnologies.employee;

public class Programmer extends Employee {

    protected Programmer(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return baseSalary;
    }

    @Override
    public String getName() {
        return super.getName();
    }
}