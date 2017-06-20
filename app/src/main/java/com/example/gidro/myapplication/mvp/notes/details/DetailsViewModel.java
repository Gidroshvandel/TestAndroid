package com.example.gidro.myapplication.mvp.notes.details;

import com.example.gidro.myapplication.model.Note;

/**
 * Created by Gidro on 07.06.2017.
 */

public class DetailsViewModel {

    private Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
