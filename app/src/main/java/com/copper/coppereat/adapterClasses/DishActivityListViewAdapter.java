package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.copper.coppereat.DishActivity;
import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Carts;
import com.copper.coppereat.customObjects.Dish;

import java.util.ArrayList;
import java.util.HashMap;

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
       // ImageView imageView=(ImageView)view.findViewById(R.id.imageViewl1);
        final TextView textView=(TextView)view.findViewById(R.id.dishNameCheckoutListViewTextView);

        textView.setText(getItem(position).getDishName()+"\n Rs "+getItem(position).getDishPrice());

        final Button increaseByOne=(Button)view.findViewById(R.id.increaseByOneCheckoutListViewButton);
        final Button decreaseByOne=(Button)view.findViewById(R.id.decreaseByOneCheckoutListViewButton);
        final Button displayItemCount=(Button)view.findViewById(R.id.addCheckoutListViewButton);
        HashMap<Dish,Integer> ch= Carts.getCartList();

        if(ch.size()==0) {
            decreaseByOne.setVisibility(View.INVISIBLE);
        }else {
            String s=textView.getText().toString();
            displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(textView.getText().toString())));
        }
        increaseByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseByOne.setVisibility(View.VISIBLE);
                Carts.addItemsToCarts(textView.getText().toString());
                DishActivity.showCartDetailsSummary();
                displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(textView.getText().toString())));

            }
        });

        decreaseByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=Carts.deleteItemsFromCarts(textView.getText().toString());
                DishActivity.showCartDetailsSummary();;
                if(!flag){
                   decreaseByOne.setVisibility(View.INVISIBLE);
                }
                displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(textView.getText().toString())));
            }
        });


        return view;
    }
}
