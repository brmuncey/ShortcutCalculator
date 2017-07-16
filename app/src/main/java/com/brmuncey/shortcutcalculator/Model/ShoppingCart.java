package com.brmuncey.shortcutcalculator.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cart = new ArrayList<>();

    public void addItem(double price, CartItem.ItemType type){ cart.add(new CartItem(price, type)); }

    public void deleteItem(CartItem item) { if(getItemIndex(item) > -1){ cart.remove(getItemIndex(item)); } }

    private int getItemIndex(CartItem item) {
        for(int i = 0; i < cart.size() ; i++){ if(cart.get(i).getPrice() == item.getPrice() && cart.get(i).getType() == item.getType()){ return i; } }
        return -1;
    }

    public double getTotal() {
        double total = 0;
        for(CartItem c : cart) { total += c.getPrice(); }
        return total;
    }

    public double getTaxedTotal() {
        double total = 0;
        for(CartItem c : cart) { total += c.getPriceWTax(); }
        return total;
    }

    public CartItem getItem(CartItem item) {
        int index = getItemIndex(item);
        if(index == -1) { return null; }
        return cart.get(index);
    }

    public List<CartItem> getCart() {
        return cart;
    }


}
