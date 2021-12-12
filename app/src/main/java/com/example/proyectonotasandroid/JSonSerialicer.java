package com.example.proyectonotasandroid;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class JSonSerialicer {

    private String file;
    private Context contexto;
    private Gson gson;

    JSonSerialicer(String file, Context contexto) {
        this.file = file;
        this.contexto = contexto;
    }

    public void save(ArrayList<Nota> notasList){
        gson = new Gson();
        String json = gson.toJson(notasList);
        Writer writer = null;

        try{
            OutputStream out = contexto.openFileOutput(file,contexto.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Nota> load(){
        ArrayList<Nota> returner = null;

        BufferedReader reader = null;

        try{
            reader= new BufferedReader(new InputStreamReader(contexto.openFileInput(file)));
            gson = new Gson();
            Type type = new TypeToken<ArrayList<Nota>>(){}.getType();
            returner= gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(returner==null){
            return new ArrayList<>();
        }
        return returner;
    }

}
