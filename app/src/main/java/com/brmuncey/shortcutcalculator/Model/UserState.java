package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

public class UserState {

    private double salesTax;

    private String stateKey; // used for object construction using json. // TODO: 8/7/17

    public double getSalesTax() { return salesTax; }

    public void setSalesTax(State state) { salesTax = 0.08; } // will connect to db and fetch correct tax amount // TODO: 8/7/17

    public State getState() { return State.valueOf(stateKey); } // TODO: 8/7/17
}
