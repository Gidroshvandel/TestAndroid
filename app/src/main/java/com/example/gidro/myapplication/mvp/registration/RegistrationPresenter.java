package com.example.gidro.myapplication.mvp.registration;

import android.text.TextUtils;

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

    public RegistrationPresenter(RegistrationContract.View view, RegistrationViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;
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
            model.registration(viewModel.getName(), viewModel.getEmail(), viewModel.getPassword(), new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    view.hideProgress();
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                    view.hideProgress();
                }
            });
            view.showNotes();
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
            view.showPasswordValue();
            viewModel.setShowPassword(false);
        }
        else {
            view.hidePasswordValue();
            viewModel.setShowPassword(true);
        }

    }

    private boolean isFilled(){
        return  !(TextUtils.isEmpty(viewModel.getEmail()) && TextUtils.isEmpty(viewModel.getName()) && TextUtils.isEmpty(viewModel.getName()));
    }

}
