package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class CadastroFragment extends Fragment {

    Button btn_cadastrar;
    EditText edit_nome, edit_cpf, edit_email, edit_telefone, edit_celular, edit_cep, edit_numero, edit_endereco, edit_senha, edit_senha2;
    String parametros;
    String cepBuscar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cadastro, container, false);

        findViews();

        configurarBuscaEndereco();
        ConfigurarClickBotaoCadastrar();


        return v;
    }

    private void configurarBuscaEndereco() {
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

                if(cepBuscar.length() == 8 ){

                    if(Internet.VerificarConexao(getContext())){
                        buscarEdereco();
                    }else{
                        Snackbar.make(v, "Não conseguimos buscar seu endereço, pois você está sem internet.", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    private void findViews(){

        btn_cadastrar = v.findViewById(R.id.btn_cadastrar);

        edit_nome = v.findViewById(R.id.edit_nome);
        edit_cpf = v.findViewById(R.id.edit_cpf);
        edit_email = v.findViewById(R.id.edit_email);
        edit_telefone = v.findViewById(R.id.edit_telefone);
        edit_celular = v.findViewById(R.id.edit_celular);
        edit_cep = v.findViewById(R.id.edit_cep);
        edit_endereco = v.findViewById(R.id.edit_endereco);
        edit_senha = v.findViewById(R.id.edit_senha);
        edit_senha2 = v.findViewById(R.id.edit_senha2);
        edit_numero = v.findViewById(R.id.edit_numero);

    }

    private void ConfigurarClickBotaoCadastrar(){
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(getContext())){
                    if( edit_nome.getText().toString().isEmpty() || edit_email.getText().toString().isEmpty()
                            || edit_cpf.getText().toString().isEmpty() || edit_celular.getText().toString().isEmpty()
                            || edit_cep.getText().toString().isEmpty() || edit_numero.getText().toString().isEmpty()
                            || edit_senha.getText().toString().isEmpty() || edit_senha2.getText().toString().isEmpty()){

                        Snackbar.make(getView(), "Preencha todas as informações para continuar", Snackbar.LENGTH_LONG).show();
                    }else{

                        if (edit_senha.getText().toString().equals(edit_senha2.getText().toString())){

                            String nome = edit_nome.getText().toString();
                            String email = edit_email.getText().toString();
                            String cpf = edit_cpf.getText().toString();
                            String celular = edit_celular.getText().toString();
                            String telefone = edit_telefone.getText().toString();
                            String cep = edit_cep.getText().toString();
                            String numero = edit_numero.getText().toString();
                            String senha = edit_senha.getText().toString();

                            parametros = "nome="+nome+"&email="+email+"&cpf="+cpf+"&celular="+celular+"&telefone="+telefone+"&cep="+cep+"&numero="+numero+"&senha="+senha;
                            cadastrarUsuario();

                        }else{
                            Snackbar.make(getView(), "Senhas não conferem", Snackbar.LENGTH_LONG).show();
                        }

                    }
                }else {
                    Snackbar.make(v, "Sua internet já foi, trás ela de volta, a gente espera!", Snackbar.LENGTH_LONG).show();
                }


            }
        });
    }

    private void buscarEdereco(){

        new AsyncTask<Void, Void, Void>(){

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
                    edit_endereco.setText(enderecoJson.getLogradouro() + " - " + enderecoJson.getLocalidade() + " (" + enderecoJson.getUf() + ")"  );

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.execute();

    }

    private void cadastrarUsuario(){

        new AsyncTask<Void, Void, Void>(){


            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "Eneviando seus dados...");
            }

            String resultado;
            @Override
            protected Void doInBackground(Void... voids) {
                resultado = Conexao.postDados(getString(R.string.link_node) + "/CadastrarUsuario", parametros );

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                try{
                    if(!resultado.isEmpty()){
                        Snackbar.make(getView(), resultado, Snackbar.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Não conseguimos fazer seu cadastro, tente novamente mais tarde", Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();


            }

        }.execute();
    }

}
