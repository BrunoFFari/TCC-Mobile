package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AbrirPedidoClienteActivity extends AppCompatActivity {

    int idCliente;

    Button btn_acompanhar;
    EditText edit_codigo;

    String parametro;
    Context context;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_pedido_cliente);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        context = this;


        btn_acompanhar = (Button)findViewById(R.id.btn_acompanhar);
        edit_codigo = (EditText)findViewById(R.id.edit_codigo);

        Intent intent = getIntent();
        idCliente = intent.getIntExtra("idCliente", 0);

        configurarClickBotao();


    }

    private void configurarClickBotao() {
        btn_acompanhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(context)){

                    if(!edit_codigo.getText().toString().isEmpty()){

                        parametro = "?codigo="+ edit_codigo.getText().toString()+"&idCliente="+ idCliente;
                        AbrirPedidoClinte();
                    }else{
                        Snackbar.make(coordinatorLayout,
                                "Preencha o campo acima com o código para continuar",
                                Snackbar.LENGTH_LONG).show();
                    }

                }else{
                    Snackbar.make(coordinatorLayout,
                            "Sua internet já era, trás ela de volta a gente te espera...",
                            Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    private void AbrirPedidoClinte(){

        new AsyncTask<Void, Void, Void>(){


            String resultado;
            @Override
            protected Void doInBackground(Void... voids) {

                resultado = HttpConnection.get(getString(R.string.link_node) + "/AbrirPedidoCliente" + parametro);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(resultado != null){
                    Snackbar.make(coordinatorLayout,
                            resultado, Snackbar.LENGTH_LONG).show();

                    SystemClock.sleep(1500);

                    if(resultado.contains("conectado")){
                        Intent intent = new Intent(context, AcompanhamentoActivity.class);
                        intent.putExtra("idCliente", idCliente);
                        intent.putExtra("codigo", edit_codigo.getText().toString());
                        startActivity(intent);
                    }

                }
            }


        }.execute();

    }



}
