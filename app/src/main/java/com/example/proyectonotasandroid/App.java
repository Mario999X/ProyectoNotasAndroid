package com.example.proyectonotasandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;

public class App extends AppCompatActivity implements OnClick{

    ArrayList<Nota> notaList;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

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
        guardarNotas();
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
            NuevaNota dialog = new NuevaNota();
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
    public void onClick(int posicion) {

    }

    @Override
    public void onLongClick(int posicion) {
        borrarNota(posicion).show();
    }


    private void initComponents(){

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        notaList = leerFichero();

        setAdaptator();
    }


    public void nuevaNota(Nota n){
        notaList.add(n);
        setAdaptator();
    }


    private AlertDialog borrarNota(int posicion){
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);

        aviso.setMessage("¿Quieres borrar la nota?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notaList.remove(posicion);
                setAdaptator();

            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return aviso.create();
    }

    private void guardarNotas(){

        serialicer = new JSonSerialicer("notas.json", this);
        serialicer.save(notaList);
        setAdaptator();
    }

    private ArrayList<Nota> leerFichero(){
        serialicer = new JSonSerialicer("notas.json", this);
       return serialicer.load();
    }


    private void setAdaptator(){
        myAdapter = new MyAdapter(notaList, this);
        recyclerView.setAdapter(myAdapter);
    }

}