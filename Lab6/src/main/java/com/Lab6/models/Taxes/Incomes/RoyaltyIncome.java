package com.Lab6.models.Taxes.Incomes;

public class RoyaltyIncome implements Income {
    private double _income;

    public RoyaltyIncome(double _income) {
        this._income = _income;
    }

    @Override
    public double getincome() {
        return _income;
    }

    @Override
    public double calculateTax() {
        return _income * 0.2;
    }

    @Override
    public int compareTo(Income o) {
        return Double.compare(this._income, o.getincome());
    }
}
