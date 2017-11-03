package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContatoActivity extends AppCompatActivity {


    Context context;
    List<Ocorrencia> ocorrenciaList = new ArrayList<>();
    ArrayAdapter<Ocorrencia> adpter;
    Spinner spinner;
    FloatingActionButton fab;
    EditText edit_nome, edit_telefone, edit_celular, edit_email, edit_menssagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        configurarFloatButton();
        findviews();
        buscarOcorrencias();

        adpter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<Ocorrencia>()
        );
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adpter);

    }

    private void findviews(){
        spinner = (Spinner)findViewById(R.id.spn_ocorrencia);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        edit_celular = (EditText) findViewById(R.id.edit_celular);
        edit_telefone = (EditText) findViewById(R.id.edit_telefone);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_menssagem = (EditText) findViewById(R.id.edit_menssagem);

    }

    private void voltarNavegar(){

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    private void configurarFloatButton(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(edit_nome.getText().toString().isEmpty()){
                   Snackbar.make(view, "Preencha seu nome para continuar", Snackbar.LENGTH_LONG).show();
               }else {

                   if(edit_celular.getText().toString().isEmpty() && edit_email.getText().toString().isEmpty() && edit_telefone.getText().toString().isEmpty()){
                       Snackbar.make(view, "Preencha pelo menos uma forma de contato", Snackbar.LENGTH_LONG).show();
                   }else {

                   }

               }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void buscarOcorrencias(){

        new AsyncTask<Void, Void, Void>(){

            Ocorrencia[] ocorrencias;
            @Override
            protected Void doInBackground(Void... params) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarOcorrencias");
                ocorrencias = new Gson().fromJson(dadosJson, Ocorrencia[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adpter.clear();
                adpter.addAll(Arrays.asList(ocorrencias));

            }



        }.execute();
    }

    private void enviarMensagem(){



        new AsyncTask<String, Void, String>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();

    }

}
