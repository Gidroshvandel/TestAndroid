package com.example.gidro.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gidro.myapplication.api.service.ApiService;
import com.example.gidro.myapplication.api.model.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegistration;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegistration = (Button) findViewById(R.id.button_registration);

        passwordText  = (EditText) findViewById(R.id.editTextPassword);

        passwordText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;
                if(event.getRawX() >= (passwordText.getRight() - passwordText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                    switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        passwordText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_eye_open, 0);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        passwordText.setInputType(InputType.TYPE_CLASS_TEXT);
                        passwordText.setCompoundDrawablesWithIntrinsicBounds( 0, 0, R.drawable.ic_eye_close, 0);
                        break;

                        // your action here
//                        Toast.makeText(LoginActivity.this, "абвгдейка", Toast.LENGTH_SHORT).show();

                    }
                    return true;
                }
                else{
                    return false;
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

        EditText email_text = (EditText) findViewById(R.id.editTextLogin);

        EditText password_text = (EditText) findViewById(R.id.editTextPassword);

        User user = new User( email_text.getText().toString(), password_text.getText().toString() );

        sendNetworkRequest(user);

    }
    private void onClickRegistration(View view) {

        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);

    }

    private void sendNetworkRequest(User user) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2525/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build());

        Retrofit retrofit = builder.build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<User> call = apiService.login(user.getEmail(), user.getPassword());

        final ProgressDialog mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(getResources().getString(R.string.text_progress_dialog));
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        call.enqueue(new Callback<User>() {

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
                // Create the AlertDialog object and return it
//                Toast.makeText(LoginActivity.this, R.string.text_error_login, Toast.LENGTH_SHORT).show();

            }
        });

    }

}
