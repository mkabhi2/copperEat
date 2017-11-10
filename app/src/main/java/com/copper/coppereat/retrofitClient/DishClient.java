package com.copper.coppereat.retrofitClient;

import com.copper.coppereat.customObjects.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by vijaysingh on 11/6/2017.
 */

public interface DishClient {

    @GET("/messenger/webapi/restrauntdish/dishget")
    Call<List<Dish>> getDishes();

    @POST("/messenger/webapi/restrauntdish/dishdetails")
    Call<List<Dish>> getDishDetails(@Body Dish dish);

    @POST("/messenger/webapi/restrauntdish")
    Call<List<Dish>> getDishCategory(@Body Dish dish);

}
