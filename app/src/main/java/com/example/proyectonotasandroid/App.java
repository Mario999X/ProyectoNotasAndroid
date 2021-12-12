package com.example.proyectonotasandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class App extends AppCompatActivity implements OnClick{



    ArrayList<Nota> notaList;
    RecyclerView recyclerView;
    MyAdapter myAdapter;



    private SharedPreferences mPrefes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        notaList = new ArrayList<Nota>();
        myAdapter = new MyAdapter(notaList, this);


        recyclerView.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPrefes = getSharedPreferences("ProyectoNotasAndroid", MODE_PRIVATE);
    }

    public void crearNuevaNota(Nota newNota) {
        //este metodo recibira una nota creada por el dialogo

             //nTemporal = newNota;

            myAdapter.addNota(newNota);
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

        /*if(item.getItemId() == R.id.action_ajustes){
            //creamos un Intent
            Intent intent = new Intent(this, AjustesActivity.class);
            //arrancar la activity
            startActivity(intent);
        }

         */
        return false;
    }

    @Override
    public void onClick(int posicion) {

    }
}