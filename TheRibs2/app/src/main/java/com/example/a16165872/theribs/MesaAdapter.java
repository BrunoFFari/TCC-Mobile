package com.example.a16165872.theribs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Bruno on 21/10/2017.
 */

public class MesaAdapter extends ArrayAdapter<Mesa> {

    int resource;
    public MesaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Mesa> objects) {
        super(context, resource, objects);

        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        Mesa mesa = getItem(position);

        if(mesa != null){

            TextView txt_lugares = v.findViewById(R.id.txt_lugares);
            TextView txt_mesa = v.findViewById(R.id.txt_mesa);

            txt_lugares.setText(mesa.getLugares() + " - Lugares");
            txt_mesa.setText(mesa.getNumero());

        }


        return v;
    }
}
