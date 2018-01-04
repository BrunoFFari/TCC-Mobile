package com.example.a16165872.theribs;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
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
 * Created by Bruno on 15/11/2017.
 */

public class ItensPedidosAdapter extends ArrayAdapter<Prato>{

    int resource;

    public ItensPedidosAdapter(@NonNull Context context, @LayoutRes int resource, List<Prato> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        Prato pratos = getItem(position);

        if (pratos != null) {

            TextView txt_nome_prato = v.findViewById(R.id.txt_nome_prato);
            ImageView img_prato = v.findViewById(R.id.img_prato);
            TextView txt_preco_prato = v.findViewById(R.id.txt_preco_prato);

            txt_nome_prato.setText(pratos.getNome());
            Picasso.with(getContext())
                    .load("http://www.eatribstuff.com.br/" + pratos.getUrl())
                    .into(img_prato);

            txt_preco_prato.setText("R$" + pratos.getPreco());

        }

        return v;
    }
}
