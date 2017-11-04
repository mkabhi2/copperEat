package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.copper.coppereat.RestaurantListFragment;
import com.copper.coppereat.customObjects.Restaurants;

import java.util.ArrayList;

/**
 * Created by abhmishr on 10/28/17.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    ArrayList<Restaurants> restaurants;
    public RestaurantListFragment restaurantListFragment[] = new RestaurantListFragment[3];

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm, ArrayList<Restaurants> restaurants) {
        super(fm);
        mContext = context;
        this.restaurants = restaurants;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            restaurantListFragment[0] = RestaurantListFragment.newInstance(1, restaurants);
            return restaurantListFragment[0];
        } else if (position == 1){
            restaurantListFragment[1] = RestaurantListFragment.newInstance(1, restaurants);
            return restaurantListFragment[1];
        } else {
            restaurantListFragment[2] = RestaurantListFragment.newInstance(1, restaurants);
            return restaurantListFragment[2];
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return "Restaurants";
            case 1:
                return "Recommended";
            case 2:
                return "Favourites";
            default:
                return null;
        }
    }

}
