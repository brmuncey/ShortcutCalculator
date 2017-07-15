package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

public class CartItem {

    public enum ItemType { FOOD , HOUSEHOLD , ELECTRONIC , OTHER }

    private double price;
    private ItemType type;
    private double priceWTax;

    CartItem(double price, ItemType type){
        this.price = price;
        this.type = type;
        setPriceWTax(this.price);
    }

    public void updatePrice(double price){ this.price = price; }

    double getPrice() { return price; }

    ItemType getType() { return type; }

    private void setPriceWTax(double price) {
        SalesTaxCalculator stc = new SalesTaxCalculator(State.Alabama);
        priceWTax = stc.getTaxedPrice(price); }

    double getPriceWTax() { return priceWTax; }
}
