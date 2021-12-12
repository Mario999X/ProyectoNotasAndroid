package com.example.proyectonotasandroid;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {


    private SharedPreferences mPrefes; // para leer datos guardados en disco
    private SharedPreferences.Editor mEditor; // para escribir datos en las shared

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        mPrefes = getSharedPreferences("ProyectoNotasAndroid", MODE_PRIVATE);

        mEditor = mPrefes.edit(); //formato editable

    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.commit();
    }
}