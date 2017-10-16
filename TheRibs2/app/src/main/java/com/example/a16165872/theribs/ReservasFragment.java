package com.example.a16165872.theribs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ReservasFragment extends Fragment {

    List<ReservaDisponivel> reservas = new ArrayList<>();
    ListView listView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reservas, container, false);

        listView = v.findViewById(R.id.list_item);

        if(!reservas.isEmpty()){
            reservas.clear();
        }

        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "01", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "02", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "03", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "04", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "05", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "06", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "07", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "08", "11h até 15h"));
        reservas.add(new ReservaDisponivel(1 , 1, "Almoço", "09", "11h até 15h"));

        ReservaDisponivelAdpter adpter = new ReservaDisponivelAdpter(
            getContext(),
            R.layout.adpter_reservas,
            reservas
        );

        listView.setAdapter(adpter);



        return v;
    }


}
