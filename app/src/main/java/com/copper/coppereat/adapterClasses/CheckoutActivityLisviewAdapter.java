package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.copper.coppereat.CheckoutFragment;
import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Carts;

/**
 * Created by kumarpallav on 18/11/17.
 */

public class CheckoutActivityLisviewAdapter extends BaseAdapter {

    Context context;
    public CheckoutActivityLisviewAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return Carts.getCartList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            view= layoutInflater.inflate(R.layout.listview_checkout,null);
        }

        final TextView dishNameCheckoutListView=(TextView)view.findViewById(R.id.dishNameCheckoutListView);
        final Button increaseByOneCheckoutListView=(Button)view.findViewById(R.id.increaseByOneCheckoutListView);
        final Button decreaseByOneCheckoutListView=(Button)view.findViewById(R.id.decreaseByOneCheckoutListView);
        final TextView showAmountCheckoutListView=(TextView)view.findViewById(R.id.showAmountCheckoutListView);
        showAmountCheckoutListView.setText(String.valueOf(Carts.getTotalDishPriceByPosition(Carts.getDishNameByPosition(position))));
        dishNameCheckoutListView.setText(Carts.getDishNameByPosition(position));
        showAmountCheckoutListView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
        dishNameCheckoutListView.setGravity(Gravity.CENTER_VERTICAL);
        increaseByOneCheckoutListView.setGravity(Gravity.CENTER_VERTICAL);
        decreaseByOneCheckoutListView.setGravity(Gravity.CENTER_VERTICAL);


        increaseByOneCheckoutListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Carts.addItemsToCartsFromCheckoutPage(dishNameCheckoutListView.getText().toString());
                showAmountCheckoutListView.setText(String.valueOf(Carts.getTotalDishPriceByPosition(dishNameCheckoutListView.getText().toString())));
                showAmountCheckoutListView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
                CheckoutFragment.updateTotalValueOfCart();
            }
        });

        decreaseByOneCheckoutListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carts.deletetemsToCartsFromCheckoutPage(dishNameCheckoutListView.getText().toString());
                showAmountCheckoutListView.setText(String.valueOf(Carts.getTotalDishPriceByPosition(dishNameCheckoutListView.getText().toString())));
                showAmountCheckoutListView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
                CheckoutFragment.updateTotalValueOfCart();
            }
        });


        return view;

    }

}
