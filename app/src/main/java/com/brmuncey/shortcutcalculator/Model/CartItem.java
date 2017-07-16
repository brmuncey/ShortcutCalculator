package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

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

    public double getPrice() {
        return price;
    }

    ItemType getType() { return type; }

    double getPriceWTax() {
        return priceWTax;
    }

    private void setPriceWTax(double price) {
        SalesTaxCalculator stc = new SalesTaxCalculator(State.Alabama);
        priceWTax = stc.getTaxedPrice(price); }

    public enum ItemType {FOOD, HOUSEHOLD, ELECTRONIC, OTHER}
}
