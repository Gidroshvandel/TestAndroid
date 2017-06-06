package com.example.gidro.myapplication.mvp.notes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gidro.myapplication.R;
import com.example.gidro.myapplication.adapters.NotesAdapter;
import com.example.gidro.myapplication.model.Note;

import java.util.List;

/**
 * Created by Gidro on 29.05.2017.
 */

public class NotesActivity extends Activity implements NotesContract.View {

    private NotesContract.Presenter presenter;

    private NotesAdapter adapter;

    private ProgressDialog mProgressDialog;
    private RecyclerView notesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        presenter = new NotesPresenter(this, new NotesViewModel(), new NotesModel());

        notesRv = (RecyclerView)findViewById(R.id.rv_notes);
        notesRv.setLayoutManager(llm);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getResources().getString(R.string.text_progress_dialog));
        mProgressDialog.setCanceledOnTouchOutside(false);

        presenter.onViewCreate();

    }

    @Override
    public void showNoteElementDetails(List<Note> noteList) {

        adapter = new NotesAdapter(noteList);
        notesRv.setAdapter(adapter);
//        adapter.updateData(noteList);

    }

    @Override
    public void showProgress() {

        mProgressDialog.show();

    }

    @Override
    public void hideProgress() {

        mProgressDialog.hide();

    }
}
