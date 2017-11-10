package com.copper.coppereat.retrofitServiceGenerator;

import com.copper.coppereat.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vijaysingh on 11/6/2017.
 */

public class DishServiceGeneratorRetrofit {
    private static final String BASE_URL = "http://192.168.0.7:8034/";

    static OkHttpClient.Builder okhttpClientBuilder=new OkHttpClient.Builder();
    static HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();




    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okhttpClientBuilder.build());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(BuildConfig.DEBUG) {
            okhttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        return retrofit.create(serviceClass);
    }
}
