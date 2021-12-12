package com.example.proyectonotasandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Nota> notaList;
    private OnClick onClick;

    public MyAdapter(ArrayList<Nota> notaList,OnClick onClick) {
        this.notaList = notaList;
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
        holder.salidaTexto.setText(notaList.get(position).getTexto());
    }

    @Override
    public int getItemCount() {
        return notaList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView salidaTexto;

        public MyViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            salidaTexto = itemView.findViewById(R.id.salidaTexto);

        }

        @Override
        public void onClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onClick(posicion);
        }

        @Override
        public boolean onLongClick(View v) {
            int posicion = getAdapterPosition();
            onClick.onLongClick(posicion);
            return false;
        }
    }


}
