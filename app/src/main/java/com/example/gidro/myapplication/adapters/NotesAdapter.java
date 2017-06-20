package com.example.gidro.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gidro.myapplication.R;
import com.example.gidro.myapplication.model.Note;
import com.example.gidro.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gidro on 06.06.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final Callback callback;
    private ArrayList<Note> noteList;

    public interface Callback {

        void onNoteClick(Note note, int noteId);

    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);

        NoteViewHolder noteViewHolder = new NoteViewHolder(v);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {

        holder.header.setText(noteList.get(position).getHeader());
        holder.notePriority.setImageResource(Utils.numberToDrawableIdPriority(noteList.get(position).getPriority()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onNoteClick(noteList.get(position), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        private TextView header;
        private ImageView notePriority;
        private ViewGroup mainLayout;


        public NoteViewHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.header);
            notePriority = (ImageView) itemView.findViewById(R.id.note_priority);
            mainLayout = (ViewGroup) itemView.findViewById(R.id.main_notes);
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("id of click "+getAdapterPosition());
                    callback.onNoteClick(noteList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

    public NotesAdapter(ArrayList<Note> noteList, Callback callback){
        this.noteList = noteList;
        this.callback = callback;
    }

    public ArrayList<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(ArrayList<Note> noteList) {
        this.noteList = noteList;
    }

    public void updateData(ArrayList<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }
}
