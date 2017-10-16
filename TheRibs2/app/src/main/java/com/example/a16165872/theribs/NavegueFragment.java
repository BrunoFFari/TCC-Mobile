package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class NavegueFragment extends Fragment {

    List<Filial> filial = new ArrayList<>();
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_navegue, container, false);

        listView = rootView.findViewById(R.id.list_item);

        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Aberto", "504", R.drawable.restaurante1));
        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Fechado", "504", R.drawable.restaurante2));
        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Aberto", "504", R.drawable.restaurante3));
        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Aberto", "504", R.drawable.restaurante4));
        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Fechado", "504", R.drawable.restaurante5));
        filial.add(new Filial("The Ribs - Steakhouse (São Paulo)", "Av. Paulista", "(11)4002-8922", "Aberto", "504", R.drawable.restaurante6));

        FilialAdapter adpater = new FilialAdapter(
            getContext(),
            R.layout.adpter_filiais,
                filial);

        listView.setAdapter(adpater);


        configurarClickLista();

        return rootView;
    }

    private void configurarClickLista() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), RestauranteActivity.class);
                startActivity(intent);

            }
        });
    }


}
