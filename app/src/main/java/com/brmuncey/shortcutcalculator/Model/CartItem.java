package com.brmuncey.shortcutcalculator.Model;

import com.brmuncey.shortcutcalculator.Data.State;

class CartItem {

    public enum ItemType { FOOD , HOUSEHOLD , ELECTRONIC , OTHER }

    private double price;
    private ItemType type;
    private double priceWTax;

    public CartItem(double price, ItemType type){
        this.price = price;
        this.type = type;
    }

    public void setPrice(double price){ this.price = price; }

    public double getPrice() { return price; }

    public ItemType getType() { return type; }

    public void setPriceWTax(double price) {
        SalesTaxCalculator stc = new SalesTaxCalculator(State.Alabama);
        priceWTax = stc.getTaxedPrice(price); }

    private double getPriceWTax() { return priceWTax; }
}
