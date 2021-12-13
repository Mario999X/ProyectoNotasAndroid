package com.example.proyectonotasandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import java.util.ArrayList;

public class App extends AppCompatActivity implements OnClick{

    ArrayList<Note> noteList;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    private ConstraintLayout layoutMain;
    private Animation blink;

    private JSonSerialicer serialicer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initComponents();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause(){
        super.onPause();
        saveNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflamos el menu con el layout menu creado
        getMenuInflater().inflate(R.menu.menu_superior, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //return super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.action_add){
            //aqui debemos de invocar una nueva instancia del dialogo para crear notas
            NewNote dialog = new NewNote();
            //mostramos ese dialogo a traves del manager
            dialog.show(getSupportFragmentManager(),"nota_crear");
        }

        if(item.getItemId() == R.id.action_ajustes){
            //creamos un Intent
            Intent intent = new Intent(this, SettingsActivity.class);
            //arrancar la activity
            startActivity(intent);
        }

        return false;
    }

    @Override
    public void onClick(int position) {

    }


    @Override
    public void onLongClick(int position, ConstraintLayout layoutMain) {
        removeNote(position, layoutMain).show();
    }


    private void initComponents(){

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        layoutMain = findViewById(R.id.layoutMain);

        noteList = readFile();
        blink = AnimationUtils.loadAnimation(this,R.anim.prolonged_selection);


        setAdaptator();
    }


    public void addNewNote(Note n){
        noteList.add(n);
        setAdaptator();
    }


    private AlertDialog removeNote(int position, ConstraintLayout layoutMain){
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        layoutMain.startAnimation(blink);

        aviso.setMessage("¿Quieres borrar la nota?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteList.remove(position);
                setAdaptator();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return aviso.create();
    }

    private void saveNotes(){

        serialicer = new JSonSerialicer("notes.json", this);
        serialicer.save(noteList);
        setAdaptator();
    }

    private ArrayList<Note> readFile(){
        serialicer = new JSonSerialicer("notes.json", this);
        return serialicer.load();
    }


    private void setAdaptator(){
        myAdapter = new MyAdapter(noteList, this);
        recyclerView.setAdapter(myAdapter);
    }

}