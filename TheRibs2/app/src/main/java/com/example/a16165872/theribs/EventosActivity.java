package com.example.a16165872.theribs;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventosActivity extends AppCompatActivity {

    List<Evento> eventos = new ArrayList<>();
    ListView lista;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        lista = (ListView)findViewById(R.id.list_item);

        eventos.add(new Evento(1, "Titulo Evento", "Evento muito legal mesmo!", "15/10/2017", R.drawable.evento1));
        eventos.add(new Evento(1, "Titulo Evento", "Evento muito legal mesmo!", "15/10/2017", R.drawable.evento2));
        eventos.add(new Evento(1, "Titulo Evento", "Evento muito legal mesmo!", "15/10/2017", R.drawable.evento3));
        eventos.add(new Evento(1, "Titulo Evento", "Evento muito legal mesmo!", "15/10/2017", R.drawable.evento4));

        EventoAdpter adpter = new EventoAdpter(
                context,
                R.layout.adpter_eventos,
                eventos
        );

        lista.setAdapter(adpter);

    }

}
