package com.brmuncey.shortcutcalculator.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cart = new ArrayList<CartItem>();

    public void addItem(double price, CartItem.ItemType type){ cart.add(new CartItem(price, type)); }

    public List<CartItem> getCart() { return cart; }

    public void deleteItem(CartItem item) { if(getItemIndex(item) > -1){ cart.remove(getItemIndex(item)); } }

    private int getItemIndex(CartItem item) {
        for(int i = 0; i < cart.size() ; i++){
            if(cart.get(i).getPrice() == item.getPrice() && cart.get(i).getType() == item.getType()){ return i; }
        }
        return -1;
    }

}
