package com.example.gidro.myapplication.mvp.notes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.gidro.myapplication.LoginActivity;
import com.example.gidro.myapplication.R;
import com.example.gidro.myapplication.adapters.NotesAdapter;
import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.mvp.notes.details.DetailsActivity;
import com.example.gidro.myapplication.mvp.registration.RegistrationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gidro on 29.05.2017.
 */

public class NotesActivity extends Activity implements NotesContract.View, NotesAdapter.Callback {

    private NotesContract.Presenter presenter;

    private NotesAdapter adapter;

    private ProgressDialog mProgressDialog;
    private RecyclerView notesRv;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        presenter = new NotesPresenter(this, new NotesViewModel(), new NotesModel());

        llm = new LinearLayoutManager(this);
        
        notesRv = (RecyclerView) findViewById(R.id.rv_notes);
        notesRv.setLayoutManager(llm);

        presenter.onViewCreate();

    }

    @Override
    public void showNoteElementDetails(Note note, int noteId) {
        Intent intent = new Intent(NotesActivity.this, DetailsActivity.class);
        intent.putExtra("note", note);
        intent.putExtra("noteId", noteId);
        startActivityForResult(intent, 1);
    }

    @Override
    public void showNoteElements(ArrayList<Note> noteList) {
        adapter = new NotesAdapter(noteList, this);
        notesRv.setAdapter(adapter);
    }

    @Override
    public void showProgress() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getResources().getString(R.string.text_progress_dialog));
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

    }

    @Override
    public void hideProgress() {

        mProgressDialog.dismiss();

    }

    @Override
    public void onNoteClick(Note note, int noteId) {
        presenter.onListElementClick(note, noteId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        switch (resultCode) {
            case RESULT_OK:
                presenter.onResultEdit((Note) data.getExtras().get("note"), (int) data.getExtras().get("noteId"));
                break;
            case RESULT_CANCELED:
                presenter.onResultCancel();
                break;
            case 1:
                presenter.onResultDelete((int) data.getExtras().get("noteId"));
                break;
        }
    }
}
