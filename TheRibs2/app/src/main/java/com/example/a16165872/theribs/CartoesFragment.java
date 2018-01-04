package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a16165872.theribs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CartoesFragment extends Fragment {

    ListView listView;
    CartaoAdapter adapter;

    int id;
    public CartoesFragment (int id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cartoes, container, false);

        listView = rootView.findViewById(R.id.list_item);



        adapter = new CartaoAdapter(
                getContext(),
                R.layout.adpter_cartao,
                new ArrayList<Cartao>()
        );

        listView.setAdapter(adapter);

        buscarCartoes();


        return  rootView;
    }


    public  void buscarCartoes(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "Buscando os seus cartões");

            }

            Cartao cartao[];
            @Override
            protected Void doInBackground(Void... voids) {

                SystemClock.sleep(500);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarCartoes?id="+ id);
                cartao = new Gson().fromJson(dadosJson, Cartao[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                try{
                    adapter.clear();
                    adapter.addAll(Arrays.asList(cartao));
                }catch (Exception e){
                    Log.d("Erro", e.getMessage());
                }

                dialog.dismiss();


            }
        }.execute();

    }

}
