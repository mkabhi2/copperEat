package com.copper.coppereat.retrofitServiceGenerator;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.copper.coppereat.retrofitClient.DishClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kumarpallav on 12/11/17.
 */

public class DishServiceRetrofitGeneratorSynchronus extends IntentService{
    private static final String BASE_URL = "http://192.168.0.4:8080/";

    static OkHttpClient.Builder okhttpClientBuilder=new OkHttpClient.Builder();
    static HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okhttpClientBuilder.build());

    private static Retrofit retrofit = builder.build();



    public DishServiceRetrofitGeneratorSynchronus(){
        super("DishServiceRetrofitGeneratorSynchronus");
    }



    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(okhttpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        Retrofit.Builder builder=new Retrofit.Builder()
//                .baseUrl("http://192.168.0.4:8080/")
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit=builder.build();
//        DishClient dishClient=retrofit.create(DishClient.class);

    }

}
