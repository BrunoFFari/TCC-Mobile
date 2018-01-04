package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

public class PagamentoAutorizadoActivity extends AppCompatActivity {


    TextView valor, emissao, numero, status;

    int idCliente, idPedido;
    Float valor_final;
    String status_final;

    Context context;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_autorizado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        context = this;

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("idCliente", 0);
        idPedido = intent.getIntExtra("idPedido", 0);
        valor_final = intent.getFloatExtra("valor", 0);
        status_final = intent.getStringExtra("Status");

        valor = (TextView)findViewById(R.id.valor);
        emissao = (TextView)findViewById(R.id.emissao);
        numero = (TextView)findViewById(R.id.numero);
        status = (TextView)findViewById(R.id.status);

        if(status_final.equals("Recusado")){
            status.setTextColor(Color.RED);
            status.setText(status_final);
        }else{


            if(Internet.VerificarConexao(context)){
                BuscarNotaFiscal();
            }else{
                Snackbar.make(
                        coordinatorLayout,
                        "Sua internet já foi trás ela de volta, a gente te espera",
                        Snackbar.LENGTH_LONG
                ).show();
            }

            status.setTextColor(Color.GREEN);
            status.setText(status_final);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("idCliente", idCliente);
        intent.putExtra("validacao", 1);
        startActivity(intent);

    }

    private void BuscarNotaFiscal(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Validando o Pagamento...");
            }


            NotaFiscal notaFiscal[];
            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarNotaFiscalID?id=" + idPedido);
                notaFiscal = new Gson().fromJson(dadosJson, NotaFiscal[].class);

                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(notaFiscal != null){
                    numero.setText("Número da nota fiscal: " + notaFiscal[0].getNumero());
                    emissao.setText("Data de Emissao: " + notaFiscal[0].getEmissao());
                    valor.setText("Valor: R$ " + valor_final.toString());
                }

                dialog.dismiss();
            }
        }.execute();
    }
}
