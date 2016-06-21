package com.example.veggkart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


/**
 * Created by Prince on 6/8/2016.
 */
public class cart_activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button b=(Button)findViewById(R.id.check_out_button);
        //LinearLayout check_out = (LinearLayout) findViewById(R.id.check_out);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart_activity.this, Delivery_Activity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onBackPressed();
        return true;

    }
}