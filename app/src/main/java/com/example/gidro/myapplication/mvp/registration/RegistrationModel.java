package com.example.gidro.myapplication.mvp.registration;

import com.example.gidro.myapplication.api.model.User;
import com.example.gidro.myapplication.api.service.ApiService;
import com.example.gidro.myapplication.api.service.LoginService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gidro on 30.05.2017.
 */

public class RegistrationModel {

    public void registration(String name, String email, String password, Callback callback){

        ApiService.getInstance().create(LoginService.class).registration(name, email, password).enqueue(callback);

    }

}
