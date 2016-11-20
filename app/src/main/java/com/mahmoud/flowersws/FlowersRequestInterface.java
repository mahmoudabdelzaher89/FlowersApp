package com.mahmoud.flowersws;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by HP Pro 3500 on 16/11/2016.
 */

public interface FlowersRequestInterface {
    @GET("feeds/flowers.json")
    Call<ArrayList<Flower>> getJson();
}
