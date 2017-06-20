package com.example.gidro.myapplication.mvp.notes;

import com.example.gidro.myapplication.model.Note;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gidro on 20.06.2017.
 */
@RunWith(RobolectricTestRunner.class)
public class NotesPresenterTest {
    private NotesContract.Presenter presenter;

    @Mock
    private NotesContract.View view;

    @Mock
    private NotesViewModel viewModel;

    @Mock
    private NotesModel model;

    @Captor
    ArgumentCaptor<Callback<ArrayList<Note>>> argumentCaptor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new NotesPresenter(view, viewModel, model);
    }

    @Test
    public void onViewCreate_response(){
        when(viewModel.getName()).thenReturn("name");
        when(viewModel.getEmail()).thenReturn("email");
        when(viewModel.getPassword()).thenReturn("password");

        Call call = mock(Call.class);

        presenter.onViewCreate();

        verify(view).showProgress();

        verify(model).getModelList(eq("email"), argumentCaptor.capture());

        ArrayList<Note> noteList = new ArrayList<>();

        argumentCaptor.getValue().onResponse(call, Response.success(noteList));

        verify(view).hideProgress();
    }

    @Test
    public void onViewCreate_fail(){
        when(viewModel.getName()).thenReturn("name");
        when(viewModel.getEmail()).thenReturn("email");
        when(viewModel.getPassword()).thenReturn("password");

        Call call = mock(Call.class);

        presenter.onViewCreate();

        verify(view).showProgress();

        verify(model).getModelList(eq("email"), argumentCaptor.capture());

        argumentCaptor.getValue().onFailure(call, null);

        verify(view).hideProgress();
    }

    @Test
    public void onListElementClick(){
        Note note = mock(Note.class);
        presenter.onListElementClick(note, 0);
        verify(view).showNoteElementDetails(note, 0);
    }

    @Test
    public void onResultEdit(){
        Note note = mock(Note.class);

        ArrayList<Note> arrayList = mock(ArrayList.class);
        when(viewModel.getNoteList()).thenReturn(arrayList);

        presenter.onResultEdit(note, 0);
        verify(view).showNoteElements(viewModel.getNoteList());
    }

    @Test
    public void onResultCancel(){
        presenter.onResultCancel();
        verify(view).showNoteElements(viewModel.getNoteList());
    }

    @Test
    public void onResultDelete(){
        ArrayList<Note> arrayList = mock(ArrayList.class);
        when(viewModel.getNoteList()).thenReturn(arrayList);
        presenter.onResultDelete(0);
        verify(view).showNoteElements(viewModel.getNoteList());
    }
}
