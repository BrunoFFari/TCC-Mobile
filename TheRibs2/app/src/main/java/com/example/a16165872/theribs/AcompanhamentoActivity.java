package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AcompanhamentoActivity extends AppCompatActivity {


    ListView list_item;
    List<Pedido> pedidos = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhamento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;


        list_item = (ListView) findViewById(R.id.list_item);

        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));
        pedidos.add(new Pedido(1,1,R.drawable.prato2, "Batata Assada", "3"));

        PedidoAdapter adapter = new PedidoAdapter(
                this,
                R.layout.adpter_pedido,
                pedidos
        );

        list_item.setAdapter(adapter);

    }




}
