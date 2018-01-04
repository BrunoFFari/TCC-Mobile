package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.graphics.EmbossMaskFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class AcompanhamentoActivity extends AppCompatActivity {


    ListView list_item;
    Context context;
    PedidoAdapter adapter;
    TextView txt_title;
    Button btn_buscar;

    Socket socket;

    Float precoFinal;

    int idCliente, idPedido;

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        context = this;
        txt_title = (TextView) findViewById(R.id.txt_title);
        btn_buscar = (Button) findViewById(R.id.btn_buscar);

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("idCliente", 0);
        idPedido = intent.getIntExtra("idPedido", 0);

        list_item = (ListView) findViewById(R.id.list_item);

        adapter = new PedidoAdapter(
                this,
                R.layout.adpter_pedido,
                new ArrayList<Pedido>()
        );

        list_item.setAdapter(adapter);


        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(context)){
                    BuscarPratosSolicitados();
                }else{
                    Snackbar.make(
                            coordinatorLayout,
                            "Sua internet já foi trás ela de volta a gente te espera",
                            Snackbar.LENGTH_LONG
                    );
                }
            }
        });


        if(Internet.VerificarConexao(context)){
            BuscarPratosSolicitados();
        }else{
            Snackbar.make(
                    coordinatorLayout,
                    "Sua internet já foi trás ela de volta a gente te espera",
                    Snackbar.LENGTH_LONG
            );
        }


        try{
            socket = IO.socket(getString(R.string.link_node));

        }catch (Exception e){
            e.printStackTrace();
        }
        socket.connect();
        socket.on("novo_pedido", new Emitter.Listener(){

            @Override
            public void call(Object... args) {

                if(Internet.VerificarConexao(context)){
                    BuscarPratosSolicitados();
                }else{
                    Snackbar.make(
                            coordinatorLayout,
                            "Sua internet já foi, trás ela de volta a gente te espera...",
                            Snackbar.LENGTH_LONG
                    ).show();
                }

            }
        });

        socket.on("conta_fechada", new Emitter.Listener(){

            @Override
            public void call(Object... args) {
                if(Internet.VerificarConexao(context)){
                    VerificarStatusDaConta();
                }else{
                    Snackbar.make(
                            coordinatorLayout,
                            "Sua internet já foi, trás ela de volta a gente te espera...",
                            Snackbar.LENGTH_LONG
                    ).show();
                }
            }
        });

    }

    private void BuscarPratosSolicitados(){

        new AsyncTask<Void, Void, Void>(){


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //Toast.makeText(, "teste", Toast.LENGTH_LONG).show();
                Log.d("#teste", "funcionou");
            }

            String dadosJson;
            Pedido pedidos[];
            @Override
            protected Void doInBackground(Void... voids) {
                dadosJson = HttpConnection.get(getString(R.string.link_node) +
                        "/BuscarPratosPedido?id="+ idPedido
                        + "&idCliente=" + idCliente);
                pedidos = new Gson().fromJson(dadosJson, Pedido[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.contains("[]")){

                    if(pedidos[0].getStatus() == 0){
                        Toast.makeText(context, "Sua conta foi fechada, escolha a forma de pagamento", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, PagamentoActivity.class);
                        intent.putExtra("idCliente", idCliente);
                        intent.putExtra("preco", precoFinal);
                        startActivity(intent);
                    }else{
                        txt_title.setText("Seus Pedidos");

                        precoFinal = Float.parseFloat("0");
                        for(int i = 0; i < pedidos.length; i++){

                            precoFinal += pedidos[i].getPreco();

                        }


                        try{
                            adapter.clear();
                            adapter.addAll(Arrays.asList(pedidos));
                        }catch (Exception e){
                            Log.d("ErroAcompanhamento", e.getMessage());
                        }

                    }


                }else{
                    txt_title.setText("Nenhum pedido realizado");

                }
            }

        }.execute();

    }

    private void VerificarStatusDaConta(){

        new AsyncTask<Void, Void, Void>(){


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                //Toast.makeText(, "teste", Toast.LENGTH_LONG).show();
                Log.d("#teste", "funcionou");
            }

            String dadosJson;
            Pedido pedidos[];
            @Override
            protected Void doInBackground(Void... voids) {
                dadosJson = HttpConnection.get(getString(R.string.link_node) +
                        "/BuscarPratosPedido?id="+ idPedido
                        + "&idCliente=" + idCliente);
                pedidos = new Gson().fromJson(dadosJson, Pedido[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.contains("[]")){

                    if(pedidos[0].getStatus() == 0){
                        Toast.makeText(context, "Sua conta foi fechada, escolha a forma de pagamento", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, PagamentoActivity.class);
                        intent.putExtra("idCliente", idCliente);
                        intent.putExtra("preco", precoFinal);
                        startActivity(intent);
                    }
                }
            }

        }.execute();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("idCliente", idCliente);
        intent.putExtra("validacao", 1);
        startActivity(intent);
    }



}
