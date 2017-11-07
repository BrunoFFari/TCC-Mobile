package com.example.a16165872.theribs;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EventosActivity extends AppCompatActivity {

    List<Evento> eventos = new ArrayList<>();
    ListView lista;
    Context context;
    EventoAdpter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        adpter = new EventoAdpter(
                context,
                R.layout.adpter_eventos,
                new ArrayList<Evento>()
        );


        lista = (ListView)findViewById(R.id.list_item);

        lista.setAdapter(adpter);

        carregarEventos();

    }

    private void carregarEventos(){
        new AsyncTask<Void, Void, Void>(){

            Evento listaEvento[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarEventos");
                listaEvento = new Gson().fromJson(dadosJson, Evento[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adpter.clear();
                adpter.addAll(Arrays.asList(listaEvento));
            }

        }.execute();
    }

}
