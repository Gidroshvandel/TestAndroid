package com.example.gidro.myapplication.api.service;

import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.model.NoteList;
import com.example.gidro.myapplication.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    @POST("login")
    Call<User> login(@Query("email") String email, @Query("password") String password);
    @POST("registration")
    Call<User> registration(@Query("name") String name, @Query("email") String email, @Query("password") String password);
    @GET("getNotes")
    Call<NoteList> getNotes(@Query("email") String email);

}