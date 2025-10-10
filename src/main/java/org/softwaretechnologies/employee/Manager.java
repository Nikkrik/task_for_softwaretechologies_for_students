package org.softwaretechnologies.employee;

public class Manager extends Employee {

    protected Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        if (month % 2 == 0) {
            return baseSalary;
        } else {
            return baseSalary / 2;
        }
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
