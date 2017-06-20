package com.example.gidro.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.gidro.myapplication.api.service.ApiService;
import com.example.gidro.myapplication.api.service.LoginService;
import com.example.gidro.myapplication.model.User;
import com.example.gidro.myapplication.mvp.notes.NotesActivity;
import com.example.gidro.myapplication.mvp.registration.RegistrationActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private CustomEditText passwordText;

    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.button_login);
        Button btnRegistration = (Button) findViewById(R.id.button_registration);

        passwordText = (CustomEditText) findViewById(R.id.editTextPassword);

        flag = false;

        passwordText.setRightDrawableClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_open, 0);
                    flag = false;
                }
                else {
                    passwordText.setInputType(InputType.TYPE_CLASS_TEXT);
                    passwordText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_close, 0);
                    flag = true;
                }
            }
        });

        // создание обработчика
        OnClickListener oclBtn = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // по id определеяем кнопку, вызвавшую этот обработчик
                switch (v.getId()) {
                    case R.id.button_login:
                        onClickLogin(v);
                        break;
                    case R.id.button_registration:
                        onClickRegistration(v);
                        break;
                }

            }
        };

        btnLogin.setOnClickListener(oclBtn);
        btnRegistration.setOnClickListener(oclBtn);
    }

    private void onClickLogin(View view) {

        EditText emailText = (EditText) findViewById(R.id.editTextLogin);

        EditText passwordText = (EditText) findViewById(R.id.editTextPassword);

        User user = new User(emailText.getText().toString(), passwordText.getText().toString());

        sendNetworkRequest(user);

    }

    private void onClickRegistration(View view) {

        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }

    private void sendNetworkRequest(User user) {

        final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getResources().getString(R.string.text_progress_dialog));
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        ApiService.getInstance().create(LoginService.class).login(user.getEmail(), user.getPassword()).enqueue( new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mProgressDialog.hide();
                Intent intent = new Intent(LoginActivity.this, NotesActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressDialog.hide();
                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage(R.string.text_error_login)
                        .setNegativeButton(R.string.text_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                EditText email_text = (EditText) findViewById(R.id.editTextLogin);

                                EditText password_text = (EditText) findViewById(R.id.editTextPassword);

                                email_text.setText("");
                                password_text.setText("");
                            }
                        });
                builder.show();

            }
        });

    }

}
