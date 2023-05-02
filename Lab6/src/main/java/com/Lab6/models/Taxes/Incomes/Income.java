package com.Lab6.models.Taxes.Incomes;

public interface Income extends Comparable<Income>{
    public abstract double getincome();
    public abstract double calculateTax();
}
