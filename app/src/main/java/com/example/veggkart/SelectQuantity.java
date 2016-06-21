package com.example.veggkart;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Created by HIMANSHU on 6/10/2016.
 */

public class SelectQuantity extends DialogFragment {
    NumberPicker numberPicker;
    Button proceed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_sel_qty, container,
                false);
        getDialog().setTitle("Select Quantity");
        numberPicker=(NumberPicker) rootView.findViewById(R.id.np_qty);
        proceed=(Button) rootView.findViewById(R.id.bProceed);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setEnabled(true);
        numberPicker.getDisplay();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add the product to cart database here..

                //till here
                Toast.makeText(getActivity(),"Added to cart..",Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        return rootView;
    }
}
