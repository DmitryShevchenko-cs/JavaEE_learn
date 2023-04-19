package com.Lab4.models.Taxes.Incomes;

public class JobIncome implements Income {
    private double salary;

    public JobIncome(double salary) {
        this.salary = salary;
    }

    @Override
    public double getincome() {
        return salary;
    }

    @Override
    public double calculateTax() {
        return salary * 0.13;
    }

    @Override
    public int compareTo(Income o) {
        return Double.compare(this.salary, o.getincome());
    }
}