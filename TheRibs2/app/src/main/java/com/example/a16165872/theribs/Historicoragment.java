package com.example.a16165872.theribs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Historicoragment extends Fragment {

    ListView listView;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_historicoragment, container, false);

        listView = v.findViewById(R.id.list_item);
        String[] teste = {"20/10/2017 - Restaurante Tal. Mesa: 01",
                "20/10/2017 - Restaurante Tal. Mesa: 01",
                "20/10/2017 - Restaurante Tal. Mesa: 01",
                "20/10/2017 - Restaurante Tal. Mesa: 01",
                "20/10/2017 - Restaurante Tal. Mesa: 01"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                teste
        );

        listView.setAdapter(adapter);

        registerForContextMenu(listView);



        return v;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Selecione a ação desejada");
        menu.add(0, v.getId(), 0, "Apagar");
        menu.add(0, v.getId(), 0, "Ver mais");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getTitle().equals("Apagar")){

            Snackbar.make(v, "Ação de Apagar", Snackbar.LENGTH_SHORT).show();
        }else if(item.getTitle().equals("Ver mais")){
            Snackbar.make(v, "Ação de ver mais", Snackbar.LENGTH_SHORT).show();
        }


        return true;
    }
}
