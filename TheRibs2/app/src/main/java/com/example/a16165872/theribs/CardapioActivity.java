package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class CardapioActivity extends AppCompatActivity {

    List<Prato> pratos = new ArrayList<>();
    GridView grid_items;
    Context context;
    Spinner spn_categoria, spn_filial;
    String url;

    ArrayAdapter adapter, adapter_filiais;
    PratoAdpter adpter_pratos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;


        grid_items = (GridView) findViewById(R.id.grid_items);

        adpter_pratos = new PratoAdpter(
                this,
                R.layout.adpter_cardapio,
                new ArrayList<Prato>()
        );

        grid_items.setAdapter(adpter_pratos);

        carregarPratos();


    }

    public void voltarNavegacao(View view) {

        Intent intent = getIntent();
        String parent = intent.getStringExtra("Parent");

        if(parent.equals("Main")){

            Intent abrirMain = new Intent(this, MainActivity.class);
            startActivity(abrirMain);

        }else if(parent.equals("Home")){

            Intent abrirHome = new Intent(this, HomeActivity.class);
            startActivity(abrirHome);
        }

    }

    View mView;
    public void abrirFiltroDialog(View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CardapioActivity.this);
        mView = getLayoutInflater().inflate(R.layout.dialog_filtro, null);

        spn_categoria = mView.findViewById(R.id.spn_categoria);
        spn_filial = mView.findViewById(R.id.spn_filial);

        adapter = new ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                new ArrayList<Categoria>()

        );

        adapter_filiais = new ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                new ArrayList<Filial>()
        );


        spn_categoria.setAdapter(adapter);
        spn_filial.setAdapter(adapter_filiais);

        carregarCategorias();
        carregarFiliais();

        mBuilder.setView(mView);

        AlertDialog alert = mBuilder.create();
        alert.show();

    }

    private void carregarCategorias(){

        new AsyncTask<Void, Void, Void>(){

            Categoria categorias[];

            @Override
            protected Void doInBackground (Void...voids){

                String dadosJason = HttpConnection.get(getString(R.string.link_node) + "/BuscarCateggoias");
                categorias = new Gson().fromJson(dadosJason, Categoria[].class);

                Log.d("#tag", dadosJason);

                return null;
            }

            @Override
            protected void onPostExecute (Void aVoid){
                super.onPostExecute(aVoid);

                adapter.clear();
                adapter.addAll(Arrays.asList(categorias));


            }
        }.execute();
    }

    private void carregarFiliais(){

        new AsyncTask<Void, Void, Void>(){

            Filial filial[];

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarFiliais");
                filial = new Gson().fromJson(dadosJson, Filial[].class);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adapter_filiais.clear();
                adapter_filiais.addAll(Arrays.asList(filial));
            }
        }.execute();
    }

    private void carregarPratos(){

        new AsyncTask<Void, Void, Void>(){

            Prato pratos[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJason = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratos");
                pratos = new Gson().fromJson(dadosJason, Prato[].class);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adpter_pratos.clear();
                adpter_pratos.addAll(Arrays.asList(pratos));

            }
        }.execute();
    }

}
