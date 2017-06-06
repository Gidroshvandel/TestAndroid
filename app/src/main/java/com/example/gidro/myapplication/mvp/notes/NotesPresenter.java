package com.example.gidro.myapplication.mvp.notes;

import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.model.NoteList;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//        view.showNoteElementDetails();

        view.showProgress();

        model.getModelList(viewModel.getEmail(), new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        NoteList noteArrayList = ((NoteList) response.body());
                        view.showNoteElementDetails(noteArrayList.getNoteList());
                        view.hideProgress();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        view.hideProgress();
                    }
                });

    }

    @Override
    public void onListElementClick() {

    }
}
