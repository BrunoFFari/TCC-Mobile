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

import com.example.a16165872.theribs.Cartao;

import java.util.List;

/**
 * Created by Bruno on 20/10/2017.
 */

public class CartaoAdapter extends ArrayAdapter<Cartao>
{

    int resource;
    public CartaoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Cartao> objects) {
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

        Cartao cartao = getItem(position);

        if (cartao != null) {

            TextView numero_cartao = v.findViewById(R.id.numero_cartao);
            TextView nome_cartao = v.findViewById(R.id.nome_cartao);

            numero_cartao.setText(cartao.getNumero_cartao());
            nome_cartao.setText(cartao.getNome_cartao());

        }

        return v;
    }
}
