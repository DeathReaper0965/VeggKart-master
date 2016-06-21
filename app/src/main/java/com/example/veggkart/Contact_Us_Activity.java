package com.example.veggkart;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Prince on 6/8/2016.
 */
public class Contact_Us_Activity extends DialogFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment, container,
                false);
        getDialog().setTitle("Contact Us");
        // Do something else
        return rootView;
    }


}
