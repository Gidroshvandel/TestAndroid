package com.example.gidro.myapplication.api.model;

/**
 * Created by Gidro on 26.05.2017.
 */

public class User {

    private String Email;
    private String Password;

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}