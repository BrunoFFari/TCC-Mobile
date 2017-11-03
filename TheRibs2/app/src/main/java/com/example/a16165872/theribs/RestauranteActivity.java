package com.example.a16165872.theribs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class RestauranteActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Context context;
    String uriString;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = this;

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }

    public void voltarNavegacao(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void abrirContato(View view) {
        Intent intent = new Intent(this, ContatoActivity.class);
        startActivity(intent);
    }

    public void abrirEscolherData(View view) {

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        int ano = calendar.get( java.util.Calendar.YEAR);
        int mes = calendar.get( java.util.Calendar.MONTH);
        int dia = calendar.get( java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(RestauranteActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                ano, mes, dia);

        dialog.show();

    }

    public void avaliar(View view) {

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RestauranteActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_avaliacao, null);

        mBuilder.setView(mView);

        AlertDialog alert = mBuilder.create();
        alert.show();

    }

    public void abrirAvaliacao(View view) {

        Intent whatsapp = new Intent(Intent.ACTION_SEND);
        whatsapp.setType("text/plain");
        whatsapp.putExtra(Intent.EXTRA_TEXT, "Utilize o SmartGames, é muito bom!! Desenvolvido por, .STUFF:" + "  " + "www.smartgames.com.br");
        whatsapp.setPackage("com.whatsapp");
        startActivity(whatsapp);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_restaurante, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    InformacoesFragment informacoesFragment = new InformacoesFragment();
                    return informacoesFragment;
                case 1:
                    AvalicaoFragment avalicaoFragment = new AvalicaoFragment();
                    return avalicaoFragment;
                case 2:
                    ReservasFragment reservasFragment = new ReservasFragment();
                    return reservasFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informações";
                case 1:
                    return "Avaliação";
                case 2:
                    return "Reserva";
            }
            return null;
        }
    }
}
