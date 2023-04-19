package com.Lab4.models.Taxes.Incomes;

public class RoyaltyIncome implements Income {
    private double amount;

    public RoyaltyIncome(double amount) {
        this.amount = amount;
    }

    @Override
    public double getincome() {
        return amount;
    }

    @Override
    public double calculateTax() {
        return amount * 0.2;
    }

    @Override
    public int compareTo(Income o) {
        return Double.compare(this.amount, o.getincome());
    }
}
