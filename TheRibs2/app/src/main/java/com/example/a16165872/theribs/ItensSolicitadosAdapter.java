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
 * Created by Bruno on 16/11/2017.
 */

public class ItensSolicitadosAdapter extends ArrayAdapter<ItemPedido>{


    int resource;
    public ItensSolicitadosAdapter(@NonNull Context context, @LayoutRes int resource, List<ItemPedido> objects) {
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

        ItemPedido item = getItem(position);

        if (item != null) {

            TextView txt_nome_prato = v.findViewById(R.id.txt_nome_prato);
            TextView txt_quantidade = v.findViewById(R.id.txt_quantidade);

            txt_nome_prato.setText(item.getNome());
            txt_quantidade.setText("Quantidade: " + item.getQtd());

        }

        return v;
    }
}
