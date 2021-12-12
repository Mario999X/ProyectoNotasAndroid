package com.example.proyectonotasandroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity{

    MyAdapter myAdapter;
    Button botonReset;

    private SharedPreferences mPrefes; // para leer datos guardados en disco
    private SharedPreferences.Editor mEditor; // para escribir datos en las shared

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        mPrefes = getSharedPreferences("ProyectoNotasAndroid", MODE_PRIVATE);

        mEditor = mPrefes.edit(); //formato editable

        botonReset = findViewById(R.id.botonReset);

        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.removeAllNotas();
                myAdapter.notifyDataSetChanged();
        }
    });


    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.commit();
    }


}