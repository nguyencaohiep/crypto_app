package com.example.cryptoapp.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    Gson gson = new GsonBuilder().create();
    // http://localhost:8000/
    Api api = new Retrofit.Builder()
            .baseUrl("https://2a8d-58-187-157-33.ap.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api.class);

    @GET("crypto_service/list")
    Call<ResponeData> getCryptos();
}
