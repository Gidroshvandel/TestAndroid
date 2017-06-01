package com.example.gidro.myapplication.mvp.registration;

/**
 * Created by Gidro on 30.05.2017.
 */

public class RegistrationViewModel {

    private String Name;
    private String Email;
    private String Password;

    private boolean isShowPassword;

    public boolean isShowPassword() {
        return isShowPassword;
    }

    public void setShowPassword(boolean showPassword) {
        isShowPassword = showPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
