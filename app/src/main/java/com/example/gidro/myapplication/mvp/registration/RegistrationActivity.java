package com.example.gidro.myapplication.mvp.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gidro.myapplication.CustomEditText;
import com.example.gidro.myapplication.LoginActivity;
import com.example.gidro.myapplication.R;
import com.example.gidro.myapplication.mvp.notes.NotesActivity;

/**
 * Created by Gidro on 26.05.2017.
 */

public class RegistrationActivity extends Activity implements RegistrationContract.View {

    private RegistrationContract.Presenter presenter;

    private EditText nameText;
    private EditText emailText;
    private CustomEditText passwordCustomText;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder dialog;

    private View.OnClickListener oclBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // по id определеяем кнопку, вызвавшую этот обработчик
            switch (v.getId()) {
                case R.id.button_login:
                    presenter.onLoginClick();
                    break;
                case R.id.button_next:
                    presenter.onNextClick();
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        presenter = new RegistrationPresenter(this, new RegistrationViewModel(), new RegistrationModel());

        initUi();

        presenter.onViewCreate();
    }

    private void initUi(){
        dialog = new AlertDialog.Builder(this);

        Button loginBtn = (Button) findViewById(R.id.button_login);
        loginBtn.setOnClickListener(oclBtn);

        Button nextBtn = (Button) findViewById(R.id.button_next);
        nextBtn.setOnClickListener(oclBtn);

        nameText = (EditText) findViewById(R.id.editTextName);
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                presenter.onNameChange(s.toString());
            }
        });

        emailText = (EditText) findViewById(R.id.editTextEmail);
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                presenter.onEmailChange(s.toString());
            }
        });

        passwordCustomText = (CustomEditText) findViewById(R.id.editTextPassword);
        passwordCustomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                presenter.onPasswordChange(s.toString());
            }
        });
        passwordCustomText.setRightDrawableClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPasswordVisibilityChange();
            }
        });
    }

    @Override
    public void showNotes() {
        startActivity(new Intent(RegistrationActivity.this, NotesActivity.class));
    }

    @Override
    public void showLogin() {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    @Override
    public void showPasswordValue() {
        passwordCustomText.setInputType(InputType.TYPE_CLASS_TEXT);
        passwordCustomText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_eye_close, 0);
    }

    @Override
    public void hidePasswordValue() {
        passwordCustomText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordCustomText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_eye_open, 0);
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
    public void showDialogErr() {
        dialog.setMessage(R.string.text_reg_dialog_err)
                .setNegativeButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        dialog.show();
    }

    @Override
    public void showDialogOk() {
        dialog.setMessage(R.string.text_reg_dialog_ok)
                .setNegativeButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        presenter.onDialogOkClick();
                    }
                });
        dialog.show();
    }
}
