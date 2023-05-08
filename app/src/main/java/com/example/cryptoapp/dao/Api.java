package com.example.cryptoapp.dao;

import com.example.cryptoapp.dao.asset.Asset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    Gson gson = new GsonBuilder().create();
    // http://localhost:8000/
    Api api = new Retrofit.Builder()
            .baseUrl("https://b84b-58-187-157-33.ap.ngrok.io/crypto_service/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Api.class);

    @GET("list")
    Call<ResponeData> getCryptos();

    @GET("assets")
    Call<ResponeAssets> getAssets(@Query("address") String address);

    @GET("detail")
    Call<ResponeDetail> getDetail(@Query("id") int id);
}
