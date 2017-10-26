package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.a16165872.theribs.R;

import java.util.ArrayList;
import java.util.List;


public class AtendimentoFragment extends Fragment {

    GridView gridView;
    List<Mesa> mesas = new ArrayList<>();

    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_atendimento, container, false);


        gridView = v.findViewById(R.id.list_item);

        mesas.add(new Mesa(1, "01", "4", 1));
        mesas.add(new Mesa(1, "02", "4", 1));
        mesas.add(new Mesa(1, "07", "4", 1));
        mesas.add(new Mesa(1, "09", "4", 1));
        mesas.add(new Mesa(1, "12", "4", 1));
        mesas.add(new Mesa(1, "13", "4", 1));
        mesas.add(new Mesa(1, "16", "4", 1));

        MesaAdapter adapter = new MesaAdapter(
                getContext(),
                R.layout.adpter_mesa,
                mesas
        );

        gridView.setAdapter(adapter);
        registerForContextMenu(gridView);

        configurarClickLista();

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Selecione a ação desejada");
        menu.add(0, v.getId(), 0, "Fechar a Conta");
        menu.add(0, v.getId(), 0, "Ecluir");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals("Apagar")){

            Snackbar.make(v, "Ação de Apagar", Snackbar.LENGTH_SHORT).show();
        }else if(item.getTitle().equals("Ver mais")){
            Snackbar.make(v, "Ação de ver mais", Snackbar.LENGTH_SHORT).show();
        }


        return true;
    }

    private void configurarClickLista() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getContext(), AcompanhamentoActivity.class);
                startActivity(intent);

            }
        });
    }


}
