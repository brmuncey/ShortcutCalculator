package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Helper.CurrencyFormatter;

public class Calculator {

    public double computeTip(double price, double tip){return price * (tip/100); }

    public String computeSalesTax(String price, double tax){
        double value = Double.parseDouble(price);
        return CurrencyFormatter.format(value + (value * tax));
    }

}
