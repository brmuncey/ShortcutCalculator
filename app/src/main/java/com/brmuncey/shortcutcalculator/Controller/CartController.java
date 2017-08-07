package com.brmuncey.shortcutcalculator.Controller;

import com.brmuncey.shortcutcalculator.Data.State;
import com.brmuncey.shortcutcalculator.Helper.CurrencyFormatter;
import com.brmuncey.shortcutcalculator.Model.Calculator;
import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.Model.ShoppingCart;
import com.brmuncey.shortcutcalculator.Model.UserState;
import com.google.gson.Gson;

import java.util.List;

public class CartController {

    public static UserState userState;
    private ShoppingCart cart = new ShoppingCart();

    public void addToCart(double price  , CartItem.ItemType type){ cart.addItem(price, type); }

    public String getTip(String price, String tipPercentage){ return CurrencyFormatter.format(new Calculator().computeTip(Double.parseDouble(price), Double.parseDouble(tipPercentage))); }

    public String getTaxedTotal() { return cart.getTaxedTotal(); }

    public CartItem.ItemType getItemType(int index) { return CartItem.ItemType.values()[index]; }

    public List<CartItem> getCart() { return cart.getCart(); }

    public void updateItem(String newPrice, String newType, CartItem item) {
        double price = Double.parseDouble(newPrice);
        if(price != item.getPrice()) { item.updatePrice(price); }

        if(CartItem.ItemType.valueOf(newType) != item.getType()) { item.updateType(CartItem.ItemType.valueOf(newType)); }
    }

    public void setState(String stateJson) {
        userState = new Gson().fromJson( stateJson , UserState.class );
        State state = userState.getState();
        userState.setSalesTax(state);
    }

    public void deleteItem(CartItem item) { cart.deleteItem(item); }

    public String getSingleItemTax(String s) { return new Calculator().computeSalesTax(s,userState.getSalesTax()); }
}
