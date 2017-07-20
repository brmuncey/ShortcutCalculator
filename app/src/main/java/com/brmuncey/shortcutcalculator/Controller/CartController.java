package com.brmuncey.shortcutcalculator.Controller;

import com.brmuncey.shortcutcalculator.Data.State;
import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.Model.ShoppingCart;
import com.brmuncey.shortcutcalculator.Model.UserState;
import com.google.gson.Gson;

import java.util.List;

public class CartController {

    public static UserState userState;
    private ShoppingCart cart = new ShoppingCart();
    private State state;

    public void addToCart(double price  , CartItem.ItemType type){ cart.addItem(price, type); }

    //public void deleteFromCart(CartItem item) { cart.deleteItem(item); }

    //public double getTotal(){ return cart.getTotal(); }

    public String getTaxedTotal() { return cart.getTaxedTotal(); }

    //public void updateItemPrice(CartItem item, double newPrice) { if(cart.getItem(item) != null) { cart.getItem(item).updatePrice(newPrice); } }

    public CartItem.ItemType getItemType(int index) { return CartItem.ItemType.values()[index]; }

    public List<CartItem> getCart() { return cart.getCart(); }

    public void updateItem(String newPrice, String newType, CartItem item) {
        double price = Double.parseDouble(newPrice);
        if(price != item.getPrice()) { item.updatePrice(price); }

        if(CartItem.ItemType.valueOf(newType) != item.getType()) { item.updateType(CartItem.ItemType.valueOf(newType)); }
    }

    public void setState(String stateJson) {
        userState = new Gson().fromJson( stateJson , UserState.class );
        this.state = userState.getState();
        userState.setSalesTax( state );
    }

}
