package com.example.a16165872.theribs;

import android.content.Context;
import android.support.annotation.IdRes;
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
 * Created by Bruno on 16/10/2017.
 */

public class AvaliacoesAdpter extends ArrayAdapter<Avaliacoes> {

    int resource;

    public AvaliacoesAdpter(@NonNull Context context, @LayoutRes int resource, List<Avaliacoes> objects) {
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

        Avaliacoes avaliacoes = getItem(position);

        if (avaliacoes != null) {



        }

        return v;
    }
}
