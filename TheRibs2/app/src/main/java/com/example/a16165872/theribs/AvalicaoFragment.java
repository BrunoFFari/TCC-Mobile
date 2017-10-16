package com.example.a16165872.theribs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class AvalicaoFragment extends Fragment {


    List<Avaliacoes> avaliacoes = new ArrayList<>();
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_avalicao, container, false);

        listView = v.findViewById(R.id.list_item);

        avaliacoes.add(new Avaliacoes(4, "Essa é a nota que noss clientes nos deram sobre os nosso prato!"));
        avaliacoes.add(new Avaliacoes(5, "Essa é a nota que noss clientes nos deram sobre os nosso prato!"));
        avaliacoes.add(new Avaliacoes(3, "Essa é a nota que noss clientes nos deram sobre os nosso prato!"));
        avaliacoes.add(new Avaliacoes(4, "Essa é a nota que noss clientes nos deram sobre os nosso prato!"));
        avaliacoes.add(new Avaliacoes(5, "Essa é a nota que noss clientes nos deram sobre os nosso prato!"));


        AvaliacoesAdpter adpter = new AvaliacoesAdpter(
                getContext(),
                R.layout.adpter_avaliacao,
                avaliacoes
        );


        listView.setAdapter(adpter);



        return v;
    }

}
