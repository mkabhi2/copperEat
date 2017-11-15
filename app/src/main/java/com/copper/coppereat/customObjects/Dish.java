package com.copper.coppereat.customObjects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abhmishr on 11/3/17.
 */

public class Dish implements Parcelable{

    int dishID;
    int restaurantID;
    String budget;
    String dishName;
    String posterLink;
    String offer;
    String rating;
    int numOfRatings;
    int totalOrders;


    public Dish(){}


    public Dish(int dishID, int restaurantID, String budget, String dishName, String posterLink, String offer, String rating, int numOfRatings, int totalOrders) {
        this.dishID = dishID;
        this.restaurantID = restaurantID;
        this.budget = budget;
        this.dishName = dishName;
        this.posterLink = posterLink;
        this.offer = offer;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.totalOrders = totalOrders;
    }


    //Functions to implememt parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(dishID);
        parcel.writeInt(restaurantID);
        parcel.writeString(budget);
        parcel.writeString(dishName);
        parcel.writeString(posterLink);
        parcel.writeString(offer);
        parcel.writeString(rating);
        parcel.writeInt(numOfRatings);
        parcel.writeInt(totalOrders);
    }

    public static final Parcelable.Creator<Dish> CREATOR = new Parcelable.Creator<Dish>() {

        @Override
        public Dish createFromParcel(Parcel parcel) {
            return new Dish(parcel);
        }

        public Dish[] newArray(int size){
            return new Dish[size];
        }
    };

    private Dish(Parcel in){
        dishID = in.readInt();
        restaurantID = in.readInt();
        budget = in.readString();
        dishName = in.readString();
        posterLink = in.readString();
        offer = in.readString();
        rating = in.readString();
        numOfRatings = in.readInt();
        totalOrders = in.readInt();
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
