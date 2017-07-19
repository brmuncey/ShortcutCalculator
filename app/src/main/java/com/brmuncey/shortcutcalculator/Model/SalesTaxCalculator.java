package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

class SalesTaxCalculator extends Calculator {

    private double tax;

    SalesTaxCalculator(State state){ tax = new Location(state).getSalesTax(); }

    double getTaxedPrice(double price) { return price + (price * tax); }
}
