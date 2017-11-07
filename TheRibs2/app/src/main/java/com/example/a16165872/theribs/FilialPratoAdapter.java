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

import com.example.a16165872.theribs.Filial;
import com.example.a16165872.theribs.Ingrediente;
import com.example.a16165872.theribs.R;

import java.util.List;

/**
 * Created by 16165872 on 07/11/2017.
 */

public class FilialPratoAdapter extends ArrayAdapter<Filial> {

    int resource;
    public FilialPratoAdapter(Context context, int resource, List<Filial> objects) {
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

        Filial filial = getItem(position);

        if (filial != null) {

            TextView txt_filial = v.findViewById(R.id.txt_filial);

            txt_filial.setText(filial.getNome());

        }


        return v;
    }
}
