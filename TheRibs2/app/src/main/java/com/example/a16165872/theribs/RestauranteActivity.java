package com.example.a16165872.theribs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class RestauranteActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    Context context;
    int idRestaurante;
    TextView txt_nome_restaurante;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);


        context = this;

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        Intent intent = getIntent();
        idRestaurante = intent.getIntExtra("id", 0);

        txt_nome_restaurante = (TextView)findViewById(R.id.txt_nome_restaurante);


        if(Internet.VerificarConexao(context)){
            BuscarDados();
        }else{
            Snackbar.make(coordinatorLayout, "Sua internet já foi, trás ela de volta, a gente te espera.", Snackbar.LENGTH_INDEFINITE).show();
        }





    }

    public void voltarNavegacao(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        whatsapp.putExtra(Intent.EXTRA_TEXT, "Faça sua reversa na The Ribs - Steakhouse, é muito bom!! Desenvolvido por, .STUFF:" + "  " + "www.eatribstuff.com.br");
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
                    InformacoesFragment informacoesFragment = new InformacoesFragment(idRestaurante);
                    return informacoesFragment;
                case 1:
                    AvalicaoFragment avalicaoFragment = new AvalicaoFragment();
                    return avalicaoFragment;

            }
            return null;
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Informações";
                case 1:
                    return "Avaliação";

            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(context, ContatoActivity.class);
            intent.putExtra("idRestaurante", idRestaurante);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void BuscarDados(){
        new AsyncTask<Void, Void, Void>(){

            Filial filial[];


            @Override
            protected Void doInBackground(Void... voids) {

                String dados = HttpConnection.get(getString(R.string.link_node) + "/BuscarDadosFilial?id=" + idRestaurante);
                filial = new Gson().fromJson(dados, Filial[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(filial != null){

                    txt_nome_restaurante.setText(filial[0].getNome());

                }else{
                    Toast.makeText(context, "Não conseguimos buscar os dados, verifique sua conexão com internet.", Toast.LENGTH_LONG).show();
                }

            }
        }.execute();

    }


}
