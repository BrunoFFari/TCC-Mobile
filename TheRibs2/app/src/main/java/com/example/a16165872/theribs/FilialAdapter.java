package com.example.a16165872.theribs;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 16165872 on 05/10/2017.
 */

public class FilialAdapter extends ArrayAdapter<Filial> {

    int resource;

    public FilialAdapter(Context context, int resource, List<Filial> objects) {
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

        Filial filial = getItem(position);

        if(filial != null){

            TextView nome_flial = v.findViewById(R.id.txt_nome_filial);
            TextView endereco_filial = v.findViewById(R.id.txt_endereco_filial);
            TextView telefone_filial = v.findViewById(R.id.txt_telefone_filial);
            TextView status_filial =  v.findViewById(R.id.txt_status_filial);
            ImageView img_filial = v.findViewById(R.id.img_filial);

            nome_flial.setText(filial.getNome());
            endereco_filial.setText(filial.getEndereco() + ", " + filial.getNumero());
            telefone_filial.setText(filial.getTelefone());

            Picasso.with(getContext())
                    .load(filial.getLocal_imagem())
                    .into(img_filial);

            if(filial.getStatus().equals("Aberto")){
                status_filial.setText(filial.getStatus());
                status_filial.setTextColor(Color.parseColor("#7CFC00"));
            }else{
                status_filial.setText(filial.getStatus());
                status_filial.setTextColor(Color.parseColor("#FF0000"));
            }

        }

        return v;
    }
}
