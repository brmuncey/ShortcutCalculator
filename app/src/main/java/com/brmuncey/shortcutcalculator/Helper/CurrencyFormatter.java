package com.brmuncey.shortcutcalculator.Helper;


import java.text.NumberFormat;

public class CurrencyFormatter {

    public static String format(double price) { return NumberFormat.getCurrencyInstance().format(price); }
}
