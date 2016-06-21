package com.example.veggkart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Praneet on 16/5/2016.
 */
public class CustomProductAdapter extends BaseAdapter {
    String[] images;
    String[] proNames;
    String[] desc;
    Activity context;
    ButtonClickNotify bClick;
    private static LayoutInflater inflater;
    public ImageLoader imageloader;

    public CustomProductAdapter(MainActivity mainActivity, String[] imagesUrl, String[] proNames, String[] desc) {
        this.images = imagesUrl;
        this.proNames = proNames;
        this.desc = desc;
        context = mainActivity;
        try{
            bClick=(MainActivity)context;
        }catch(Throwable e){
            //interface is not implemented
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageloader = new ImageLoader(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return proNames.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView productView;
        ImageView imageView;
        //NumberPicker numberPicker;
        TextView descView;
        TextView productCost;
        ImageButton iButton;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        final CustomProductAdapter p=this;
        final View rowView;
        rowView = inflater.inflate(R.layout.product_list_test, null);
        holder.productView=(TextView) rowView.findViewById(R.id.productView);
        holder.descView=(TextView) rowView.findViewById(R.id.descView);
        holder.imageView=(ImageView) rowView.findViewById(R.id.imageView);
        holder.productCost=(TextView) rowView.findViewById(R.id.productCost);
        holder.iButton=(ImageButton) rowView.findViewById(R.id.imageButton);
        holder.iButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bClick.onButtonClick(pos);
            }
        });
        holder.productView.setText(proNames[pos]);
        holder.descView.setText(R.string.description_product);
        holder.productCost.setText("0.00");
        ImageView image =holder.imageView;
        imageloader.DisplayImage(images[pos],image);


        return rowView;
    }
}
