package com.example.a16165872.theribs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.a16165872.theribs.R;

import java.util.ArrayList;
import java.util.List;


public class CartoesFragment extends Fragment {

    ListView listView;
    List<Cartao> cartoes = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cartoes, container, false);

        listView = rootView.findViewById(R.id.list_item);

        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));
        cartoes.add(new Cartao(1, "0000.0000.0000.0000", "Cremiudo Raimundo", "986", "12/2018" ));

        CartaoAdapter adapter = new CartaoAdapter(
                getContext(),
                R.layout.adpter_cartao,
                cartoes
        );

        listView.setAdapter(adapter);



        return  rootView;
    }

}
