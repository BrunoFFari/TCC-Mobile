package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class DetalhesPratoActivity extends AppCompatActivity {


    int idPrato;
    Toolbar toolbar;
    Context context;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView txt_descricao;
    ListView list_ingredientes, list_filiais;
    IngredienteAdapter adapter_ingredientes;
    FilialPratoAdapter adapter_filiais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_prato);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        context = this;

        //Resgata o ID do prato selecinado em CardapioActivity
        Intent intent = getIntent();
        idPrato = intent.getIntExtra("idPrato", 0);

        findViews();
        adapters();
        buscarPratoID();
        buscarIngredientes();
        buscarFiliais();

    }

    private  void findViews(){
        txt_descricao = (TextView) findViewById(R.id.txt_descricao);
        list_ingredientes = (ListView)findViewById(R.id.list_ingredientes);
        list_filiais = (ListView) findViewById(R.id.list_filiais);
    }

    private  void  adapters(){

        adapter_filiais = new FilialPratoAdapter(
                context,
                R.layout.adpter_filiais_detalhes_prato,
                new ArrayList<Filial>()
        );

        adapter_ingredientes = new IngredienteAdapter(
                context,
                R.layout.adpter_ingredientes,
                new ArrayList<Ingrediente>()
        );

        list_ingredientes.setAdapter(adapter_ingredientes);
        list_filiais.setAdapter(adapter_filiais);
    }

    private void buscarPratoID(){

        new AsyncTask<Void, Void, Void>(){

            Prato prato[];
            Drawable drawable;
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratoID?id=" + idPrato);
                prato = new Gson().fromJson(dadosJson, Prato[].class);

                try {
                    URL url = new URL("http://euppiro.com.br/wp-content/uploads/2017/07/segmento-restaurante-ecomanda-705x296.jpg");
                    Bitmap decoded = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    drawable = new BitmapDrawable(getResources(), decoded);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){

                    toolbar.setTitle(prato[0].getNome());
                    txt_descricao.setText(prato[0].getDescricao());

                    if(Build.VERSION.SDK_INT > 16){
                        collapsingToolbarLayout.setBackground(drawable);
                    }else{
                        Log.d("#tag", "Celular velho.");
                    }

                }

            }
        }.execute();


    }

    private void buscarIngredientes(){

        new AsyncTask<Void, Void, Void>(){

            Ingrediente ingrediente[];

            @Override
            protected Void doInBackground(Void... voids) {


                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarIngredientesPrato?id=" + idPrato);
                ingrediente = new Gson().fromJson(dadosJson, Ingrediente[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adapter_ingredientes.clear();
                adapter_ingredientes.addAll(Arrays.asList(ingrediente));

            }
        }.execute();

    }

    private void buscarFiliais(){

        new AsyncTask<Void, Void, Void>(){


            Filial filial[];
            @Override
            protected Void doInBackground(Void... voids) {
                String url = getString(R.string.link_node) + "/BuscarFiliaisPrato?id=" + idPrato;
                String dadosJson = HttpConnection.get(url);

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

}
