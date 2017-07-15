package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

public class Location {

    private double salesTax;

    public Location(State state){ salesTax = lookUpStateTax(state); }

    private double lookUpStateTax(State state) { return 0.08; } // todo connect to firebase db and get state sales tax data.

    public double getSalesTax() { return salesTax; }

}
