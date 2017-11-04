package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.copper.coppereat.R;
import com.copper.coppereat.customObjects.Restaurants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by abhmishr on 11/4/17.
 */

public class MyRestaurantRecyclerViewAdapter extends RecyclerView.Adapter<MyRestaurantRecyclerViewAdapter.ViewHolder> {

    private final List<Restaurants> restaurants;
    private final Context mContext;

    public MyRestaurantRecyclerViewAdapter(List<Restaurants> items, Context context) {
        restaurants = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Restaurants restaurants = this.restaurants.get(position);
        //TODO
        Picasso.with(mContext).load(this.restaurants.get(position).getPosterLink()).into(holder.mRestrauntImage);
        //Picasso.with(mContext).load("https://static.pexels.com/photos/70497/pexels-photo-70497.jpeg").into(holder.mRestrauntImage);
        holder.mRestrauntName.setText(restaurants.getRestaurantName());
        holder.mSpeciality.setText(restaurants.getSpeciality());
        holder.mDeliveryDetails.setText(restaurants.getDeliveryDetails());
        holder.mOffer.setText(restaurants.getOffer());
        holder.mRatingValue.setText(restaurants.getRating());
        Integer i = new Integer(restaurants.getNumOfRatings());
        holder.mNumRatings.setText(i.toString());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public final View mView;
        public final ImageView mRestrauntImage;
        public final TextView mRestrauntName;
        public final TextView mSpeciality;
        public final TextView mDeliveryDetails;
        public final TextView mOffer;
        public final TextView mRatingValue;
        public final TextView mNumRatings;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mRestrauntImage = (ImageView) view.findViewById(R.id.restaurant_list_item_image);
            mRestrauntName = (TextView) view.findViewById(R.id.restaurant_list_item_name) ;
            mSpeciality = (TextView) view.findViewById(R.id.restaurant_list_item_speciality);
            mDeliveryDetails = (TextView) view.findViewById(R.id.restaurant_list_item_cost_delivery_details);
            mOffer = (TextView) view.findViewById(R.id.restaurant_list_item_offer);
            mRatingValue = (TextView) view.findViewById(R.id.restaurant_list_item_rating_value);
            mNumRatings = (TextView) view.findViewById(R.id.restaurant_list_item_rating_num);

        }

    }
}

