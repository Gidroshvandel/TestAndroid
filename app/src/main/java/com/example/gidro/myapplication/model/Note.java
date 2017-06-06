package com.example.gidro.myapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gidro on 05.06.2017.
 */

public class Note {

    private String header;

    private int priority;

    public Note(String header, int priority) {
        this.header = header;
        this.priority = priority;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
