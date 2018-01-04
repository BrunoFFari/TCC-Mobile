package com.example.a16165872.theribs;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class InformacoesFragment extends Fragment {

    int id;

    public InformacoesFragment(int id){
        this.id = id;
    }

    Button btnLigar, btnLocalizar;
    TextView txt_telefone, txt_cnpj, txt_cep, txt_endereco, txt_nome;
    String cepBuscar;
    String numero;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =   inflater.inflate(R.layout.fragment_informacoes, container, false);

        findViews(v);
        configurarClickBotoes();
        BuscarrDados();
        buscarEdereco();

        return v;
    }

    private void configurarClickBotoes() {
        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);



                if(!numero.isEmpty()){
                    intent.setData(Uri.parse("tel:" + numero));
                }

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED){

                    Snackbar.make(getView(), "Conceda permissão para podermos realizar a ligação", Snackbar.LENGTH_LONG
                            ).show();

                }else{
                    startActivity(intent);
                }

            }
        });

        btnLocalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:47.4925,19.0513"));
                Intent chooser = Intent.createChooser(intent, "Lauch Maps");
                startActivity(chooser);
            }
        });
    }

    private void findViews(View v){
        btnLigar = v.findViewById(R.id.btn_ligar);
        btnLocalizar = v.findViewById(R.id.btnLocalizar);
        txt_cep = v.findViewById(R.id.txt_cep);
        txt_telefone = v.findViewById(R.id.txt_telefone);
        txt_cnpj = v.findViewById(R.id.txt_cnpj);
        txt_endereco = v.findViewById(R.id.txt_endereco);
        txt_nome = v.findViewById(R.id.txt_nome);
    }

    private void BuscarrDados(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos Trabalhando", "bsucando os dados");
            }

            Filial filial[];
            @Override
            protected Void doInBackground(Void... voids) {
                String dados = HttpConnection.get(getString(R.string.link_node) + "/BuscarDadosFilial?id="+id);
                filial = new Gson().fromJson(dados, Filial[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(filial != null){
                    txt_nome.setText(filial[0].getNome());
                    txt_cep.setText(filial[0].getCep());
                    cepBuscar = filial[0].getCep();
                    txt_cnpj.setText(filial[0].getCnpj());
                    txt_telefone.setText(filial[0].getTelefone());
                    numero = filial[0].getTelefone();

                    dialog.dismiss();
                }

            }

        }.execute();
    }

    private void buscarEdereco(){

        new AsyncTask<Void, Void, Void>(){

            EnderecoJson enderecoJson;
            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos Trabalahndo","buscando o endereço..." );
            }

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get("http://viacep.com.br/ws/"+ txt_cep.getText().toString() +"/json/");
                enderecoJson = new Gson().fromJson(dadosJson, EnderecoJson.class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                try{
                    txt_endereco.setText(enderecoJson.getLogradouro() + " - " + enderecoJson.getLocalidade() + " (" + enderecoJson.getUf() + ")"  );

                }catch (Exception erro){

                }

                dialog.dismiss();


            }
        }.execute();

    }


}
