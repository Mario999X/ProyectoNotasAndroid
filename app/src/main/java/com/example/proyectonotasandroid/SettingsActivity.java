package com.example.proyectonotasandroid;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity{

    //MyAdapter myAdapter;
    //Button botonReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
/*
        botonReset = findViewById(R.id.botonReset);

        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //myAdapter.removeNotes();
        }
    });


 */
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

}