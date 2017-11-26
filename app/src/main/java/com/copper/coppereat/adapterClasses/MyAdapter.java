package com.copper.coppereat.adapterClasses;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.copper.coppereat.DishActivity;
import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Carts;
import com.copper.coppereat.customObjects.Dish;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kumarpallav on 26/11/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener{



    DishActivity dishActivity=new DishActivity();
    Context context;
    ArrayList<Dish> dish=new ArrayList<Dish>();

    public MyAdapter(Context context, ArrayList<Dish> dish){
        this.context=context;
        this.dish=dish;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView.setText(dish.get(position).getDishName()+"\n Rs "+dish.get(position).getDishPrice());
        HashMap<Dish,Integer> ch= Carts.getCartList();

        if(ch.size()==0) {
            holder.decreaseByOne.setVisibility(View.INVISIBLE);
        }else {
            String s=holder.textView.getText().toString();
            holder.displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(holder.textView.getText().toString())));
        }
        holder.increaseByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.decreaseByOne.setVisibility(View.VISIBLE);
                Carts.addItemsToCarts(holder.textView.getText().toString());
                DishActivity.showCartDetailsSummary();
                holder.displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(holder.textView.getText().toString())));

            }
        });

        holder.decreaseByOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=Carts.deleteItemsFromCarts(holder.textView.getText().toString());
                DishActivity.showCartDetailsSummary();;
                if(!flag){
                    holder.decreaseByOne.setVisibility(View.INVISIBLE);
                }
                holder.displayItemCount.setText(String.valueOf(Carts.getQuantityForSelectedItem(holder.textView.getText().toString())));
            }
        });

    }


    @Override
    public int getItemCount() {
        return dish.size();
    }


    @Override
    public void onClick(View v) {

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public final CardView mCardView;
        public final TextView textView;
        public final Button increaseByOne;
        public final Button decreaseByOne;
        public final Button displayItemCount;

        public MyViewHolder(View v){
            super(v);

            mCardView = (CardView) v.findViewById(R.id.card_view);
            increaseByOne =(Button)v.findViewById(R.id.increaseByOneCheckoutListViewButton);
            decreaseByOne=(Button)v.findViewById(R.id.decreaseByOneCheckoutListViewButton);
            textView=(TextView)v.findViewById(R.id.dishNameCheckoutListViewTextView);
            displayItemCount=(Button) v.findViewById(R.id.addCheckoutListViewButton);

        }

    }

}