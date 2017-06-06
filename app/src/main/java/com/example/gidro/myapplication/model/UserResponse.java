package com.example.gidro.myapplication.model;

/**
 * Created by Gidro on 02.06.2017.
 */

public class UserResponse {

    private User user;

    private Error error;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
