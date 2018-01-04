package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity {

    ImageView img_user;
    TextView nome_user;
    Context context;

    String nome;
    String foto;
    int validacao;
    int id;

    Drawable imagem;


    CoordinatorLayout container;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent abrirMeusDados = new Intent(context, MeusDadosActivity.class);
                    abrirMeusDados.putExtra("idCliente", id);
                    startActivity(abrirMeusDados);
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content, new NavegueFragment()).commit();

                    return true;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(context, CheckInActivity.class);
                    intent.putExtra("idCliente", id);
                    startActivity(intent);

                    return true;
                case R.id.navigation_sair:
                    Intent sair = new Intent(context, MainActivity.class);
                    startActivity(sair);
                    finish();
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
        container = (CoordinatorLayout)findViewById(R.id.container);

        Intent intent = getIntent();
        id = intent.getIntExtra("idCliente", 0);
        validacao = intent.getIntExtra("validacao", 0);

        if(validacao != 1){

            nome = intent.getStringExtra("nomeCliente");
            foto = intent.getStringExtra("fotoCliente");

            if(Internet.VerificarConexao(this)){
                buscarFoto();
                montarDialogInicial();
            }else{
                Snackbar.make(container,
                        "Sua internet já foi, trás ela de volta a gente te espera...",
                        Snackbar.LENGTH_LONG).show();

            }
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new NavegueFragment()).commit();

    }

    private void buscarFoto(){

        new AsyncTask<Void, Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    URL url = new URL(foto);
                    Bitmap decoded = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    imagem = new BitmapDrawable(getResources(), decoded);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                Picasso.with(context)
                        .load(foto)
                        .transform(new CircleTransform())
                        .into(img_user);

            }

        }.execute();
    }

    private void montarDialogInicial() {
        View dialog = getLayoutInflater().inflate(R.layout.dialog_boas_vindas, null);

        img_user = dialog.findViewById(R.id.foto_usuario);
        nome_user = dialog.findViewById(R.id.txt_nome_usuario);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this);
        mBuilder.setView(dialog);


        Picasso.with(this)
                .load("http://www.media.inaf.it/wp-content/uploads/2014/02/Einstein_laughing.jpeg")
                .transform(new CircleTransform())
                .into(img_user);


        nome_user.setText("Bem vindo, " + nome);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("idCliente", id);
        intent.putExtra("nomeCliente", nome);
        intent.putExtra("fotoCliente", foto);
        startActivity(intent);
    }
}
