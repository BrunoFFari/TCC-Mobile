package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PagamentoActivity extends AppCompatActivity {


    ToggleButton toggle_button;
    Button btn_enviar, btn_voltar;
    RatingBar avaliacao;
    Context context;
    TextView txt_valor_final;
    ListView list_item;
    ListView list_item_cartoes;

    ItensSolicitadosAdapter adapter;
    CartaoAdapter cartaoAdapter;
    Cartao cartaoSelecionado;

    int idCliente, idPedido;
    Float preco;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        context = this;

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("idCliente", 0);
        preco = intent.getFloatExtra("preco", 0);
        idPedido = intent.getIntExtra("idPedido", 0);

        toggle_button = (ToggleButton) findViewById(R.id.toggle_button);
        btn_enviar = (Button)findViewById(R.id.btn_enviar);
        avaliacao = (RatingBar)findViewById(R.id.avaliacao);
        txt_valor_final = (TextView)findViewById(R.id.txt_valor_final);
        list_item = (ListView)findViewById(R.id.list_item);

        adapter = new ItensSolicitadosAdapter(
                context,
                R.layout.adapter_itens_solicitados,
                new ArrayList<ItemPedido>()
        );

        list_item.setAdapter(adapter);

        txt_valor_final.setText("R$ " + preco.toString());

        if(Internet.VerificarConexao(context)){

            BuscarItensPedido();
        }else{
            Snackbar.make(coordinatorLayout,
                    "Infelizmente você não poderá efetuar " +
                            "o pagamento pelo aplicativo, pois está sem internet",
                    Snackbar.LENGTH_LONG).show();
        }

        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(toggle_button.isChecked()){
                    AbrirDialogCartao();
                }else{
                    AbrirDialogDinheiro();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("idCliente", idCliente);
        intent.putExtra("validacao", 1);
        startActivity(intent);
    }

    public void BuscarItensPedido(){


        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Buscabdo os pratos que você pediu...");
            }

            ItemPedido itens[];
            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarItensSolicitados?id="+ idPedido);
                itens = new Gson().fromJson(dadosJson, ItemPedido[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(itens != null){
                    adapter.clear();
                    adapter.addAll(Arrays.asList(itens));
                    dialog.dismiss();
                }
            }

        }.execute();


    }

    public void AbrirDialogCartao(){
        View dialog = getLayoutInflater().inflate(R.layout.fragment_cartoes, null);

        list_item_cartoes = dialog.findViewById(R.id.list_item);


        AlertDialog.Builder mBuilder = new AlertDialog.Builder(PagamentoActivity.this);
        mBuilder.setView(dialog);


        cartaoAdapter = new CartaoAdapter(
                context,
                R.layout.adpter_cartao,
                new ArrayList<Cartao>()
        );

        list_item_cartoes.setAdapter(cartaoAdapter);

        if(Internet.VerificarConexao(context)){
            BuscarCartões();
        }else{
            Snackbar.make(coordinatorLayout,
                    "Infelizmente você não poderá efetuar " +
                            "o pagamento pelo aplicativo, pois está sem internet",
                    Snackbar.LENGTH_LONG).show();
        }

        list_item_cartoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                cartaoSelecionado = cartaoAdapter.getItem(i);

                if(cartaoSelecionado.getId_cartao() == 4){
                    Intent intent = new Intent(context, PagamentoAutorizadoActivity.class);
                    intent.putExtra("idPedido", idPedido);
                    intent.putExtra("idCliente", idCliente);
                    intent.putExtra("valor", preco);
                    intent.putExtra("Status", "Recusado");
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(context, PagamentoAutorizadoActivity.class);
                    intent.putExtra("idPedido", idPedido);
                    intent.putExtra("idCliente", idCliente);
                    intent.putExtra("valor", preco);
                    intent.putExtra("Status", "Aprovado");
                    startActivity(intent);
                }

            }
        });


        final AlertDialog alert = mBuilder.create();
        alert.show();
    }

    public void AbrirDialogDinheiro(){
        View dialog = getLayoutInflater().inflate(R.layout.dialog_dinheiro, null);

        btn_voltar = dialog.findViewById(R.id.btn_voltar);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(PagamentoActivity.this);
        mBuilder.setView(dialog);

        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeActivity.class);
                intent.putExtra("idCliente", idCliente);
                intent.putExtra("validacao", 1);
                startActivity(intent);
            }
        });


        final AlertDialog alert = mBuilder.create();
        alert.show();
    }

    public void BuscarCartões(){


        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(context, "Estamos Trabalhando", "Buscando os seus cartões...");
            }

            Cartao cartao[];
            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarCartoes?id="+ idCliente);
                cartao = new Gson().fromJson(dadosJson, Cartao[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                cartaoAdapter.clear();
                cartaoAdapter.addAll(Arrays.asList(cartao));
                dialog.dismiss();

            }
        }.execute();
    }

}
