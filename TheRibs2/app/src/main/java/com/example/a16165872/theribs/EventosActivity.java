package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
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


        if(Internet.VerificarConexao(context)){
            carregarEventos();
        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente espera!", Snackbar.LENGTH_LONG).show();
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Evento evento = adpter.getItem(i);
                Intent intent = new Intent(context, DetalhesEventosActivity.class);
                intent.putExtra("idEvento", evento.getId_vento());
                startActivity(intent);

            }
        });


    }

    private void carregarEventos(){
        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Buscando os Eventos...");
            }

            Evento listaEvento[];
            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarEventos");
                listaEvento = new Gson().fromJson(dadosJson, Evento[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adpter.clear();
                adpter.addAll(Arrays.asList(listaEvento));

                dialog.dismiss();
            }

        }.execute();
    }

}
