package com.copper.coppereat;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.copper.coppereat.customObjects.Dish;
import com.copper.coppereat.retrofitClient.DishClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kumarpallav on 11/11/17.
 */

public class BackgroundService extends IntentService {
    static List<Dish> dishList;

    public BackgroundService(){
        super("BackgroundService");
    }


   // onSt

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://192.168.0.4:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit=builder.build();

        DishClient dishClient=retrofit.create(DishClient.class);
        Call<List<Dish>> call=dishClient.getDishes();
        try {
            Response<List<Dish>> result=call.execute();
            List<Dish> list=result.body();
            setDishDetails(list);
            Log.d("no error","success");
        }catch (Exception e){
            Log.d("error",e.toString());
        }

    }


    public void setDishDetails(List<Dish> list){
        dishList=list;
    }

    public List<Dish> getDishList(){
        return dishList;
    }
}
