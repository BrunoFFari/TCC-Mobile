package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ContatoActivity extends AppCompatActivity {


    Context context;
    List<Ocorrencia> ocorrenciaList = new ArrayList<>();
    ArrayAdapter<Ocorrencia> adpter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Ainda não é possivel entrar em contato por aqui, tente pelo site, blz?", Snackbar.LENGTH_LONG)
                        .setAction("Voltar a Navegar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                voltarNavegar();
                            }
                        }).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = (Spinner)findViewById(R.id.spn_ocorrencia);


        ocorrenciaList.add(new Ocorrencia(1, "Reclamações"));
        ocorrenciaList.add(new Ocorrencia(1, "Sugestões"));
        ocorrenciaList.add(new Ocorrencia(1, "Criticas"));

        adpter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                ocorrenciaList
        );


        adpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adpter);

    }


    private void voltarNavegar(){

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}
