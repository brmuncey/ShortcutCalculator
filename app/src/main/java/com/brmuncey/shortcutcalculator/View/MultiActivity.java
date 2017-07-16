package com.brmuncey.shortcutcalculator.View;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brmuncey.shortcutcalculator.Controller.CartController;
import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.R;

import java.util.List;

import static android.support.v7.app.AlertDialog.Builder;

public class MultiActivity extends AppCompatActivity {

    private CartController cartController = new CartController();
    private EditText priceField;
    private ListView cartListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        setupComponents();
        if (!cartController.checkForState()) { /*set state, save to json */}
    }

    private void setupComponents() {
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        priceField = (EditText) findViewById(R.id.priceText);
        cartListView = (ListView) findViewById(R.id.cartListView);
    }

    private void showDialog() {
        Builder builder = new Builder(getActivity());
        builder.setTitle("Item Type").setItems(R.array.itemtypes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (!priceField.getText().toString().isEmpty()) {
                    double price = Double.parseDouble(priceField.getText().toString());
                    cartController.addToCart(price, cartController.getItemType(which));
                    priceField.getText().clear();
                    updateListView();
                    updateTotal();
                    hideKeyboard();
                    toast("Item added");
                } else {
                    toast("You must enter a price");
                }
            }
        });
        builder.create().show();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
            Log.d("KB", "Problem hiding keyboard");
        }
    }

    private void updateTotal() {
        TextView priceLabel = (TextView) findViewById(R.id.priceLabel);
        priceLabel.setText(cartController.getTaxedTotal());
    }

    private void toast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    private void updateListView() {
        List<CartItem> itemList = cartController.getCart();
        ArrayAdapter<CartItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        cartListView.setAdapter(arrayAdapter);
    }

    private Context getActivity() {
        return MultiActivity.this;
    }
}
