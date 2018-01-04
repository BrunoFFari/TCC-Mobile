package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;


public class CheckFragment extends Fragment {

    int idCliente;
    Pedido pedido[];

    Button btn_check, btn_acompanhar;
    TextView txt_instrucao;

    public CheckFragment (int id){
        this.idCliente = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Timer timer = new Timer();
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView  = inflater.inflate(R.layout.fragment_check, container, false);

        btn_check = rootView.findViewById(R.id.btn_check);
        btn_acompanhar = rootView.findViewById(R.id.btn_acompanhar);
        txt_instrucao = rootView.findViewById(R.id.txt_instrucao);

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), AbrirPedidoClienteActivity.class);
                intent.putExtra("idCliente", idCliente);
                startActivity(intent);

            }
        });

        TimerTask updateBall = new UpdateBallTask();
        timer.scheduleAtFixedRate(updateBall, 0, 1000);

        btn_acompanhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), AcompanhamentoActivity.class);
                intent.putExtra("idCliente", idCliente);
                intent.putExtra("idPedido", pedido[0].getId_pedido());
                startActivity(intent);

            }
        });

        return  rootView;
    }

    class UpdateBallTask extends TimerTask {
        public void run() {
            BucarNovasContas();
        }
    }

    private void BucarNovasContas(){

        new AsyncTask<Void, Void, Void>(){

            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPedidoIDCliente?id="+idCliente);
                pedido = new Gson().fromJson(dadosJson, Pedido[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.contains("[]")){
                    btn_check.setVisibility(View.INVISIBLE);
                    txt_instrucao.setVisibility(View.INVISIBLE);
                    btn_acompanhar.setVisibility(View.VISIBLE);
                }else{
                    btn_acompanhar.setVisibility(View.INVISIBLE);
                }
            }
        }.execute();

    }


}
