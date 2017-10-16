package com.example.a16165872.theribs;

import android.os.Bundle;
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content, new NavegueFragment()).commit();
                    return true;
                case R.id.navigation_notifications:

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

        montarDialogInicial();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private void montarDialogInicial() {
        View dialog = getLayoutInflater().inflate(R.layout.dialog_boas_vindas, null);

        img_user = dialog.findViewById(R.id.foto_usuario);
        nome_user = dialog.findViewById(R.id.txt_nome_usuario);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(HomeActivity.this);
        mBuilder.setView(dialog);

        Picasso.with(this)
                .load(R.drawable.user_padrao)
                .resize(120,100)
                .centerCrop()
                .transform(new CircleTransform())
                .into(img_user);

        nome_user.setText("Bem vindo, Nome da pessoa!");

        final AlertDialog alert = mBuilder.create();
        alert.show();
    }

}
