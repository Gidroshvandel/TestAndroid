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

import java.util.List;

/**
 * Created by Gidro on 06.06.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> noteList;

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(v);
        return noteViewHolder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {

        holder.header.setText(noteList.get(position).getHeader());
        holder.notePriority.setImageResource(Utils.numberToDrawbleIdPriority(noteList.get(position).getPriority()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView header;
        ImageView notePriority;

        public NoteViewHolder(View itemView) {
            super(itemView);
            header = (TextView)itemView.findViewById(R.id.header);
            notePriority = (ImageView)itemView.findViewById(R.id.note_priority);
        }
    }

    public NotesAdapter(List<Note> noteList){
        this.noteList = noteList;
    }

    public void updateData(List<Note> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }
}
