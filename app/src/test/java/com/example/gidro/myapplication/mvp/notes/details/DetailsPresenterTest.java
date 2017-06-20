package com.example.gidro.myapplication.mvp.notes.details;

import com.example.gidro.myapplication.model.Note;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Gidro on 20.06.2017.
 */
@RunWith(RobolectricTestRunner.class)
public class DetailsPresenterTest {
    private DetailsContract.Presenter presenter;

    @Mock
    private DetailsContract.View view;

    @Mock
    private DetailsViewModel viewModel;

    @Mock
    private Note note;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        presenter = new DetailsPresenter(view, viewModel, note);
    }

    private void setNote(){
        Note note = mock(Note.class);

        when(viewModel.getNote()).thenReturn(note);
        when(viewModel.getNote().getDetails()).thenReturn("Details");
        when(viewModel.getNote().getHeader()).thenReturn("Header");
        when(viewModel.getNote().getPictureURL()).thenReturn("PictureURL");
        when(viewModel.getNote().getPriority()).thenReturn(1);
    }

    @Test
    public void onViewCreate(){
        setNote();
        presenter.onViewCreate();
        verify(view).showNoteDetailsInformation(viewModel.getNote().getDetails());
        verify(view).showNoteHeader(viewModel.getNote().getHeader());
        verify(view).showNotePicture(viewModel.getNote().getPictureURL());
        verify(view).showNotePriority(viewModel.getNote().getPriority());
    }

    @Test
    public void onNoteHeaderChange(){
        setNote();
        presenter.onNoteHeaderChange(viewModel.getNote().getHeader());
        verify(viewModel.getNote()).setHeader(anyString());
    }

    @Test
    public void onNoteDetailsInformationChange(){
        setNote();
        presenter.onNoteDetailsInformationChange(viewModel.getNote().getDetails());
        verify(viewModel.getNote()).setDetails(anyString());
    }

    @Test
    public void onNoteDeleteClick(){
        Note note = mock(Note.class);
        when(viewModel.getNote()).thenReturn(note);

        presenter.onNoteDeleteClick();
        verify(view).showNotes(note,1);

    }

    @Test
    public void onNoteBackClick(){
        presenter.onBackPressed();
        verify(view).showDialogSave();
    }

    @Test
    public void onNotePriorityClick(){
        setNote();

        presenter.onNotePriorityClick();
        verify(view).showNotePriority(1);
    }

    @Test
    public void onDialogSaveOkClick(){
        Note note = mock(Note.class);
        when(viewModel.getNote()).thenReturn(note);

        presenter.onDialogSaveOkClick();
        verify(view).showNotes(note,RESULT_OK);
    }

    @Test
    public void onDialogSaveCancelClick(){
        presenter.onDialogSaveCancelClick();
        verify(view).showNotes(null,RESULT_CANCELED);
    }

    @Test
    public void onBackPressed(){
        presenter.onBackPressed();
        verify(view).showDialogSave();
    }
}
