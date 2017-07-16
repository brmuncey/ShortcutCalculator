package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

import java.text.NumberFormat;

public class CartItem {

    private double price;
    private ItemType type;
    private double priceWTax;

    CartItem(double price, ItemType type){
        this.price = price;
        this.type = type;
        setPriceWTax(this.price);
    }

    public void updatePrice(double price){ this.price = price; }

    double getPrice() {
        return price;
    }

    ItemType getType() { return type; }

    double getPriceWTax() {
        return priceWTax;
    }

    private void setPriceWTax(double price) {
        SalesTaxCalculator stc = new SalesTaxCalculator(State.Alabama);
        priceWTax = stc.getTaxedPrice(price);
    }

    @Override
    public String toString() {
        return getType().toString() + "\t" + format(getPriceWTax());
    }

    private String format(double price) {
        return NumberFormat.getCurrencyInstance().format(price);
    }

    public enum ItemType {FOOD, HOUSEHOLD, ELECTRONIC, OTHER}
}
