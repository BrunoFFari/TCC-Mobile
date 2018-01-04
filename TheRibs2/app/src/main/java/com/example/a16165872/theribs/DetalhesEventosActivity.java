package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.w3c.dom.Text;
import java.text.Format;


public class DetalhesEventosActivity extends AppCompatActivity {


    int idEvento;
    int idRestaurante;
    Context context;
    TextView txt_descricao, txt_restaurante, txt_data;
    Toolbar toolbar;
    Button btn_more_info;

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_eventos);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        findViews();

        final Intent intent = getIntent();
        idEvento = intent.getIntExtra("idEvento", 0);

        if(Internet.VerificarConexao(context)){
            buscarEvento();
            configurarClickBotaoMoreInfo();
        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente te espera", Snackbar.LENGTH_LONG).show();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        txt_restaurante = (TextView)findViewById(R.id.txt_restaurante);
        txt_descricao = (TextView)findViewById(R.id.txt_descricao);
        txt_data = (TextView)findViewById(R.id.txt_data);
        btn_more_info = (Button)findViewById(R.id.btn_more_info);
    }

    private void configurarClickBotaoMoreInfo() {
        btn_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent abrirRestaurante = new Intent(context, RestauranteActivity.class);
                abrirRestaurante.putExtra("id", idEvento);
                startActivity(abrirRestaurante);

            }
        });
    }

    private void buscarEvento(){

        new AsyncTask<Void, Void, Void>(){

            Evento evento[];
            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BucarEventoID?id=" + idEvento);
                evento = new Gson().fromJson(dadosJson, Evento[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.contains("[]")){

                    idRestaurante =  evento[0].getId_restaurante();
                    txt_descricao.setText(evento[0].getSobre());
                    txt_data.setText(evento[0].getData());
                    toolbar.setTitle(evento[0].getNome());
                    txt_restaurante.setText(evento[0].getNome_filial());

                }else{
                    Toast.makeText(context, "Tivemos um probleminha =( , tente mais tarde.", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute();

    }



}
