package com.example.gidro.myapplication.mvp.notes.details;

import com.example.gidro.myapplication.model.Note;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Gidro on 07.06.2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;
    private DetailsViewModel viewModel;
    private DetailsModel model;

    public DetailsPresenter(DetailsContract.View view, DetailsViewModel viewModel, Note note) {
        this.view = view;
        this.viewModel = viewModel;
        this.viewModel.setNote(note);
    }

    @Override
    public void onViewCreate() {
        view.showNoteDetailsInformation(viewModel.getNote().getDetails());
        view.showNoteHeader(viewModel.getNote().getHeader());
        view.showNotePicture(viewModel.getNote().getPictureURL());
        view.showNotePriority(viewModel.getNote().getPriority());
    }

    @Override
    public void onNoteHeaderChange(String noteHeader) {
        viewModel.getNote().setHeader(noteHeader);
    }

    @Override
    public void onNoteDetailsInformationChange(String noteDetails) {
        viewModel.getNote().setDetails(noteDetails);
    }

    @Override
    public void onNoteDeleteClick() {
        view.showNotes(viewModel.getNote(), 1);

    }

    @Override
    public void onNoteBackClick() {
        view.showDialogSave();
    }

    @Override
    public void onNotePriorityClick() {
       if(viewModel.getNote().getPriority() + 1 > 3){
           viewModel.getNote().setPriority(1);
       }
       else{
           viewModel.getNote().setPriority(viewModel.getNote().getPriority() + 1);
       }
       view.showNotePriority(viewModel.getNote().getPriority());
    }

    @Override
    public void onDialogSaveOkClick() {
        view.showNotes(viewModel.getNote(), RESULT_OK);
    }

    @Override
    public void onDialogSaveCancelClick() {
        view.showNotes(null, RESULT_CANCELED);
    }

    @Override
    public void onBackPressed() {
        view.showDialogSave();
    }
}
