package com.brmuncey.shortcutcalculator.View;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.brmuncey.shortcutcalculator.R;

import static com.brmuncey.shortcutcalculator.View.Toast.makeToast;

public class TipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);
        setupButtons();
    }

    private void setupButtons() {
        Button calculate = (Button) findViewById(R.id.calculateBtn);
        Button clear = (Button) findViewById(R.id.clearBtn);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(getActivity(),"Calculate");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(getActivity(),"Clear");
            }
        });

    }

    private Context getActivity() { return TipActivity.this; }
}
