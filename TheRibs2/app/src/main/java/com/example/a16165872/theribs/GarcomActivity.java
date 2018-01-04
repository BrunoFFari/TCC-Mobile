package com.example.a16165872.theribs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class GarcomActivity extends AppCompatActivity {

    int id;
    Context context;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.content, new AtendimentoFragment(id)).commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.content, new MesaFragment(id)).commit();
                    return true;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(context, MainActivity.class);
                    finish();
                    startActivity(intent);
                    return true;
            }
        return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garcom);

        context = this;

        Intent intent = getIntent();
        id = intent.getIntExtra("Funcionario", 0);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.content, new AtendimentoFragment(id)).commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(context, GarcomActivity.class);
        intent.putExtra("Funcionario", id );
        startActivity(intent);

    }
}
