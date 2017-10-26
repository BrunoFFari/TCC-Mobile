package com.example.a16165872.theribs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MesaFragment extends Fragment {

    GridView gridView;
    List<Mesa> mesas = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mesa, container, false);

        gridView = rootView.findViewById(R.id.list_item);

        mesas.add(new Mesa(1, "01", "4", 1));
        mesas.add(new Mesa(1, "02", "4", 1));
        mesas.add(new Mesa(1, "03", "4", 1));
        mesas.add(new Mesa(1, "04", "4", 1));
        mesas.add(new Mesa(1, "05", "4", 1));
        mesas.add(new Mesa(1, "06", "4", 1));
        mesas.add(new Mesa(1, "07", "4", 1));
        mesas.add(new Mesa(1, "08", "4", 1));
        mesas.add(new Mesa(1, "09", "4", 1));
        mesas.add(new Mesa(1, "10", "4", 1));
        mesas.add(new Mesa(1, "11", "4", 1));
        mesas.add(new Mesa(1, "12", "4", 1));
        mesas.add(new Mesa(1, "13", "4", 1));
        mesas.add(new Mesa(1, "14", "4", 1));
        mesas.add(new Mesa(1, "15", "4", 1));
        mesas.add(new Mesa(1, "16", "4", 1));


        MesaAdapter adapter = new MesaAdapter(
                getContext(),
                R.layout.adpter_mesa,
                mesas
        );


        gridView.setAdapter(adapter);


        return  rootView;
    }

}
