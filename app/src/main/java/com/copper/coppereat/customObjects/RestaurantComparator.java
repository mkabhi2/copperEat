package com.copper.coppereat.customObjects;

import java.util.Comparator;

/**
 * Created by abhmishr on 11/4/17.
 */

public class RestaurantComparator implements Comparator<Restaurants> {

    String sortValue;

    public RestaurantComparator(String sortValue){
        this.sortValue = sortValue;
    }

    @Override
    public int compare(Restaurants restaurants, Restaurants t1) {
        switch (sortValue){
            //TODO IMPLEMENT OTHER SORTING PARAMETERS
            case "Ratings" : return (Math.round(Float.parseFloat(t1.getRating())-Float.parseFloat(restaurants.getRating())));
        }
        return 0;
    }
}

