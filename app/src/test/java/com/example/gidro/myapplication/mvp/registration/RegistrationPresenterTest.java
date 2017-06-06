package com.example.gidro.myapplication.mvp.registration;

import com.example.gidro.myapplication.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gidro on 30.05.2017.
 */
@RunWith(RobolectricTestRunner.class)
public class RegistrationPresenterTest {

    private RegistrationContract.Presenter presenter;

    @Mock
    private RegistrationContract.View view;

    @Mock
    private RegistrationViewModel viewModel;

    @Mock
    private RegistrationModel model;

    @Captor
    ArgumentCaptor<Callback<User>> argumentCaptor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new RegistrationPresenter(view, viewModel, model);
    }

    @Test
    public void onEmailChange(){

        presenter.onEmailChange(viewModel.getEmail());

    }

    @Test
    public void onLoginClick(){

        presenter.onLoginClick();
        verify(view).showLogin();

    }

    @Test
    public void onNameChange(){

        presenter.onNameChange(viewModel.getName());

    }

    @Test
    public void onNextClick_response(){

        when(viewModel.getName()).thenReturn("name");
        when(viewModel.getEmail()).thenReturn("email");
        when(viewModel.getPassword()).thenReturn("password");

        Call call = mock(Call.class);

        presenter.onNextClick();

        verify(view).showProgress();

        verify(model).registration(eq("name"), eq("email"), eq("password"), argumentCaptor.capture());

        argumentCaptor.getValue().onResponse(call, null);

        verify(view).hideProgress();
        verify(view).showDialogOk();

    }

    @Test
    public void onNextClick_fail(){

        when(viewModel.getName()).thenReturn("name");
        when(viewModel.getEmail()).thenReturn("email");
        when(viewModel.getPassword()).thenReturn("password");

        Call call = mock(Call.class);

        presenter.onNextClick();

        verify(view).showProgress();

        verify(model).registration(eq("name"), eq("email"), eq("password"), argumentCaptor.capture());

        argumentCaptor.getValue().onFailure(call, null);

        verify(view).hideProgress();
        verify(view).showDialogErr();

    }

    @Test
    public void onPasswordChange(){

        presenter.onPasswordChange(viewModel.getPassword());

    }

    @Test
    public void onPasswordVisibilityChange_hideValue(){
        when(viewModel.isShowPassword()).thenReturn(true);

        presenter.onPasswordVisibilityChange();

        verify(view).hidePasswordValue();
        verify(viewModel).setShowPassword(false);
    }
    @Test
    public void onPasswordVisibilityChange_showValue(){
        when(viewModel.isShowPassword()).thenReturn(false);

        presenter.onPasswordVisibilityChange();

        verify(view).showPasswordValue();
        verify(viewModel).setShowPassword(true);
    }

    @Test
    public void onDialogOkClick(){

        presenter.onDialogOkClick();
        verify(view).showNotes();

    }

}
