package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

import java.text.NumberFormat;

public class CartItem {

    private double price;
    private ItemType type;
    private double priceWTax;
    private State state;

    CartItem(double price, ItemType type){
        this.price = price;
        this.type = type;
        setPriceWTax(this.price);
    }

    public void updatePrice(double price){
        this.price = price;
        setPriceWTax(this.price);
    }

    public double getPrice() { return price; }

    public ItemType getType() { return type; }

    public String getItemInfo() { return "Type: " + type.toString() +"\tPrice: "+ format(price); }

    double getPriceWTax() {
        return priceWTax;
    }

    private void setPriceWTax(double price) { priceWTax = new SalesTaxCalculator(state).getTaxedPrice(price); }

    @Override
    public String toString() { return getType().toString() + "\tTaxed: " + format(getPriceWTax()); }

    private String format(double price) {
        return NumberFormat.getCurrencyInstance().format(price);
    }

    public void setState(State state) { this.state = state; } //needs refactored

    public void updateType(ItemType newType) { type = newType; }

    public enum ItemType { FOOD, HOUSEHOLD, ELECTRONIC, OTHER }
}
