package com.brmuncey.shortcutcalculator.View;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brmuncey.shortcutcalculator.Controller.CartController;
import com.brmuncey.shortcutcalculator.Helper.StateFetcher;
import com.brmuncey.shortcutcalculator.Model.CartItem;
import com.brmuncey.shortcutcalculator.Model.CartItem.ItemType;
import com.brmuncey.shortcutcalculator.R;

import static android.support.v7.app.AlertDialog.Builder;
import static com.brmuncey.shortcutcalculator.View.Toast.makeToast;

public class MultiActivity extends AppCompatActivity{

    private CartController cartController = new CartController();
    private EditText priceField;
    private ListView cartListView;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        setupComponents();

        StateFetcher stateFetcher = new StateFetcher();
        if(stateFetcher.loadStateFromJson(getActivity()) != null) { cartController.setState(stateFetcher.loadStateFromJson(getActivity())); }
        else { showStateSelector(); }
    }

    private void showStateSelector() {
        Log.d("State", "Showing state selector");
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.select_state);
        dialog.show();
    }

    private void setupComponents() {
        addBtn = (Button) findViewById(R.id.addBtn);
        priceField = (EditText) findViewById(R.id.priceText);
        cartListView = (ListView) findViewById(R.id.cartListView);
        addClickListeners();
    }

    private void addClickListeners() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { showDialog(); }
        });
        cartListView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { editPopup(parent.getItemAtPosition(position)); }
        });
    }

    private void editPopup(Object object) {
        CartItem item = (CartItem) object;

        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.edit_item);

        TextView currLbl = (TextView) dialog.findViewById(R.id.itemLbl);
        currLbl.setText(item.getItemInfo());

        EditText newPrice = (EditText) dialog.findViewById(R.id.updatePriceBox);
        String price = Double.toString(item.getPrice());
        newPrice.setText(price);

        Spinner spinner = (Spinner) dialog.findViewById(R.id.typeSpinner);
        spinner.setSelection(getPosition(spinner,item));

        addEditBtnListeners(dialog,item);

        dialog.show();
    }

    private void addEditBtnListeners(final Dialog dialog, final CartItem item) {
        Button update = (Button) dialog.findViewById(R.id.updateBtn);
        Button cancel = (Button) dialog.findViewById(R.id.cancelBtn);
        Button delete = (Button) dialog.findViewById(R.id.deleteItemBtn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText v1 = (EditText) dialog.findViewById(R.id.updatePriceBox);
                Spinner spinner = (Spinner) dialog.findViewById(R.id.typeSpinner);
                cartController.updateItem( v1.getText().toString() , spinner.getSelectedItem().toString().toUpperCase() , item);
                updateListView();
                dialog.hide();
                makeToast(getActivity(),"Item updated");
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { dialog.hide(); }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartController.deleteItem(item);
                updateListView();
                dialog.hide();
                makeToast(getActivity(),"Item deleted");
            }
        });

    }

    private int getPosition(Spinner spinner, CartItem item) {
        int index = 0;
        for(int i = 0; i < ItemType.values().length; i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(item.getType().toString())){
                index = i;
                break;
            }
        }
        return index;
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
                    makeToast(getActivity(),"Item added");
                } else { makeToast(getActivity(),"You must enter a price"); }
            }
        });
        builder.create().show();
    }

    @SuppressWarnings("ConstantConditions")
    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try { inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); }
        catch (NullPointerException e) { Log.d("KB", "Problem hiding keyboard"); }
    }

    private void updateTotal() {
        TextView priceLabel = (TextView) findViewById(R.id.priceLabel);
        priceLabel.setText(cartController.getTaxedTotal());
    }

    private void updateListView() {
        ArrayAdapter<CartItem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartController.getCart());
        cartListView.setAdapter(adapter);
    }

    private Context getActivity() { return MultiActivity.this; }

}
