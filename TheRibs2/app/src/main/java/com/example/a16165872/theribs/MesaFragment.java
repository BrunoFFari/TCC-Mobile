package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


public class MesaFragment extends Fragment {

    GridView gridView;
    MesaAdapter adapter;


    Funcionario funcionario[];
    int idRestaurante;

    int id;
    public MesaFragment(int id){
        this.id = id;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mesa, container, false);

        gridView = rootView.findViewById(R.id.list_item);


         adapter = new MesaAdapter(
                getContext(),
                R.layout.adpter_mesa,
                new ArrayList<Mesa>()
        );


        gridView.setAdapter(adapter);


        if(Internet.VerificarConexao(getContext())){
            BusvarFuncionario();
            buscarMesas();
        }else{
            Snackbar.make(getView(), "Sua internet já foi, trás ela de volta, a gente te espera.", Snackbar.LENGTH_LONG).show();
        }


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Mesa mesa = adapter.getItem(i);
                Intent intent = new Intent(getContext(), AbrirPedidoActivity.class);
                intent.putExtra("idMesa", mesa.getId_mesa());
                intent.putExtra("idGarcom", id);
                startActivity(intent);
            }
        });



        return  rootView;
    }

    public  void BusvarFuncionario(){

        new AsyncTask<Void, Void, Void>(){

            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarFuncionarioID?id="+ id);

                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.contains("[]")){
                    funcionario = new Gson().fromJson(dadosJson, Funcionario[].class);
                    idRestaurante = funcionario[0].getId_restaurante();
                }else{
                    Toast.makeText(getContext(), "to aqui", Toast.LENGTH_LONG).show();
                }

            }
        }.execute();

    }

    public void buscarMesas(){

        new AsyncTask<Void, Void, Void>(){

            Mesa mesa[];
            ProgressDialog dialog;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(getContext(), "Estamos Trabalhando", "buscando as mesas...");
            }

            @Override
            protected Void doInBackground(Void... voids) {

                SystemClock.sleep(1000);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) +
                        "/BuscarMesasDisponiveis?id="+ idRestaurante);

                mesa = new Gson().fromJson(dadosJson, Mesa[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                try{
                    adapter.clear();
                    adapter.addAll(Arrays.asList(mesa));

                    dialog.dismiss();
                }catch (Exception e){

                }

            }


        }.execute();
    }

}
