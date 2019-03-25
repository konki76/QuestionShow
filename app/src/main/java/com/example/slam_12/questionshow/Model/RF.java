package com.example.slam_12.questionshow.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RF {
    private Gson gson;
    private Retrofit retrofit;
    private Json json;

    public RF(String url){
        gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
    }
    public Json getJson(){
        return this.retrofit.create(Json.class);
    }
}
