package com.copper.coppereat.customObjects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by abhmishr on 11/3/17.
 */

public class Restaurant implements Parcelable{

    int restaurantID;
    String budget;
    String restaurantName;
    String restaurantEmail;
    String restaurantAddress;
    String restaurantPassword;
    String restaurantTimmings;
    String restaurantDishTableName;
    String posterLink;
    String speciality;
    String deliveryDetails;
    String offer;
    String rating;
    int numOfRatings;
    int pinCode;
    int totalOrders;
    ArrayList<String> dishTypes;


    public Restaurant(){}


    public Restaurant(int restaurantID, String budget, String restaurantName, String restaurantEmail, String restaurantAddress, String restaurantPassword, String restaurantTimmings, String restaurantDishTableName, String posterLink, String speciality, String deliveryDetails, String offer, String rating, int numOfRatings, int pinCode, ArrayList<String> dishTypes) {
        this.restaurantID = restaurantID;
        this.budget = budget;
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPassword = restaurantPassword;
        this.restaurantTimmings = restaurantTimmings;
        this.restaurantDishTableName = restaurantDishTableName;
        this.posterLink = posterLink;
        this.speciality = speciality;
        this.deliveryDetails = deliveryDetails;
        this.offer = offer;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.pinCode = pinCode;
        this.dishTypes = dishTypes;
    }


    //Functions to implememt parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(restaurantID);
        parcel.writeString(budget);
        parcel.writeString(restaurantName);
        parcel.writeString(restaurantEmail);
        parcel.writeString(restaurantAddress);
        parcel.writeString(restaurantPassword);
        parcel.writeString(restaurantTimmings);
        parcel.writeString(restaurantDishTableName);
        parcel.writeString(posterLink);
        parcel.writeString(speciality);
        parcel.writeString(deliveryDetails);
        parcel.writeString(offer);
        parcel.writeString(rating);
        parcel.writeInt(numOfRatings);
        parcel.writeInt(pinCode);
        parcel.writeStringList(dishTypes);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR = new Parcelable.Creator<Restaurant>() {

        @Override
        public Restaurant createFromParcel(Parcel parcel) {
            return new Restaurant(parcel);
        }

        public Restaurant[] newArray(int size){
            return new Restaurant[size];
        }
    };

    private Restaurant(Parcel in){
        restaurantID = in.readInt();
        budget = in.readString();
        restaurantName = in.readString();
        restaurantEmail = in.readString();
        restaurantAddress = in.readString();
        restaurantPassword = in.readString();
        restaurantTimmings = in.readString();
        restaurantDishTableName = in.readString();
        posterLink = in.readString();
        speciality = in.readString();
        deliveryDetails = in.readString();
        offer = in.readString();
        rating = in.readString();
        numOfRatings = in.readInt();
        pinCode = in.readInt();
        dishTypes = in.createStringArrayList();
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantPassword() {
        return restaurantPassword;
    }

    public void setRestaurantPassword(String restaurantPassword) {
        this.restaurantPassword = restaurantPassword;
    }

    public String getRestaurantTimmings() {
        return restaurantTimmings;
    }

    public void setRestaurantTimmings(String restaurantTimmings) {
        this.restaurantTimmings = restaurantTimmings;
    }

    public String getRestaurantDishTableName() {
        return restaurantDishTableName;
    }

    public void setRestaurantDishTableName(String restaurantDishTableName) {
        this.restaurantDishTableName = restaurantDishTableName;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(String deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
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

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public ArrayList<String> getDishTypes() {
        return dishTypes;
    }

    public void setDishTypes(ArrayList<String> dishTypes) {
        this.dishTypes = dishTypes;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }
}
