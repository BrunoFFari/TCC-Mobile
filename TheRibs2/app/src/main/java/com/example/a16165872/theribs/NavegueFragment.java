package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

        buscarFiliais();
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

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarFiliais");
                filial = new Gson().fromJson(dadosJson, Filial[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adpater.clear();
                adpater.addAll(Arrays.asList(filial));

            }
        }.execute();
    }


}
