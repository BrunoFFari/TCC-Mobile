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
 * Created by 16165872 on 11/10/2017.
 */

public class PratoAdpter extends ArrayAdapter<Prato>{

    int resource;

    public PratoAdpter(@NonNull Context context, @LayoutRes int resource, List<Prato> objects) {
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

        Prato prato = getItem(position);

        if(prato != null){

            ImageView img_prato = v.findViewById(R.id.img_prato);
            TextView txt_nome_prato = v.findViewById(R.id.txt_nome_prato);

            txt_nome_prato.setText(prato.getNome());

            Picasso.with(getContext())
                    .load(prato.getUrl())
                    .into(img_prato);

        }

        return v;
    }
}
