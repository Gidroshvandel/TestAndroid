package com.example.gidro.myapplication.mvp.registration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Gidro on 30.05.2017.
 */
@RunWith(RobolectricTestRunner.class)
public class RegistrationPresenterTest {

    private RegistrationContract.Presenter presenter;

    @Mock
    private RegistrationContract.View view;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new RegistrationPresenter(view);
    }

    @Test
    public void onEmailChange(){

        presenter.onEmailChange();

    }

    @Test
    public void onLoginClick(){

        presenter.onLoginClick();
        verify(view).showLogin();

    }

    @Test
    public void onNameChange(){

        presenter.onNameChange();

    }

    @Test
    public void onNextClick(){

        presenter.onNextClick();

        verify(view).showNotes();

    }

    @Test
    public void onPasswordChange(){

        presenter.onPasswordChange();

    }

    @Test
    public void onPasswordTouchDown(){

        presenter.onPasswordTouchDown();
        verify(view).showPasswordValue();

    }

    @Test
    public void onPasswordTouchUp(){

        presenter.onPasswordTouchUp();
        verify(view).showPasswordValue();

    }
}
