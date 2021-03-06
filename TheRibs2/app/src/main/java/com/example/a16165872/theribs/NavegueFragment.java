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
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NavegueFragment extends Fragment {


    ListView listView;

    FilialAdapter adpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_navegue, container, false);

        listView = rootView.findViewById(R.id.list_item);

        adpater = new FilialAdapter(
            getContext(),
            R.layout.adpter_filiais,
                new ArrayList<Filial>());

        listView.setAdapter(adpater);


        if(Internet.VerificarConexao(getContext())){
            buscarFiliais();
        }else{
            Snackbar.make(rootView, "Sua internet já foi, trás ela de volta, a gente espera!", Snackbar.LENGTH_LONG).show();
        }

        configurarClickLista();

        return rootView;
    }

    private void configurarClickLista() {



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Filial filial = adpater.getItem(position);

                Intent intent = new Intent(getContext(), RestauranteActivity.class);
                intent.putExtra("id", filial.id_restaurante);
                startActivity(intent);

            }
        });
    }

    private void buscarFiliais(){

        new AsyncTask<Void, Void, Void>(){

            Filial filial[];

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "buscanco as filiais");
            }

            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);
                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarFiliais");
                filial = new Gson().fromJson(dadosJson, Filial[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.dismiss();

                try{
                    adpater.clear();
                    adpater.addAll(Arrays.asList(filial));
                }catch (Exception e){
                    Log.d("ErroNavegue", e.getMessage());
                }




            }
        }.execute();
    }


}
