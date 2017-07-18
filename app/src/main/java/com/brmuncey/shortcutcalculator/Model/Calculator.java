package com.brmuncey.shortcutcalculator.Model;

public class Calculator {

    private double value;

    Calculator(){ value = 0; }

    public Calculator(double value) { this.value = value; }

    public void add(double value) { this.value += value; }

    public void subtract(double value) { this.value -= value; }

    public void divide(double value) { this.value = this.value / value;}

    public void multiply(double value) { this.value = this.value * value; }

    public double getValue() { return value; }
}
