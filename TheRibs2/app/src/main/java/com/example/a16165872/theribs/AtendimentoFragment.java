package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a16165872.theribs.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AtendimentoFragment extends Fragment {

    GridView gridView;
    MesaAdapter adapter;
    ImageView img_carregando;
    TextView txt_carregando;

    Mesa mesaSelecionada;

    View v;

    int id;
    public  AtendimentoFragment(int id){
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_atendimento, container, false);


        gridView = v.findViewById(R.id.list_item);
        txt_carregando = v.findViewById(R.id.txt_carregando);
        img_carregando = v.findViewById(R.id.img_carregando);


        adapter = new MesaAdapter(
                getContext(),
                R.layout.adpter_mesa,
                new ArrayList<Mesa>()
        );

        gridView.setAdapter(adapter);
        registerForContextMenu(gridView);

        configurarClickLista();

        if(Internet.VerificarConexao(getContext())){
            BuscarMesasAtendimento();
        }else{

            Snackbar.make(getView(),
                    "Sua internet já foi, trás ela de volta, a gente te espera",
                    Snackbar.LENGTH_LONG).show();
        }


        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Selecione a ação desejada");
        menu.add(0, v.getId(), 0, "Fechar a Conta");
        menu.add(0, v.getId(), 0, "Ecluir");
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

    private void configurarClickLista() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {

                mesaSelecionada = adapter.getItem(position);

                Intent intent = new Intent(getContext(), ItensPedidoActivity.class);
                intent.putExtra("idMesa", mesaSelecionada.getId_mesa());
                intent.putExtra("idGarcom", id);
                intent.putExtra("idCliente", mesaSelecionada.getId_cliente());
                intent.putExtra("idPedido", mesaSelecionada.getId_pedido());
                startActivity(intent);

            }
        });
    }

    private void BuscarMesasAtendimento(){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPreExecute() {

                gridView.setVisibility(View.INVISIBLE);

                super.onPreExecute();
            }

            Mesa mesa[];
            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarMesasAtendimento?id=" + id);
                mesa = new Gson().fromJson(dadosJson, Mesa[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                if(dadosJson.contains("[]")){
                    img_carregando.setImageResource(R.drawable.garcom);
                    txt_carregando.setText("Você ainda não está atendendo nenhuma mesa");

                }else{

                    adapter.clear();
                    adapter.addAll(Arrays.asList(mesa));
                    gridView.setVisibility(View.VISIBLE);
                    img_carregando.setVisibility(View.INVISIBLE);
                    txt_carregando.setVisibility(View.INVISIBLE);
                }



            }
        }.execute();

    }

}
