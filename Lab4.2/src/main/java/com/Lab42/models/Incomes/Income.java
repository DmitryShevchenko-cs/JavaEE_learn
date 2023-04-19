package com.Lab42.models.Incomes;

public interface Income extends Comparable<Income>{
    public abstract double getincome();
    public abstract double calculateTax();
}
