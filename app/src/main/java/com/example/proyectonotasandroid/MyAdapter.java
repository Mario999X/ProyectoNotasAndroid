package com.example.proyectonotasandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Note> noteList;
    private OnClick onClick;

    public MyAdapter(ArrayList<Note> noteList, OnClick onClick) {
        this.noteList = noteList;
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,
                parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.salidaTexto.setText(noteList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView salidaTexto;
        private ConstraintLayout layout;

        public MyViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            layout = itemView.findViewById(R.id.layoutView);
            salidaTexto = itemView.findViewById(R.id.textOut);


        }


        @Override
        public void onClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onClick(posicion);
        }

        @Override
        public boolean onLongClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onLongClick(posicion, layout);
            return false;
        }
    }


}
