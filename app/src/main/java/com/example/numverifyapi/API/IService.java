package com.example.numverifyapi.API;


import com.example.numverifyapi.Pojo.MobileModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {
    String BASE_URL = "http://apilayer.net/api/";
    String API_KEY = "47b9a8d3870e853280f81164289a6600";

    @GET("validate")
    public Call<MobileModel> getPhone(@Query("access_key")String key, @Query("number") String number);
}
