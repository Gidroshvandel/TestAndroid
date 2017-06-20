package com.example.gidro.myapplication.mvp.notes;

import com.example.gidro.myapplication.model.Note;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Gidro on 02.06.2017.
 */

public class NotesPresenter implements NotesContract.Presenter {

    private NotesContract.View view;
    private NotesViewModel viewModel;
    private NotesModel model;

    public NotesPresenter(NotesContract.View view, NotesViewModel viewModel, NotesModel model) {
        this.view = view;
        this.viewModel = viewModel;
        this.model = model;
    }

    @Override
    public void onViewCreate() {

        view.showProgress();

        model.getModelList(viewModel.getEmail(), new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ArrayList<Note> noteArrayList = ((ArrayList<Note>) response.body());
                viewModel.setNoteList(noteArrayList);
                view.showNoteElements(viewModel.getNoteList());
                view.hideProgress();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                view.hideProgress();
            }
        });

    }

    @Override
    public void onListElementClick(Note note, int noteId) {
        view.showNoteElementDetails(note, noteId);
    }

    @Override
    public void onResultEdit(Note note, int noteId) {
        viewModel.getNoteList().set(noteId, note);
        view.showNoteElements(viewModel.getNoteList());
    }

    @Override
    public void onResultCancel() {
        view.showNoteElements(viewModel.getNoteList());
    }

    @Override
    public void onResultDelete(int noteId) {
        viewModel.getNoteList().remove(noteId);
        view.showNoteElements(viewModel.getNoteList());
    }


}
