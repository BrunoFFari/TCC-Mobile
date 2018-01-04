package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

public class AbrirPedidoActivity extends AppCompatActivity {

    int idMesa, idGarcom;
    Button btn_abrir_pedido;
    Context context;
    String parametros, codigo;
    TextView txt_codigo;


    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        idMesa = intent.getIntExtra("idMesa", 0);
        idGarcom = intent.getIntExtra("idGarcom", 0);

        txt_codigo = (TextView)findViewById(R.id.txt_codigo);

        context = this;


        if(Internet.VerificarConexao(context)){
            BuscarUltimoPedido();
        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente te espera", Snackbar.LENGTH_LONG).show();
        }

        btn_abrir_pedido = (Button)findViewById(R.id.btn_abrir_pedido);

        configurarClickBotatAbrirPedido();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void configurarClickBotatAbrirPedido() {
        btn_abrir_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(context)){

                    Calendar calendar = Calendar.getInstance();
                    int ano = calendar.get(Calendar.YEAR);
                    int mes = calendar.get(Calendar.MONTH);
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int hora = calendar.get(Calendar.HOUR);
                    int minutos = calendar.get(Calendar.MINUTE);
                    int segundos = calendar.get(Calendar.SECOND);

                    String data = ano +"-"+ mes + "-"+ dia + " " + hora + ":" + minutos + ":" + segundos;
                    parametros = "idGarcom="+idGarcom+"&idMesa="+idMesa+"&codigo="+codigo+"&data="+data;
                    AbrirPedido();


                }else{

                    Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente te espera", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    private void BuscarUltimoPedido(){

        new AsyncTask<Void, Void, Void>(){

             Conta conta[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/buscarUltimoPedido");
                conta = new Gson().fromJson(dadosJson, Conta[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                codigo = "PED000" + (conta[0].getId_pedido() + 1);
                txt_codigo.setText(codigo);

            }


        }.execute();

    }

    private void AbrirPedido(){

        new AsyncTask<Void, Void, Void>(){

            String resultado;
            @Override
            protected Void doInBackground(Void... voids) {

                resultado = Conexao.postDados(getString(R.string.link_node) + "/AbrirPedido" , parametros );

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(resultado.contains("Erro")){

                    Snackbar.make(coordinatorLayout, "Não conseguimos abrir a conta", Snackbar.LENGTH_LONG).show();

                }else{

                    Snackbar.make(coordinatorLayout, "Conta Aberta", Snackbar.LENGTH_LONG).show();

                }

            }


        }.execute();

    }

}
