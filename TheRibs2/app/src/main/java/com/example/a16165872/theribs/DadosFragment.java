package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class DadosFragment extends Fragment {

    int id;
    public DadosFragment(int id){
        this.id = id;
    }

    TextView nome_pessoa, cpf_pessoa;
    EditText edit_email, edit_telefone, edit_celular, edit_cep, edit_numero, edit_endereco, edit_senha2,edit_senha;
    ImageView img_user;
    Button btn_salvar;
    String cepBuscar, parametros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dados, container, false);

        nome_pessoa = v.findViewById(R.id.nome_pessoa);
        cpf_pessoa = v.findViewById(R.id.cpf_pessoa);
        edit_email = v.findViewById(R.id.edit_email);
        edit_telefone = v.findViewById(R.id.edit_telefone);
        edit_celular = v.findViewById(R.id.edit_celular);
        edit_cep = v.findViewById(R.id.edit_cep);
        edit_numero = v.findViewById(R.id.edit_numero);
        edit_endereco = v.findViewById(R.id.edit_endereco);
        edit_senha2 = v.findViewById(R.id.edit_senha2);
        edit_senha = v.findViewById(R.id.edit_senha);
        btn_salvar = v.findViewById(R.id.btn_salvar);
        img_user = v.findViewById(R.id.img_user);

        edit_cep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                cepBuscar = edit_cep.getText().toString();

                if(cepBuscar.length() == 8){
                    buscarEdereco();
                }
            }
        });

        buscarDados();
        configurarClickBotaoSalvar();


        return  v;
    }

    private void configurarClickBotaoSalvar() {
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Internet.VerificarConexao(getContext())){

                    String celular, telefone, email, cep, numero, senha, senha2;


                    if(edit_senha.getText().toString().isEmpty() && edit_senha2.getText().toString().isEmpty()){

                        if(edit_celular.getText().toString().isEmpty() || edit_email.getText().toString().isEmpty()
                                || edit_cep.getText().toString().isEmpty() || edit_numero.getText().toString().isEmpty()
                                || edit_endereco.getText().toString().isEmpty()){

                            Snackbar.make(view, "Preencha todos os dados para continuar.", Snackbar.LENGTH_LONG).show();
                        }else{

                            celular = edit_celular.getText().toString();
                            telefone = edit_telefone.getText().toString();
                            email = edit_email.getText().toString();
                            cep = edit_cep.getText().toString();
                            numero = edit_numero.getText().toString();

                            parametros = "celular="+celular+"&telefone="+telefone+"&email="+email+"&cep="+cep+"&numero="+numero+"&id="+id;

                        }


                        AlterarDados();

                    }else{

                        celular = edit_celular.getText().toString();
                        telefone = edit_telefone.getText().toString();
                        email = edit_email.getText().toString();
                        cep = edit_cep.getText().toString();
                        numero = edit_numero.getText().toString();
                        senha = edit_senha.getText().toString();
                        senha2 = edit_senha2.getText().toString();

                        if(senha.equals(senha2)){

                            parametros = "celular="+celular+"&telefone="+telefone+"&email="+email+"&cep="+cep+"&numero="+numero+"&senha="+senha+"&id="+id;

                            AlterarDados();

                        }else{

                            Snackbar.make(view, "Senhas não conferem, corrija e tente novamente.", Snackbar.LENGTH_LONG).show();

                        }
                    }


                }else{

                    Snackbar.make(view, "Sua internet já foi, trás ela de volta, a gente te espera.", Snackbar.LENGTH_LONG).show();

                }


            }
        });
    }

    public void buscarDados(){

        new AsyncTask<Void, Void, Void>(){


            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos Trabalhando", "buscando seus dados...");
            }

            Cliente cliente[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarDadosCliente?id=" + id);
                cliente = new Gson().fromJson(dadosJson, Cliente[].class);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                try{
                    nome_pessoa.setText(cliente[0].getNome());
                    cpf_pessoa.setText(cliente[0].getCpf());
                    edit_celular.setText(cliente[0].getCelular());
                    edit_email.setText(cliente[0].getEmail());
                    edit_cep.setText(cliente[0].getCep());
                    edit_numero.setText(cliente[0].getNumero());
                    edit_telefone.setText(cliente[0].getTelefone());
                }catch (Exception e){
                    Log.d("ErroBuscarDados", e.getMessage());

                }

                Picasso.with(getContext())
                        .load(cliente[0].getFoto())
                        .transform(new CircleTransform())
                        .into(img_user);



                dialog.dismiss();

            }

        }.execute();

    }

    private void buscarEdereco(){

        new AsyncTask<Void, Void, Void>(){


            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "buscando seu endereço...");
            }

            EnderecoJson enderecoJson;

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get("http://viacep.com.br/ws/"+ cepBuscar +"/json/");
                enderecoJson = new Gson().fromJson(dadosJson, EnderecoJson.class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                try{
                    edit_endereco.setText(enderecoJson.getLogradouro()
                            + " - " + enderecoJson.getLocalidade()
                            + " (" + enderecoJson.getUf() + ")"  );

                }catch (Exception e){
                    Log.d("ErroBuscarEndercoClient", e.getMessage());
                }
                dialog.dismiss();
            }
        }.execute();

    }

    private void AlterarDados(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "Alterando os dados...");
            }

            String retorno;
            @Override
            protected Void doInBackground(Void... voids) {

                if(parametros.contains("&senha=")){
                    retorno = Conexao.postDados(getString(R.string.link_node) + "/AlterarDadosSenha", parametros);
                }else{
                    retorno = Conexao.postDados(getString(R.string.link_node) + "/AlterarUsuarioSemSenha", parametros);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!retorno.isEmpty()){

                    Snackbar snackbar = Snackbar
                            .make(getView(), retorno, Snackbar.LENGTH_LONG)
                            .setAction("Volte a navegar", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeActivity.class);
                                    intent.putExtra("idCliente", id);
                                    intent.putExtra("validacao", 1);
                                    startActivity(intent);
                                }
                            });

                    snackbar.show();

                }


                dialog.dismiss();

            }
        }.execute();

    }



}
