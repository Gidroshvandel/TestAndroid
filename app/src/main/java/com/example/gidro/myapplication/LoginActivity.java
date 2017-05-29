package com.example.gidro.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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

    Button btnLogin;
    Button btnRegistration;
    Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.button_login);
        btnRegistration = (Button) findViewById(R.id.button_registration);
        btnSwitch = (Button) findViewById(R.id.button_switch);

        btnSwitch.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                EditText password_text = (EditText) findViewById(R.id.editTextPassword);

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        password_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        btnSwitch.setText(R.string.text_show);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        password_text.setInputType(InputType.TYPE_CLASS_TEXT);
                        btnSwitch.setText(R.string.text_hide);
                        break;

                }
                return true;
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

    public boolean onTouch(View v, MotionEvent event) {

        EditText password_text = (EditText) findViewById(R.id.editTextPassword);

        switch ( event.getAction() ) {

            case MotionEvent.ACTION_UP:
                password_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;

            case MotionEvent.ACTION_DOWN:
                password_text.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

        }
        return true;
    }

    private void sendNetworkRequest(User user) {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        // add your other interceptors …
//
//        // add logging as last interceptor
//        httpClient.addInterceptor(logging);  // <-- this is the important line!


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2525/api/")
                .addConverterFactory(GsonConverterFactory.create());
//                .client(httpClient.build());

        Retrofit retrofit = builder.build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<User> call = apiService.login(user.getEmail(), user.getPassword());
//        Call<User> call = apiService.login(user);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

//                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//                startActivity(intent);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

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
