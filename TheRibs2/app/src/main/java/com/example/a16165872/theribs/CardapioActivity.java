package com.example.a16165872.theribs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class CardapioActivity extends AppCompatActivity {


    List<Prato> pratos = new ArrayList<>();
    GridView grid_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        grid_items = (GridView) findViewById(R.id.grid_items);

        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato1));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato2));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato3));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato4));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato5));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato6));
        pratos.add(new Prato(1, "Batata Assada", Float.parseFloat("29.99"), "batata é muito bom ", 1, R.drawable.prato7));

        PratoAdpter adpter = new PratoAdpter(
                this,
                R.layout.adpter_cardapio,
                pratos
        );

        grid_items.setAdapter(adpter);

    }

    public void voltarNavegacao(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void abrirFiltroDialog(View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(CardapioActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_filtro, null);

        mBuilder.setView(mView);

        AlertDialog alert = mBuilder.create();
        alert.show();

    }
}
