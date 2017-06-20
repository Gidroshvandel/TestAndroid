package com.example.gidro.myapplication.mvp.notes;

import com.example.gidro.myapplication.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gidro on 02.06.2017.
 */

public interface NotesContract {
    interface View {

        void showNoteElementDetails(Note note, int noteId);

        void showNoteElements(ArrayList<Note> noteList);

        void showProgress();

        void hideProgress();

    }
    interface Presenter {

        void onViewCreate();

        void onListElementClick(Note note, int noteId);

        void onResultEdit(Note note, int noteId);

        void onResultCancel();

        void onResultDelete(int noteId);

    }
}
