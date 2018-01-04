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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContatoActivity extends AppCompatActivity {


    Context context;
    Ocorrencia ocorrenciaSelcionada;
    ArrayAdapter<Ocorrencia> adpter;
    Spinner spinner;
    FloatingActionButton fab;
    EditText edit_nome, edit_telefone, edit_celular, edit_email, edit_menssagem;
    String parametros;
    int idFilial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        findviews();
        configurarFloatButton();
        buscarOcorrencias();
        configurarClickSpinner();

        Intent intent = getIntent();
        idFilial = intent.getIntExtra("idRestaurante", 0);

        adpter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<Ocorrencia>()
        );
        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adpter);

    }

    private void configurarClickSpinner(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                ocorrenciaSelcionada = adpter.getItem(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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


                String nome = "";
                String email = "";
                String telefone = "";
                String celular = "";
                String mensagem = "";
                int id_filial = 0;
                int id_ocorrencia = 0;

               if(edit_nome.getText().toString().isEmpty()){
                   Snackbar.make(view, "Preencha seu nome para continuar", Snackbar.LENGTH_LONG).show();
               }else {

                   if(edit_celular.getText().toString().isEmpty() && edit_email.getText().toString().isEmpty() && edit_telefone.getText().toString().isEmpty()){
                       Snackbar.make(view, "Preencha pelo menos uma forma de contato", Snackbar.LENGTH_LONG).show();
                   }else {

                       if(ocorrenciaSelcionada != null){

                            nome = edit_nome.getText().toString();
                            email = edit_email.getText().toString();
                            telefone = edit_telefone.getText().toString();
                            celular = edit_celular.getText().toString();
                            id_filial = idFilial;
                            id_ocorrencia = ocorrenciaSelcionada.getId_ocorrencia();
                            mensagem = edit_menssagem.getText().toString();


                           if(Internet.VerificarConexao(context)){
                               parametros = "nome="+ nome +"&email="+email+"&telefone="+telefone+"&celular="+celular+"&idFilial="+id_filial+"&idOcorrencia="+id_ocorrencia+"&menssagem="+mensagem;
                               enviarMensagem();
                           }else{
                                Toast.makeText(context,
                                        "Sua internet já foi trás ela de volta, a gente te espera!",
                                        Toast.LENGTH_LONG).show();
                           }


                       }else{
                           Snackbar.make(view, "Selecione um tipo de ocorrencia", Snackbar.LENGTH_SHORT).show();
                       }



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


                try{
                    adpter.clear();
                    adpter.addAll(Arrays.asList(ocorrencias));
                }catch (Exception e){
                    e.printStackTrace();
                }


            }



        }.execute();
    }

    private void enviarMensagem(){



        new AsyncTask<String, Void, String>(){

            String retornoJason;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                retornoJason = Conexao.postDados(getString(R.string.link_node) + "/EnviarContato", parametros);

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(!retornoJason.isEmpty()){

                    if(retornoJason.contains("Obrigado")){
                        Toast.makeText(context, retornoJason, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, RestauranteActivity.class);
                        intent.putExtra("id", idFilial);
                        startActivity(intent);
                    }else{
                        Toast.makeText(context, retornoJason, Toast.LENGTH_LONG).show();
                    }


                }

            }
        }.execute();

    }

}
