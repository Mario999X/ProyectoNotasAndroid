package com.example.proyectonotasandroid;


import androidx.constraintlayout.widget.ConstraintLayout;

//CLASE INTERFAZ
public interface OnClick {

   public void onClick (int position);

   public void onLongClick(int position, ConstraintLayout layout);

}
