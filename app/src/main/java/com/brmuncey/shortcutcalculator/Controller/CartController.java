package com.brmuncey.shortcutcalculator.Controller;

import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.Model.ShoppingCart;

import java.util.List;

public class CartController {

    private ShoppingCart cart = new ShoppingCart();

    public void addToCart(double price  , CartItem.ItemType type){ cart.addItem(price, type); }

    public void deleteFromCart(CartItem item) { cart.deleteItem(item); }

    public double getTotal(){ return cart.getTotal(); }

    public String getTaxedTotal() {
        return cart.getTaxedTotal();
    }

    public void updateItemPrice(CartItem item, double newPrice) { if(cart.getItem(item) != null) { cart.getItem(item).updatePrice(newPrice); } }

    public CartItem.ItemType getItemType(int index) {
        return CartItem.ItemType.values()[index];
    }

    public List<CartItem> getCartAsList() {
        return cart.getCart();
    }
}
