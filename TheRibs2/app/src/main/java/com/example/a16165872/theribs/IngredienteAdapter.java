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
 * Created by 16165872 on 07/11/2017.
 */

public class IngredienteAdapter extends ArrayAdapter<Ingrediente> {

    int resource;

    public IngredienteAdapter(@NonNull Context context, @LayoutRes int resource, List<Ingrediente> objects) {
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

        Ingrediente ingrediente = getItem(position);

        if (ingrediente != null) {

            TextView txt_ingrediente = v.findViewById(R.id.txt_ingrediente);

            txt_ingrediente.setText(ingrediente.getNome());

        }

        return v;
    }




}
