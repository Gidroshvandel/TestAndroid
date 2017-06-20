package com.example.gidro.myapplication.mvp.notes.details;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gidro.myapplication.R;
import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by Gidro on 07.06.2017.
 */

public class DetailsActivity extends Activity implements DetailsContract.View {

    private DetailsContract.Presenter presenter;

    private ImageView noteDetailsPriority;
    private ImageView noteDetailsPicture;
    private EditText editTextHeader;
    private Button noteDetailsDeleteBtn;
    private Button noteDetailsSaveBtn;
    private EditText editTextDetails;
    private AlertDialog.Builder dialog;

    private View.OnClickListener oclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // по id определеяем кнопку, вызвавшую этот обработчик
            switch (v.getId()) {
                case R.id.noteDetailsDelete:
                    presenter.onNoteDeleteClick();
                    break;
                case R.id.noteDetailsSave:
                    presenter.onNoteBackClick();
                    break;
                case R.id.noteDetailsPriority:
                    presenter.onNotePriorityClick();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);

        presenter = new DetailsPresenter(this, new DetailsViewModel(), (Note) getIntent().getExtras().get("note"));

        initUi();

        presenter.onViewCreate();
    }

    private void initUi(){
        dialog = new AlertDialog.Builder(this);

        noteDetailsPicture = (ImageView) findViewById(R.id.noteDetailsPicture);

        noteDetailsPriority = (ImageView) findViewById(R.id.noteDetailsPriority);
        noteDetailsPriority.setOnClickListener(oclBtn);

        noteDetailsDeleteBtn = (Button) findViewById(R.id.noteDetailsDelete);
        noteDetailsDeleteBtn.setOnClickListener(oclBtn);

        noteDetailsSaveBtn = (Button) findViewById(R.id.noteDetailsSave);
        noteDetailsSaveBtn.setOnClickListener(oclBtn);

        editTextHeader = (EditText) findViewById(R.id.editTextHeader);
        editTextHeader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                presenter.onNoteHeaderChange(s.toString());
            }
        });

        editTextDetails = (EditText) findViewById(R.id.editTextDetails);
        editTextDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                presenter.onNoteDetailsInformationChange(s.toString());
            }
        });
    }

    @Override
    public void showNoteHeader(String noteHeader) {
        editTextHeader.setText(noteHeader);
    }

    @Override
    public void showNoteDetailsInformation(String noteDetails) {
        editTextDetails.setText(noteDetails);
    }

    @Override
    public void showNotePicture(String url) {
        Picasso.with(this)
                .load(url)
                .resize(150, 150)
                .centerCrop()
                .into(noteDetailsPicture);
    }

    @Override
    public void showNotePriority(int notePriority) {
        noteDetailsPriority.setImageResource(Utils.numberToDrawableIdPriority(notePriority));
    }

    @Override
    public void showNotes(Note note, int resultCode) {
        Intent intent = new Intent();
        intent.putExtra("note", note);
        intent.putExtra("noteId", (int) getIntent().getExtras().get("noteId"));
        setResult(resultCode, intent);
        finish();
    }

    @Override
    public void showDialogSave() {
        dialog.setMessage(R.string.text_dialog_save);
        dialog.setPositiveButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        presenter.onDialogSaveOkClick();
                    }
                });
        dialog.setNegativeButton(R.string.text_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        presenter.onDialogSaveCancelClick();
                    }
                });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
