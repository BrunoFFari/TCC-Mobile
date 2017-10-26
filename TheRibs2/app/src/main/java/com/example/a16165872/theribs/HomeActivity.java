package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    Button btn_fechar;
    ImageView img_user;
    TextView nome_user;

    Context context;

    Cliente cliente;



    SharedPreferences preferences;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent abrirMeusDados = new Intent(context, MeusDadosActivity.class);
                    startActivity(abrirMeusDados);
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content, new NavegueFragment()).commit();

                    return true;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(context, CheckInActivity.class);
                    startActivity(intent);

                    return true;
                case R.id.navigation_sair:

                    return true;

            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        montarDialogInicial();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new NavegueFragment()).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*cliente.setId_cliente(Integer.parseInt(preferences.getString("id_cliente", "")));
        cliente.setNome(preferences.getString("nome", ""));
        cliente.setCelular(preferences.getString("celular", ""));
        cliente.setCep(preferences.getString("cep", ""));
        cliente.setCpf(preferences.getString("cpf", ""));
        cliente.setEmail(preferences.getString("email", ""));
        cliente.setFoto(preferences.getString("foto", ""));
        cliente.setNumero(preferences.getString("numero", ""));
        cliente.setTelefone(preferences.getString("telefone", ""));*/


    }

    private void montarDialogInicial() {
        View dialog = getLayoutInflater().inflate(R.layout.dialog_boas_vindas, null);

        img_user = dialog.findViewById(R.id.foto_usuario);
        nome_user = dialog.findViewById(R.id.txt_nome_usuario);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this);
        mBuilder.setView(dialog);

        //String url = cliente.getFoto();

        Picasso.with(this)
                .load(R.drawable.user_padrao)
                .resize(120,100)
                .centerCrop()
                .transform(new CircleTransform())
                .into(img_user);

        nome_user.setText("Bem vindo, nome !");

        final AlertDialog alert = mBuilder.create();
        alert.show();
    }

    public void abrirCardapio(View view) {

        Intent intent = new Intent(this, CardapioActivity.class);
        intent.putExtra("Parent", "Home");
        startActivity(intent);

    }

    public void abrirEventos(View view) {
        Intent intent = new Intent(this, EventosActivity.class);
        startActivity(intent);
    }

}
