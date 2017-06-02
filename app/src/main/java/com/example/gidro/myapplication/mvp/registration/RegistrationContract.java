package com.example.gidro.myapplication.mvp.registration;

/**
 * Created by Gidro on 30.05.2017.
 */

public interface RegistrationContract {
    interface View {

        void showNotes();

        void showLogin();

        void showPasswordValue();

        void hidePasswordValue();

        void showProgress();

        void hideProgress();

        void showDialogErr();

        void showDialogOk();

    }
    interface Presenter {

        void onViewCreate();

        void onNextClick();

        void onLoginClick();

        void onNameChange(String name);

        void onEmailChange(String email);

        void onPasswordChange(String password);

        void onPasswordVisibilityChange();

        void onDialogErrClick();

        void onDialogOkClick();


    }
}
