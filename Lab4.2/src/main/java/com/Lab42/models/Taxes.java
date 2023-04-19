package com.Lab42.models;

import com.Lab42.models.Incomes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Taxes {
    private List<Income> incomes = new ArrayList<Income>();

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public double calculateTaxes() {
        double totalTaxes = 0;
        for (Income income : incomes) {
            totalTaxes += income.calculateTax();
        }
        return totalTaxes;
    }

    public void sortIncomesByTaxes() {
        Collections.sort(incomes);
    }

    public String toString(){
        String str = "";
        for (var tax:incomes) {
            str += "Income:" + String.valueOf(tax.getincome()) + " Tax:" + String.valueOf(tax.calculateTax()) + "\n";
        }
        return str;
    }

}
