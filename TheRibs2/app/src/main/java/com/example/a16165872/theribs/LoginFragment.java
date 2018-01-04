package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Arrays;

public class LoginFragment extends Fragment {

    Button btn_logar;
    EditText edit_cpf;
    EditText edit_senha;
    CheckBox ch_funcionario;

    String url = "";
    String parametros = "";




    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_login, container, false);

        btn_logar = v.findViewById(R.id.btn_logar);
        edit_cpf = v.findViewById(R.id.edit_cpf);
        edit_senha = v.findViewById(R.id.edit_senha);
        ch_funcionario = v.findViewById(R.id.ch_funcionario);

        configurarClickBotaoLogar();

        return v;
    }

    private void configurarClickBotaoLogar() {
        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Internet.VerificarConexao(getContext())){

                    String cpf = edit_cpf.getText().toString();
                    String senha = edit_senha.getText().toString();

                    if(cpf.isEmpty() || senha.isEmpty()){
                        Snackbar.make(view, "Preencha todas as iformações para acessar a plataforma", Snackbar.LENGTH_SHORT).show();
                    }else{
                        parametros = "?cpf=" + cpf +"&senha=" + senha;

                        if(ch_funcionario.isChecked()){
                            LogarFuncionario();
                        }else {
                            Logar();

                        }

                    }

                }else{

                    Snackbar.make(v, "Sua internet já foi, trás ela de volta, agente espera.", Snackbar.LENGTH_LONG).show();

                }


            }
        });
    }

    private void Logar(){

        new AsyncTask<Void, Void, Void>(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos trabalhando", "Verificando os seus dados...");
            }

            Cliente cliente[];
            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {
                SystemClock.sleep(1000);
                String url =  getString(R.string.link_node) + "/Login" + parametros;
                dadosJson = HttpConnection.get(url);
                cliente = new Gson().fromJson(dadosJson, Cliente[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(dadosJson.contains("[]")){
                    Toast.makeText(getContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    intent.putExtra("idCliente", cliente[0].getId_cliente());
                    intent.putExtra("nomeCliente", cliente[0].getNome());
                    intent.putExtra("fotoCliente", cliente[0].getFoto());
                    startActivity(intent);
                }

                dialog.dismiss();
            }

        }.execute();
    }

    private void LogarFuncionario(){

        new AsyncTask<Void, Void, Void>(){

            Funcionario[] funcionario;

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/LoginFuncionario" + parametros);

                if(!dadosJson.contains("[]")){
                    funcionario = new Gson().fromJson(dadosJson, Funcionario[].class);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(funcionario == null ){
                    Toast.makeText(getContext(), "Usuário ou senha incorretos", Toast.LENGTH_LONG).show();
                }else {

                    Intent intent = new Intent(getContext(), GarcomActivity.class);
                    intent.putExtra("Funcionario", funcionario[0].getId_funcionario());
                    startActivity(intent);

                }


            }
        }.execute();
    }




}
