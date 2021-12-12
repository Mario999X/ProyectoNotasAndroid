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
public class NuevaNota extends DialogFragment {

    // METODO ENCARGADO DE LA CREACION DEL DIALOGO
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //construir una alert con el constructor Builder
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //solicitamos el inflador
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflamos el dialogo con el layout  de nota nueva
        View dialogView = inflater.inflate(R.layout.activity_nueva_nota,null);

        EditText editarTexto = dialogView.findViewById(R.id.editarTexto);
        Button botonGuardar = dialogView.findViewById(R.id.botonGuardar);
        Button botonCancelar = dialogView.findViewById(R.id.botonCancelar);

        //construimos el dialog
        builder.setView(dialogView)
                .setMessage("Añadir una nueva Nota");


        // LISTENER ENCARGADO DEL BOTON CANCELAR
        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //LISTENER ENCARGADO DEL BOTON GUARDAR
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nota newNota = new Nota();

                newNota.setTexto(editarTexto.getText().toString());

                App llamarActivity = (App) getActivity();

                llamarActivity.crearNuevaNota(newNota);

                dismiss();
            }
        });

        return builder.create();

    }
}