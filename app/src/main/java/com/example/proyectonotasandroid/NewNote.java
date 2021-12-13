package com.example.proyectonotasandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


// CLASE ENCARGADA DE DISEÑAR UNA VENTANA DE DIALOGO EN LA CREACION DE UNA NUEVA NOTA
public class NewNote extends DialogFragment {

    // METODO ENCARGADO DE LA CREACION DEL DIALOGO
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //construir una alert con el constructor Builder
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //solicitamos el inflador
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflamos el dialogo con el layout  de nota nueva
        View dialogView = inflater.inflate(R.layout.activity_new_note,null);

        EditText textoToWrite = dialogView.findViewById(R.id.textToWrite);
        Button buttonGuardar = dialogView.findViewById(R.id.buttonGuardar);
        Button buttonCancelar = dialogView.findViewById(R.id.buttonCancelar);

        //construimos el dialog
        builder.setView(dialogView)
                .setMessage("Añadir una nueva nota");


        // LISTENER ENCARGADO DEL BOTON CANCELAR
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //LISTENER ENCARGADO DEL BOTON GUARDAR
        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note newNote = new Note();

                newNote.setText(textoToWrite.getText().toString());

                App callActivity = (App) getActivity();

                callActivity.addNewNote(newNote);

                dismiss();
            }
        });

        return builder.create();

    }
}