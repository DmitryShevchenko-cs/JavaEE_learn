package com.Lab42.models.Incomes;

public class PropertySaleIncome implements Income{
    private double salePrice;

    public PropertySaleIncome(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public double getincome() {
        return salePrice;
    }

    @Override
    public double calculateTax() {
        return salePrice * 0.2;
    }

    @Override
    public int compareTo(Income o) {
        return Double.compare(this.salePrice, o.getincome());
    }
}