package com.copper.coppereat.adapterClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.copper.coppereat.R;

/**
 * Created by abhmishr on 11/15/17.
 */

public class RestaurantViewHolder extends RecyclerView.ViewHolder {

    public final View mView;
    public final ImageView restaurantImage;
    public final TextView restaurantName;
    public final TextView speciality;
    public final TextView deliveryDetails;
    public final TextView offer;
    public final TextView ratingValue;
    public final TextView numRatings;

    public RestaurantViewHolder(View view) {
        super(view);
        mView = view;
        restaurantImage = (ImageView) view.findViewById(R.id.restaurant_list_item_image);
        restaurantName = (TextView) view.findViewById(R.id.restaurant_list_item_name) ;
        speciality = (TextView) view.findViewById(R.id.restaurant_list_item_speciality);
        deliveryDetails = (TextView) view.findViewById(R.id.restaurant_list_item_cost_delivery_details);
        offer = (TextView) view.findViewById(R.id.restaurant_list_item_offer);
        ratingValue = (TextView) view.findViewById(R.id.restaurant_list_item_rating_value);
        numRatings = (TextView) view.findViewById(R.id.restaurant_list_item_rating_num);

    }

    public ImageView getRestaurantImage() {
        return restaurantImage;
    }

    public TextView getRestaurantName() {
        return restaurantName;
    }

    public TextView getSpeciality() {
        return speciality;
    }

    public TextView getDeliveryDetails() {
        return deliveryDetails;
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