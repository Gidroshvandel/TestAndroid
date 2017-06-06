package com.example.gidro.myapplication.model;

import java.util.List;

/**
 * Created by Gidro on 06.06.2017.
 */

public class NoteList {

    List<Note> noteList;

    public NoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

}
