package com.brmuncey.shortcutcalculator.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.brmuncey.shortcutcalculator.Controller.CartController;
import com.brmuncey.shortcutcalculator.R;

import static com.brmuncey.shortcutcalculator.View.Toast.makeToast;

public class TipActivity extends AppCompatActivity {

    CartController cartController = new CartController();
    EditText priceBox;
    Spinner percentageSpinner;
    TextView tipLabl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        setupButtons();
    }

    private void setupButtons() {
        Button calculate = (Button) findViewById(R.id.calculateBtn);
        Button clear = (Button) findViewById(R.id.clearBtn);
        priceBox = (EditText) findViewById(R.id.priceBox);
        percentageSpinner = (Spinner) findViewById(R.id.percentSpinner);
        tipLabl = (TextView) findViewById(R.id.tipLbl);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(priceBox.getText().toString().isEmpty()) { tipLabl.setText(R.string.badInput); }
                else { tipLabl.setText(cartController.getTip(priceBox.getText().toString(), percentageSpinner.getSelectedItem().toString())); }
                makeToast(getActivity(),"Calculated");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipLabl.setText("");
                priceBox.setText("");
                makeToast(getActivity(),"Cleared");
            }
        });

    }

    private Context getActivity() { return TipActivity.this; }
}
