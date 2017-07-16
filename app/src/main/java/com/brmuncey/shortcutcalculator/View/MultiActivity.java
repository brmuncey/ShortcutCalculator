package com.brmuncey.shortcutcalculator.View;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.brmuncey.shortcutcalculator.Controller.CartController;
import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.R;

import java.util.List;

import static android.support.v7.app.AlertDialog.Builder;
import static com.brmuncey.shortcutcalculator.Model.CartItem.ItemType;

public class MultiActivity extends AppCompatActivity {

    private CartController cartController = new CartController();
    private EditText priceField;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        setupComponents();
    }

    private void setupComponents() {
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });
        priceField = (EditText) findViewById(R.id.priceText);
        cartListView = (ListView) findViewById(R.id.cartListView);
    }

    private void showDialog1() {


        Builder builder = new Builder(MultiActivity.this);
        builder.setTitle("Item Type").setItems(R.array.itemtypes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                double price = Double.parseDouble(priceField.getText().toString());
                ItemType type = cartController.getItemType(which);
                cartController.addToCart(price, type);
                priceField.getText().clear();
                updateListView();
            }
        });
        builder.create().show();
    }

    private void updateListView() {
        List<CartItem> itemList = cartController.getCartAsList();
        ArrayAdapter<CartItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList); //// TODO: 7/16/17 build custom adapter for objects 
        cartListView.setAdapter(arrayAdapter);
    }

}
