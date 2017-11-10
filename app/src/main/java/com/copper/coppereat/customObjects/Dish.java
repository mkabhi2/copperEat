package com.copper.coppereat.customObjects;

/**
 * Created by vijaysingh on 11/6/2017.
 */


public class Dish {

    private String restrauntID;
    private long dishID;
    private String dishName;
    private String dishPhoto;
    private String dishPrice;
    private String dishQuantity;
    private String dishType;
    private String dishAvailabilityTimmingSlot1;
    private String dishAvailabilityTimmingSlot2;
    private String dishAvailabilityTimmingSlot3;
    private String dishAvailabilityTimmingSlot4;
    private String dishAvailabilityFullDay;

    public Dish() {

    }

    public Dish(String restrauntID){
        this.restrauntID=restrauntID;
    }

    public Dish(long dishID, String dishName, String dishPhoto, String dishPrice, String dishQuantity,
                String dishType, String dishAvailabilityTimmingSlot1, String dishAvailabilityTimmingSlot2,
                String dishAvailabilityTimmingSlot3, String dishAvailabilityTimmingSlot4, String dishAvailabilityFullDay) {
        this.dishAvailabilityFullDay = dishAvailabilityFullDay;
        this.dishAvailabilityTimmingSlot1 = dishAvailabilityTimmingSlot1;
        this.dishAvailabilityTimmingSlot2 = dishAvailabilityTimmingSlot2;
        this.dishAvailabilityTimmingSlot3 = dishAvailabilityTimmingSlot3;
        this.dishAvailabilityTimmingSlot4 = dishAvailabilityTimmingSlot4;
        this.dishName = dishName;
        this.dishID = dishID;
        this.dishPhoto = dishPhoto;
        this.dishPrice = dishPrice;
        this.dishType = dishType;
        this.dishQuantity = dishQuantity;

    }

    public long getDishID() {
        return dishID;
    }

    public void setDishID(long dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPhoto() {
        return dishPhoto;
    }

    public void setDishPhoto(String dishPhoto) {
        this.dishPhoto = dishPhoto;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        this.dishQuantity = dishQuantity;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getDishAvailabilityTimmingSlot1() {
        return dishAvailabilityTimmingSlot1;
    }

    public void setDishAvailabilityTimmingSlot1(String dishAvailabilityTimmingSlot1) {
        this.dishAvailabilityTimmingSlot1 = dishAvailabilityTimmingSlot1;
    }

    public String getDishAvailabilityTimmingSlot2() {
        return dishAvailabilityTimmingSlot2;
    }

    public void setDishAvailabilityTimmingSlot2(String dishAvailabilityTimmingSlot2) {
        this.dishAvailabilityTimmingSlot2 = dishAvailabilityTimmingSlot2;
    }

    public String getDishAvailabilityTimmingSlot3() {
        return dishAvailabilityTimmingSlot3;
    }

    public void setDishAvailabilityTimmingSlot3(String dishAvailabilityTimmingSlot3) {
        this.dishAvailabilityTimmingSlot3 = dishAvailabilityTimmingSlot3;
    }

    public String getDishAvailabilityTimmingSlot4() {
        return dishAvailabilityTimmingSlot4;
    }

    public void setDishAvailabilityTimmingSlot4(String dishAvailabilityTimmingSlot4) {
        this.dishAvailabilityTimmingSlot4 = dishAvailabilityTimmingSlot4;
    }

    public String getDishAvailabilityFullDay() {
        return dishAvailabilityFullDay;
    }

    public void setDishAvailabilityFullDay(String dishAvailabilityFullDay) {
        this.dishAvailabilityFullDay = dishAvailabilityFullDay;
    }

    public String getRestrauntID() {
        return restrauntID;
    }

    public void setRestrauntID(String restrauntID) {
        this.restrauntID = restrauntID;
    }
}
