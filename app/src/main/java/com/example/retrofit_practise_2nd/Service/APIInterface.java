package com.example.retrofit_practise_2nd.Service;

import com.example.retrofit_practise_2nd.Service.Response.Detail;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("products")
    Call<Detail> getAllData();

}
