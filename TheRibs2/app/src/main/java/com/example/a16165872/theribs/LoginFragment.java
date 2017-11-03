package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Arrays;

public class LoginFragment extends Fragment {

    Button btn_logar;
    EditText edit_cpf;
    EditText edit_senha;

    String url = "";
    String parametros = "";

    SharedPreferences preferences;



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        btn_logar = v.findViewById(R.id.btn_logar);
        edit_cpf = v.findViewById(R.id.edit_cpf);
        edit_senha = v.findViewById(R.id.edit_senha);

        configurarClickBotaoLogar();

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        return v;
    }

    private void configurarClickBotaoLogar() {
        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cpf = edit_cpf.getText().toString();
                String senha = edit_senha.getText().toString();

                if(cpf.isEmpty() || senha.isEmpty()){
                    Snackbar.make(view, "Preencha todas as iformações para acessar a plataforma", Snackbar.LENGTH_SHORT).show();
                }else{
                    parametros = "?cpf=" + cpf +"&senha=" + senha;
                    //url = getString(R.string.link)+"logar.php";

                    //Logar();

                    if(cpf.equals("123456789") && senha.equals("theribs")){
                        Intent abrirGarçom = new Intent(getContext(), GarcomActivity.class);
                        startActivity(abrirGarçom);
                    }else if(cpf.equals("44417658862") && senha.equals("bcd127")) {
                        Intent intent = new Intent(getContext(), HomeActivity.class);
                        startActivity(intent);

                    }

                }
            }
        });
    }

    private void Logar(){

        new AsyncTask<Void, Void, Void>(){

            String dadosJson;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get("http://192.168.15.4:8888/Login" + parametros);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(dadosJson.contains("'id_funcionario'")){
                    Toast.makeText(getContext(), "É um funcionário", Toast.LENGTH_LONG).show();
                }else if(dadosJson.contains("'id_cliente'")){
                    Toast.makeText(getContext(), "É um cliente", Toast.LENGTH_LONG).show();
                }


            }

        }.execute();
    }

   /* private class SolicitaDados extends  AsyncTask<String, Void, String>{


        Funcionario funcionario[];
        Cliente cliente[];
        @Override
        protected String doInBackground(String... strings) {

            String dadosJson = HttpConnection.get("http://192.168.15.4:8888/Login" + parametros);



            return null;
        }

        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("login_ok")){



            }else{
                Snackbar.make(getView(), "Usuário ou senha incorretos", Snackbar.LENGTH_LONG).show();
            }

        }
    }*/


}
