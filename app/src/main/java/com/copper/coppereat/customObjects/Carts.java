package com.copper.coppereat.customObjects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kumarpallav on 22/11/17.
 */

public class Carts {

    public static HashMap<Dish,Integer> cartList=new HashMap<Dish,Integer>();

    Carts(){

    }

    public static HashMap<Dish,Integer> getCartList(){
        return cartList;
    }

    public static void addItemsToCarts(String text){

       Dish dish= parseText(text);
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().equals(dish.getDishName())){
                int quantity=cartList.get(d);
                cartList.put(d,quantity+1);
                return;
            }
        }
        cartList.put(dish,1);
    }

    public static Dish parseText(String text){

    String dishName=text.substring(0,text.indexOf("Rs")).trim();
    String cost=text.substring(text.indexOf("Rs")+2).trim();
    Dish dish=new Dish(dishName,cost);
    return dish;
    }

    public static boolean deleteItemsFromCarts(String text){
        Dish dish=parseText(text);
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().equals(dish.getDishName())){
                int quantity=cartList.get(d);
                if(quantity==1) {
                    cartList.remove(d);
                    return false;
                }else {
                    cartList.put(d,quantity-1);
                    return  true;
                }
            }
        }
        return false;
    }

    public static int getQuantityForSelectedItem(String text){
        Dish dish=parseText(text);
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().equals(dish.getDishName()))
                return cartList.get(d);
            }
            return 0;
        }

    public static String getDishNameByPosition(int position){
        int count=0;
        for (Dish d:cartList.keySet()) {
            if(count==position){
                return d.getDishName();
            }
            count=count+1;
        }
        return "hello";
    }

    public static double getTotalDishPriceByPosition(String dishName){
        double price=0;
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().contains(dishName)){
                price=Double.parseDouble(d.getDishPrice());
                int quantity=cartList.get(d);
                return quantity*price;
            }
        }
        return 0;
    }

    public static void addItemsToCartsFromCheckoutPage(String dishName){
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().equals(dishName.trim())){
                int quantity=cartList.get(d);
                cartList.put(d,quantity+1);
                return;
            }
        }
    }

    public static void deletetemsToCartsFromCheckoutPage(String dishName){
        for (Dish d:cartList.keySet()) {
            if(d.getDishName().equals(dishName.trim())){
                int quantity=cartList.get(d);
                if(quantity==0){
                    cartList.remove(d);
                }else {
                    cartList.put(d, quantity - 1);
                }
                return;
            }
        }
    }


}
