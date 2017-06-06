package com.example.gidro.myapplication.mvp.notes;

import com.example.gidro.myapplication.api.service.ApiService;
import com.example.gidro.myapplication.api.service.LoginService;

import retrofit2.Callback;

/**
 * Created by Gidro on 02.06.2017.
 */

public class NotesModel {

    public void getModelList(String email, Callback callback){

        ApiService.getInstance().create(LoginService.class).getNotes(email).enqueue(callback);

    }

}
