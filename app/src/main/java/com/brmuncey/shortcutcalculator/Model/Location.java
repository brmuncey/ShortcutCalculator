package com.brmuncey.shortcutcalculator.Model;

class Location {

    private double salesTax;

    Location(){ salesTax = lookUpStateTax(); }

    private double lookUpStateTax() { return 0.08; } // todo connect to firebase db and get state sales tax data.

    double getSalesTax() { return salesTax; }

}
