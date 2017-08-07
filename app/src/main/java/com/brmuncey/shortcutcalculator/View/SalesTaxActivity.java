package com.brmuncey.shortcutcalculator.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.brmuncey.shortcutcalculator.Controller.CartController;
import com.brmuncey.shortcutcalculator.Helper.StateFetcher;
import com.brmuncey.shortcutcalculator.R;

public class SalesTaxActivity extends AppCompatActivity {

    private CartController cartController = new CartController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_tax);
        setupButtons();

        StateFetcher stateFetcher = new StateFetcher();
        if(stateFetcher.loadStateFromJson(getActivity()) != null) { cartController.setState(stateFetcher.loadStateFromJson(getActivity())); }
        //else { showStateSelector(); }
    }

    private Context getActivity() { return SalesTaxActivity.this; }

    private void setupButtons() {
        Button calculate = (Button) findViewById(R.id.taxCalculateBtn);
        Button clear = (Button) findViewById(R.id.taxClearBtn);
        final TextView price = (TextView) findViewById(R.id.taxPriceLbl);
        final EditText priceInput = (EditText) findViewById(R.id.itemPriceTaxBox);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { price.setText(cartController.getSingleItemTax(priceInput.getText().toString())); }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priceInput.setText("");
                price.setText("");
            }
        });
    }
}
