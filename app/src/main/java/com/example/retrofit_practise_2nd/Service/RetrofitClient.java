package com.example.retrofit_practise_2nd.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    APIInterface service;
    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIInterface.class);
    }

    public static RetrofitClient getInstance(){
        return new RetrofitClient();
    }

    public APIInterface getAPIService(){
        return service;
    }
}
