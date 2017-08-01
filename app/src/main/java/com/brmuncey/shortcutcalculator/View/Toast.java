package com.brmuncey.shortcutcalculator.View;

import android.content.Context;

class Toast {

    public static void makeToast(Context context, String message) { android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show(); }

}
