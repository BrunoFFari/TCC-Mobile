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

import java.util.List;

/**
 * Created by Bruno on 21/10/2017.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    int resource;
    public PedidoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pedido> objects) {
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

        Pedido pedido = getItem(position);

        if(pedido != null){
            TextView txt_quantidade = v.findViewById(R.id.txt_quantidade);
            TextView txt_nome_prato = v.findViewById(R.id.txt_nome_prato);
            ImageView img_prato = v.findViewById(R.id.img_prato);

            txt_nome_prato.setText(pedido.getNome_prato());
            txt_quantidade.setText("Quantidade: " + pedido.getQuantidade());
            img_prato.setBackgroundResource(pedido.getImagem_prato());
        }


        return  v;
    }
}
