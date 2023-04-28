package com.Lab5.models.Taxes;

import com.Lab5.models.Taxes.Incomes.Income;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Taxes {
    private List<Income> incomes = new ArrayList<Income>();

    public void addIncome(Income income) { incomes.add(income); }
    public void delIncome(Income income) { incomes.remove(income); }

    public double calculateTaxes() {
        double totalTaxes = 0;
        for (Income income : incomes) {
            totalTaxes += income.calculateTax();}
        return totalTaxes;
    }

    public void sortIncomesByTaxes() { Collections.sort(incomes); }

    public List<Income> TaxList(){ return incomes; }

    public String toString(){
        String str = "";
        for (var tax:incomes) {
            str += tax.getClass().getSimpleName() + ": " + String.valueOf(tax.getincome()) + "    Tax:" + String.valueOf(tax.calculateTax()) + "\n";
        }
        return str;
    }

}
