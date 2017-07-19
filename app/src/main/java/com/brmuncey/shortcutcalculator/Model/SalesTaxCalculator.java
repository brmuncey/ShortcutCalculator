package com.brmuncey.shortcutcalculator.Model;

class SalesTaxCalculator extends Calculator {

    private double tax;

    SalesTaxCalculator(){ tax = new Location().getSalesTax(); }

    double getTaxedPrice(double price) { return price + (price * tax); }
}
