package com.example.gidro.myapplication.mvp.registration;

import com.example.gidro.myapplication.api.service.ApiService;
import com.example.gidro.myapplication.api.service.LoginService;

import retrofit2.Callback;

/**
 * Created by Gidro on 30.05.2017.
 */

public class RegistrationModel {

    public void registration(String name, String email, String password, Callback callback){

        ApiService.getInstance().create(LoginService.class).registration(name, email, password).enqueue(callback);

    }

}
