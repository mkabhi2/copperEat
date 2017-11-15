package com.copper.coppereat.adapterClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.copper.coppereat.R;

/**
 * Created by abhmishr on 11/15/17.
 */

public class DishViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final ImageView dishImage;
    public final TextView restaurantName;
    public final TextView offer;
    public final TextView ratingValue;
    public final TextView numRatings;

    public DishViewHolder(View view) {
        super(view);
        mView = view;
        dishImage = (ImageView) view.findViewById(R.id.dish_list_item_image);
        restaurantName = (TextView) view.findViewById(R.id.dish_list_item_name) ;
        offer = (TextView) view.findViewById(R.id.dish_list_item_offer);
        ratingValue = (TextView) view.findViewById(R.id.dish_list_item_rating_value);
        numRatings = (TextView) view.findViewById(R.id.dish_list_item_rating_num);

    }

    public ImageView getRestaurantImage() {
        return dishImage;
    }

    public TextView getRestaurantName() {
        return restaurantName;
    }

    public TextView getOffer() {
        return offer;
    }

    public TextView getRatingValue() {
        return ratingValue;
    }

    public TextView getNumRatings() {
        return numRatings;
    }
}
