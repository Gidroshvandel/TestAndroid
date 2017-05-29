package com.example.gidro.myapplication.api.service;

import com.example.gidro.myapplication.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

//    @Headers({
//            "Accept: application/json",
//    })
//    @Multipart
//    @FormUrlEncoded
    @POST("user")
    Call<User> login(@Query("email") String email, @Query("password") String password);
//    Call<User> login(@Body User user);
}