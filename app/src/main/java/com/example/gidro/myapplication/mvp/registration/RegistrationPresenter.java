package com.example.gidro.myapplication.mvp.registration;

import android.text.TextUtils;

import com.example.gidro.myapplication.api.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gidro on 30.05.2017.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;
    private RegistrationViewModel viewModel;
    private RegistrationModel model;

    public RegistrationPresenter(RegistrationContract.View view, RegistrationViewModel viewModel, RegistrationModel model) {
        this.view = view;
        this.viewModel = viewModel;
        this.model = model;
    }

    @Override
    public void onViewCreate() {

        viewModel.setShowPassword(false);
        view.hidePasswordValue();

    }

    @Override
    public void onNextClick() {
        if (isFilled()){
            view.showProgress();
            System.out.println("Значения моделей: "+viewModel.getName()+" "+viewModel.getEmail()+" "+viewModel.getPassword() );
            model.registration(viewModel.getName(), viewModel.getEmail(), viewModel.getPassword(), new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    view.hideProgress();
                    view.showDialogOk();
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    view.hideProgress();
                    view.showDialogErr();
                }
            });
        }
    }

    @Override
    public void onLoginClick() {
        view.showLogin();
    }

    @Override
    public void onNameChange(String name) {
        viewModel.setName(name);
    }

    @Override
    public void onEmailChange(String email) {
        viewModel.setEmail(email);
    }

    @Override
    public void onPasswordChange(String password) {
        viewModel.setPassword(password);
    }


    @Override
    public void onPasswordVisibilityChange() {

        if (viewModel.isShowPassword() ){
            view.hidePasswordValue();
            viewModel.setShowPassword(false);
        }
        else {
            view.showPasswordValue();
            viewModel.setShowPassword(true);
        }

    }

    @Override
    public void onDialogErrClick() {

    }

    @Override
    public void onDialogOkClick() {

        view.showNotes();

    }

    private boolean isFilled(){
        return  !(TextUtils.isEmpty(viewModel.getName()) || TextUtils.isEmpty(viewModel.getEmail()) || TextUtils.isEmpty(viewModel.getPassword()));
    }
}
