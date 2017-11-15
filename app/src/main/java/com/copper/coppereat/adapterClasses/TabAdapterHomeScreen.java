package com.copper.coppereat.adapterClasses;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.copper.coppereat.RestaurantListFragment;
import com.copper.coppereat.FavouritesFragment;
import com.copper.coppereat.RecommendedRestaurantFragment;

/**
 * Created by abhmishr on 10/28/17.
 */

public class TabAdapterHomeScreen extends FragmentPagerAdapter {

    private Context mContext;
    public RestaurantListFragment restaurantListFragment;
    public RecommendedRestaurantFragment recommendedFragment;
    public FavouritesFragment favouritesFragment;

    public TabAdapterHomeScreen(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            restaurantListFragment = new RestaurantListFragment();
            return restaurantListFragment;
        } else if (position == 1){
            recommendedFragment = new RecommendedRestaurantFragment();
            return recommendedFragment;
        } else {
            favouritesFragment = new FavouritesFragment();
            return favouritesFragment;
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
                return "Restaurant";
            case 1:
                return "Recommended";
            case 2:
                return "Favourites";
            default:
                return null;
        }
    }

}
