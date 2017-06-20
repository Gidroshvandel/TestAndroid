package com.example.gidro.myapplication.mvp.notes.details;

import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.mvp.BasePresenter;

/**
 * Created by Gidro on 07.06.2017.
 */

public interface DetailsContract {
    interface View {

        void showNoteHeader(String noteHeader);

        void showNoteDetailsInformation(String noteDetails);

        void showNotePicture(String url);

        void showNotePriority(int notePriority);

        void showNotes(Note note, int resultCode);

        void showDialogSave();

    }
    interface Presenter extends BasePresenter {

        void onNoteHeaderChange(String noteHeader);

        void onNoteDetailsInformationChange(String noteDetails);

        void onNoteDeleteClick();

        void onNoteBackClick();

        void onNotePriorityClick();

        void onDialogSaveOkClick();

        void onDialogSaveCancelClick();

        void onBackPressed();

    }
}
