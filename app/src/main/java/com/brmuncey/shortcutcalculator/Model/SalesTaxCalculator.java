package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

public class SalesTaxCalculator extends Calculator {

    private double tax;

    public SalesTaxCalculator(State state){ tax = new Location(state).getSalesTax(); }

    public double getTaxedPrice(double price) {
        return price + (price * tax);
    }
}
