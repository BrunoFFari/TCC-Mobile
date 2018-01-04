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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button btn_filtrar, btn_limpar;
    Context context;
    Spinner spn_categoria, spn_filial;

    ArrayAdapter adapter, adapter_filiais;
    PratoAdpter adpter_pratos;
    Prato pratoSelecionado;

    View mView;

    Categoria categoriaSelecionada;
    Filial filialSelecionada;
    String parametros;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        findViews();
        configarClickGridCardapio();

        adpter_pratos = new PratoAdpter(
                this,
                R.layout.adpter_cardapio,
                new ArrayList<Prato>()
        );

        grid_items.setAdapter(adpter_pratos);

        if(Internet.VerificarConexao(context)){
            carregarPratos();
        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente espera!", Snackbar.LENGTH_LONG).show();
        }


    }

    private void findViews(){
        grid_items = (GridView) findViewById(R.id.grid_items);
    }

    private void configarClickGridCardapio() {
        grid_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                pratoSelecionado = adpter_pratos.getItem(position);
                Intent intent = new Intent(context, DetalhesPratoActivity.class);
                intent.putExtra("idPrato", pratoSelecionado.getId_produto());
                startActivity(intent);

            }
        });
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

    public void abrirFiltroDialog(View view) {

        if(Internet.VerificarConexao(context)){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(CardapioActivity.this);
            mView = getLayoutInflater().inflate(R.layout.dialog_filtro, null);

            btn_filtrar = mView.findViewById(R.id.btn_filtrar);
            btn_limpar = mView.findViewById(R.id.btn_limpar);

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
            final AlertDialog alert = mBuilder.create();
            alert.show();

            spn_categoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    categoriaSelecionada = (Categoria) adapter.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spn_filial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    filialSelecionada = (Filial) adapter_filiais.getItem(i);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
        });

            btn_limpar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carregarPratos();
                    alert.dismiss();
                }
            });


            btn_filtrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    parametros = "?idCategoria="+categoriaSelecionada.getId_tipo_prato()+"&idFilial="+filialSelecionada.getId_restaurante();
                    carregarPratosFilto();
                    alert.dismiss();

                }
            });

        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente espera!", Snackbar.LENGTH_LONG).show();
        }


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

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Buscando as Filais...");
            }

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

                dialog.dismiss();
            }
        }.execute();
    }

    private void carregarPratos(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Buscando os pratos...");
            }

            Prato pratos[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJason = HttpConnection.get("http://www.eatribstuff.com.br:8090/BuscarPratos");
                pratos = new Gson().fromJson(dadosJason, Prato[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                try{

                    adpter_pratos.clear();
                    adpter_pratos.addAll(Arrays.asList(pratos));

                }catch (Exception e){

                }


                dialog.dismiss();

            }
        }.execute();
    }

    private void carregarPratosFilto(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(context, "Estmamos Trabalhando", "Filtrando o Cardápio");
            }

            Prato prato[];
            @Override
            protected Void doInBackground(Void... params) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratosFiltro" +  parametros);
                prato = new Gson().fromJson(dadosJson, Prato[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){

                    try{
                        adpter_pratos.clear();
                        adpter_pratos.addAll(Arrays.asList(prato));
                    }catch (Exception e){

                    }

                    dialog.dismiss();
                }

            }
        }.execute();

    }

    public void abrirPesquisa(View view) {
        Intent intent = new Intent(context, PesquisaActivity.class);
        startActivity(intent);
    }
}
