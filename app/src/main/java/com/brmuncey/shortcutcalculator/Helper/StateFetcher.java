package com.brmuncey.shortcutcalculator.Helper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class StateFetcher extends AppCompatActivity{

    public String loadStateFromJson(Context context) {
        try {
            InputStream is = context.getAssets().open("user.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            return null;
        }
    }

}
