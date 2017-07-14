package com.brmuncey.shortcutcalculator.Model;

public class Calculator {

    private double value;

    public Calculator(){ value = 0; }

    public Calculator(double value) { this.value = value; }

    public void add(double x) { value += x; }

    public void subtract(double x) { value -= x; }

    public void divide(double x) { value = value / x;}

    public void multiply(double x) { value = value * x; }

    public double getValue() { return value; }
}
