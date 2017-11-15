package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Dish;
import com.copper.coppereat.customObjects.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhmishr on 11/4/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Object> items;
    private final Context mContext;

    private final int RESTAURANT = 0, DISH = 1;

    public RecyclerViewAdapter(List<Object> items, Context context) {
        this.items = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;

        if(viewType == RESTAURANT){
            itemView = inflater.inflate(R.layout.item_restaurant_list, parent, false);
            viewHolder = new RestaurantViewHolder(itemView);
            return viewHolder;
        }
        else {
            itemView = inflater.inflate(R.layout.item_restaurant_list, parent, false);
            viewHolder = new RestaurantViewHolder(itemView);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(holder.getItemViewType() == RESTAURANT) {
            RestaurantViewHolder restaurantViewHolder = (RestaurantViewHolder) holder;
            configureRestaurantViewHolder(restaurantViewHolder, position);
        }
        else {
            DishViewHolder dishViewHolder = (DishViewHolder) holder;
            configureDishViewHolder(dishViewHolder, position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position) instanceof Restaurant) {
            return RESTAURANT;
        } else if (items.get(position) instanceof Dish) {
            return DISH;
        }
        return -1;
    }

    public void configureRestaurantViewHolder(RestaurantViewHolder restaurantViewHolder, int position){
        Restaurant restaurant = (Restaurant) items.get(position);
        if(restaurant != null) {
            Picasso.with(mContext).load(restaurant.getPosterLink()).into(restaurantViewHolder.restaurantImage);
            restaurantViewHolder.restaurantName.setText(restaurant.getRestaurantName());
            restaurantViewHolder.speciality.setText(restaurant.getSpeciality());
            restaurantViewHolder.deliveryDetails.setText(restaurant.getDeliveryDetails());
            restaurantViewHolder.offer.setText(restaurant.getOffer());
            restaurantViewHolder.ratingValue.setText(restaurant.getRating());
            Integer i = new Integer(restaurant.getNumOfRatings());
            restaurantViewHolder.numRatings.setText(i.toString());
        }
    }

    public void configureDishViewHolder(DishViewHolder dishViewHolder, int position) {
        Dish dish = (Dish) items.get(position);
        if(dish != null) {
            Picasso.with(mContext).load(dish.getPosterLink()).into(dishViewHolder.dishImage);
            dishViewHolder.restaurantName.setText(dish.getDishName());
            dishViewHolder.offer.setText(dish.getOffer());
            dishViewHolder.ratingValue.setText(dish.getRating());
            Integer i = new Integer(dish.getNumOfRatings());
            dishViewHolder.numRatings.setText(i.toString());
        }
    }
}

