package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.copper.coppereat.DishActivity;
import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Dish;

import java.util.ArrayList;

/**
 * Created by vijaysingh on 11/7/2017.
 */

public class DishActivityListViewAdapter extends BaseAdapter {
    DishActivity dishActivity=new DishActivity();
    Context context;
    ArrayList<Dish> dish=new ArrayList<Dish>();
    public DishActivityListViewAdapter(Context context, ArrayList<Dish> dish){
        this.context=context;
        this.dish=dish;
    }

    @Override
    public int getCount() {
        return dish.size();
    }

    @Override
    public Dish getItem(int position) {
        return dish.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
           view= layoutInflater.inflate(R.layout.listview_dish,null);
        }
        ImageView imageView=(ImageView)view.findViewById(R.id.imageViewl1);
        TextView textView=(TextView)view.findViewById(R.id.textViewl1);

        textView.setText(getItem(position).getDishName());
        return view;
    }
}
