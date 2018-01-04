package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class PesquisaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView search_bar;
    String pesquisa;
    private CoordinatorLayout coordinatorLayout;
    Context context;
    GridView grid_items;
    PratoAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        context = this;

        grid_items = (GridView)findViewById(R.id.grid_items);

        search_bar = (SearchView) findViewById(R.id.search_bar);
        search_bar.setOnQueryTextListener(this);


        adapter = new PratoAdpter(
                context,
                R.layout.adpter_cardapio,
                new ArrayList<Prato>()
        );

        grid_items.setAdapter(adapter);

    }

    @Override
    public boolean onQueryTextChange(String novoTexto) {

        pesquisa = "?nome="+ novoTexto;
        BuscarPratos();


        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    private void BuscarPratos(){

        new AsyncTask<Void, Void, Void>(){

            Prato prato[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dados = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratoNome" + pesquisa);
                prato = new Gson().fromJson(dados, Prato[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){

                    adapter.clear();
                    adapter.addAll(Arrays.asList(prato));

                }else {


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Não conseguimos achar o que você está procurando... =(" , Snackbar.LENGTH_LONG)
                            .setAction("Voltar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(context, CardapioActivity.class);
                                    startActivity(intent);
                                }
                            });

                    snackbar.show();

                }

            }
        }.execute();


    }


}
