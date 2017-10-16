package com.example.a16165872.theribs;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bruno on 13/10/2017.
 */

public class EventoAdpter extends ArrayAdapter<Evento> {

    int resource;

    public EventoAdpter(@NonNull Context context, @LayoutRes int resource, List<Evento> objects) {
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

        Evento evento = getItem(position);

        if(evento != null){

            TextView nome_evento = v.findViewById(R.id.txt_nome_evento);
            TextView descricao_evento = v.findViewById(R.id.txt_descricao);
            ImageView img_evento = v.findViewById(R.id.img_evento);


            nome_evento.setText(evento.getNome());
            descricao_evento.setText(evento.getDescricao() + ", Acontecerá em: " + evento.getData().toString());

            img_evento.setBackgroundResource(evento.getImagem());

        }

        return v;
    }


}
