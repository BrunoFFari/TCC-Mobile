package com.example.a16165872.theribs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bruno on 15/10/2017.
 */

public class ReservaDisponivelAdpter extends ArrayAdapter<ReservaDisponivel>{

    int resource;

    public ReservaDisponivelAdpter(@NonNull Context context, @LayoutRes int resource, List<ReservaDisponivel> objects) {
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

        ReservaDisponivel rd = getItem(position);

        if(rd != null){

            TextView txt_nome_mesa = v.findViewById(R.id.txt_nome_mesa);
            TextView txt_nome_periodo = v.findViewById(R.id.txt_periodo);

            txt_nome_mesa.setText(rd.getNome_mesa());
            txt_nome_periodo.setText(rd.getNome_periodo() + " - " + rd.getIntervalo());


        }

        return v;
    }
}
