package com.copper.coppereat;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.copper.coppereat.adapterClasses.CheckoutActivityLisviewAdapter;
import com.copper.coppereat.customObjects.Carts;
import com.copper.coppereat.customObjects.Dish;

import java.util.HashMap;


public class CheckoutFragment extends Fragment {

    ListView chekoutListView;
    private static TextView totalCheckoutFragmentTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        chekoutListView = (ListView) view.findViewById(R.id.checkoutFragmentListViews);


        CheckoutActivityLisviewAdapter customAdapter = new CheckoutActivityLisviewAdapter(getContext());
        chekoutListView.setAdapter(customAdapter);

        totalCheckoutFragmentTextView=(TextView)view.findViewById(R.id.totalCheckoutFragmentTextView);

        updateTotalValueOfCart();

        return view;
    }

    public static void updateTotalValueOfCart() {
        HashMap<Dish, Integer> cart = Carts.getCartList();
        double price = 0;
        double totalCost = 0;
        if (cart.size() == 0) {
            totalCheckoutFragmentTextView.setText("Total Amount" + "\t\t\t" + "0");
        } else {
            for (Dish d : cart.keySet()) {
                price = Double.parseDouble(d.getDishPrice());
                Integer value = cart.get(d);
                totalCost = totalCost + (price * value);
            }
            totalCheckoutFragmentTextView.setText("Total Amount" + "\t\t\t" + String.valueOf(totalCost));

        }
    }

}
