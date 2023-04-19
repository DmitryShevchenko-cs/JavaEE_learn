package com.Lab4.models.Taxes.Incomes;

public interface Income extends Comparable<Income>{
    public abstract double getincome();
    public abstract double calculateTax();
}
