package com.copper.coppereat.utilityClasses;

import com.copper.coppereat.customObjects.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhmishr on 11/3/17.
 */

public interface RetroFitNetworkClient {

    @GET("/messenger/webapi/restraunt/specificrest")
    Call<List<Restaurant>> getRestrauntListForLocation(@Query("pincode") String pincode);
}
