package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

class Location {

    private double salesTax;

    Location(State state){ salesTax = lookUpStateTax(state); }

    private double lookUpStateTax(State state) { return 0.08; } // todo connect to firebase db and get state sales tax data.

    double getSalesTax() { return salesTax; }

}
