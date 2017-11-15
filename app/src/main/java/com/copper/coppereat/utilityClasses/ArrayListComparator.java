package com.copper.coppereat.utilityClasses;

import com.copper.coppereat.customObjects.Restaurant;

import java.util.Comparator;

/**
 * Created by abhmishr on 11/4/17.
 */

public class ArrayListComparator implements Comparator<Object> {

    String sortValue;

    public ArrayListComparator(String sortValue){
        this.sortValue = sortValue;
    }

    @Override
    public int compare(Object object, Object t1) {
        if(object instanceof Restaurant && t1 instanceof Restaurant) {
            Restaurant r1 = (Restaurant) t1;
            Restaurant restaurant = (Restaurant) object;

            switch (sortValue){
                //TODO IMPLEMENT OTHER SORTING PARAMETERS
                case "Ratings"      :   return (Math.round(Float.parseFloat(r1.getRating())-Float.parseFloat(restaurant.getRating())));

                case "Popularity"   :   //return (r1.getTotalOrders() - restaurant.getTotalOrders());
                    //TODO ASK FOR THIS TO VIJAY
                    return (Math.round(Float.parseFloat(r1.getRating())-Float.parseFloat(restaurant.getRating())));

                //TODO IMPLEMENT ALL AFTER IMPLEMENTING LOCATION
                case "all"          :   //return (r1.getTotalOrders() - restaurant.getTotalOrders());
                    //TODO ASK FOR THIS TO VIJAY
                    return (Math.round(Float.parseFloat(r1.getRating())-Float.parseFloat(restaurant.getRating())));
            }
        }
        return 0;
    }
}

