package com.example.gidro.myapplication.mvp.notes;

/**
 * Created by Gidro on 02.06.2017.
 */

public class NotesViewModel {

    private String Name;
    private String Email;
    private String Password;

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
