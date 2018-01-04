package com.example.a16165872.theribs;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimerTask;

public class ItensPedidoActivity extends AppCompatActivity {


    int idMesa, idGarcom, idPedido;
    ListView list_item;

    ItensPedidosAdapter adapter;
    ItensSolicitadosAdapter adapter_itens;

    Prato pratoSelecionado;

    Context context;

    /* COMPONENTES XML*/
    Button btn_itens_solicitados, btn_fechar_conta;

    /*Componentes DIALOG */
    Button btn_mais, btn_menos, btn_salvar;
    TextView txt_qtd, txt_nome;
    ImageView img_prato;
    EditText edit_obs;
    int qtd;

    int idProduto;
    String obs;

    Button btn_nao, btn_sim;

    int numero_nota_fiscal;
    String parametros;

    String parametrosPesquisa;

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        setSupportActionBar(toolbar);
        context = this;

        list_item = (ListView)findViewById(R.id.list_item);

        Intent intent = getIntent();
        idGarcom = intent.getIntExtra("idGarcom", 0);
        idMesa = intent.getIntExtra("idMesa", 0);
        idPedido = intent.getIntExtra("idPedido", 0);

        btn_itens_solicitados = (Button) findViewById(R.id.btn_itens_solicitados);
        btn_fechar_conta = (Button) findViewById(R.id.btn_fechar_conta);

        adapter = new ItensPedidosAdapter(
                context,
                R.layout.adapter_itens_pedido,
                new ArrayList<Prato>()
        );

        list_item.setAdapter(adapter);

        registerForContextMenu(list_item);


        if(Internet.VerificarConexao(context)){
            BuscarPratos();
        }else{
            Snackbar.make(coordinatorLayout,
                    "Sua internet já foi, trás ela de volta, a gente te espera...",
                    Snackbar.LENGTH_LONG).show();
        }

        configurarClickItemLista();

        btn_itens_solicitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirDialogListaSolicitados();

            }
        });

        btn_fechar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirDialogFecharConta();
            }
        });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("Selecione a opção desejada");
        menu.add(0,v.getId(), 0, "Cancelar Pedido");
    }

    private void AbrirDialogFecharConta(){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.dialog_fechar_conta, null);

        btn_nao = mView.findViewById(R.id.btn_nao);
        btn_sim = mView.findViewById(R.id.btn_sim);

        mBuilder.setView(mView);
        final AlertDialog alert = mBuilder.create();
        alert.show();


        btn_sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(context)){
                    FecharConta();
                }else{
                    Snackbar.make(coordinatorLayout,
                            "Sua internet já foi, trás ela de volta, a gente te espera",
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });

        btn_nao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.hide();
            }
        });
    }

    private void AbrirDialogListaSolicitados(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.dialog_itens_solicitados, null);

        ListView list_itens_solicitados = mView.findViewById(R.id.list_itens_solicitados);

        adapter_itens = new ItensSolicitadosAdapter(
                context,
                R.layout.adapter_itens_solicitados,
                new ArrayList<ItemPedido>()
        );

        list_itens_solicitados.setAdapter(adapter_itens);

        if(Internet.VerificarConexao(context)){
            BucarPedidosSolicitados();
        }else{
            Snackbar.make(coordinatorLayout,
                    "Sua internet já foi, trás ela de volta, a gente te espera",
                    Snackbar.LENGTH_LONG).show();
        }


        mBuilder.setView(mView);
        final AlertDialog alert = mBuilder.create();
        alert.show();
    }

    private void configurarClickItemLista() {
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pratoSelecionado = adapter.getItem(i);

                if(Internet.VerificarConexao(context)){
                    AbrirDialogAdicionarPrato();
                }else{
                    Snackbar.make(coordinatorLayout,
                            "Sua internet já foi, trás ela de volta, a gente te espera",
                            Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }

    private void AbrirDialogAdicionarPrato(){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.dialog_adicionar_prato, null);

        btn_mais = mView.findViewById(R.id.btn_mais);
        btn_menos = mView.findViewById(R.id.btn_menos);
        btn_salvar = mView.findViewById(R.id.btn_salvar);
        txt_qtd = mView.findViewById(R.id.txt_qtd);
        txt_nome = mView.findViewById(R.id.txt_nome);
        img_prato = mView.findViewById(R.id.img_prato);
        edit_obs = mView.findViewById(R.id.edit_obs);


        qtd = Integer.parseInt(txt_qtd.getText().toString());

        mBuilder.setView(mView);
        final AlertDialog alert = mBuilder.create();
        alert.show();

        configurarCliqueBotoesOperadores();
        BuscarPratoID();

        txt_nome.setText(pratoSelecionado.getNome());
        Picasso.with(context)
                .load("http://www.eatribstuff.com.br/" + pratoSelecionado.getUrl())
                .transform(new CircleTransform())
                .into(img_prato);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                obs = edit_obs.getText().toString();
                idProduto = pratoSelecionado.getId_produto();


                if(Internet.VerificarConexao(context)){
                    EnviarPedido();
                }else{
                    Snackbar.make(coordinatorLayout,
                            "Sua internet já foi, trás ela de volta, a gente te espera",
                            Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }

    private void configurarCliqueBotoesOperadores() {
        btn_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qtd = qtd + 1;
                String quantidade = String.valueOf(qtd);
                txt_qtd.setText(quantidade);

            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(qtd > 1){
                    qtd = qtd - 1;
                    String quantidade = String.valueOf(qtd);
                    txt_qtd.setText(quantidade);

                }

            }
        });
    }

    private void BuscarPratos(){

        new AsyncTask<Void, Void, Void>(){

            Prato prato[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dados = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratos");
                prato = new Gson().fromJson(dados, Prato[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){

                    adapter.clear();
                    adapter.addAll(Arrays.asList(prato));

                }else {


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Não conseguimos encontrar nenhum parto... =(" , Snackbar.LENGTH_LONG)
                            .setAction("Tentar novamente", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    BuscarPratos();
                                }
                            });

                    snackbar.show();

                }

            }
        }.execute();


    }

    private void BuscarPratoID(){

        new AsyncTask<Void, Void, Void>(){

            Prato prato[];
            Drawable drawable;
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratoID?id=" + pratoSelecionado.getId_produto());
                prato = new Gson().fromJson(dadosJson, Prato[].class);

                /*try {
                    URL url = new URL(getString(R.string.link_imagens2) + prato[0].getUrl());
                    Bitmap decoded = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    drawable = new BitmapDrawable(getResources(), decoded);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){

                    pratoSelecionado.setId_produto(prato[0].getId_produto());
                    pratoSelecionado.setNome(prato[0].getNome());
                    pratoSelecionado.setUrl(prato[0].getUrl());

                }

            }
        }.execute();

    }

    private void EnviarPedido(){

        new AsyncTask<Void, Void, Void>(){

            String resultado;
            String parametros = "idPedido="+idPedido+"&idProduto="+idProduto+"&qtd="+qtd+"&obs="+obs;

            @Override
            protected Void doInBackground(Void... voids) {

                resultado = Conexao.postDados(getString(R.string.link_node) + "/EnviarPedido", parametros);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!resultado.isEmpty()){
                    Snackbar.make(coordinatorLayout, resultado, Snackbar.LENGTH_LONG).show();
                }

            }


        }.execute();

    }

    private void BucarPedidosSolicitados(){

        new AsyncTask<Void, Void, Void>(){

            ItemPedido itens[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarItensSolicitados?id="+ idPedido);
                itens = new Gson().fromJson(dadosJson, ItemPedido[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(itens != null){
                    adapter_itens.clear();
                    adapter_itens.addAll(Arrays.asList(itens));
                }
            }

        }.execute();
    }

    private void FecharConta(){

        new AsyncTask<Void, Void, Void>(){

            String resultado;
            @Override
            protected Void doInBackground(Void... voids) {

                String parametro = "?id="+idPedido;
                resultado = HttpConnection.get(getString(R.string.link_node) + "/FecharConta" + parametro);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!resultado.isEmpty()){
                    Toast.makeText(context, resultado, Toast.LENGTH_LONG).show();
                    BuscarUtlimaNota();
                }
            }


        }.execute();

    }

    private void BuscarUtlimaNota(){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Toast.makeText(context, "Buscando ultima nota", Toast.LENGTH_LONG).show();
            }

            NotaFiscal nota[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarUltimaNota");
                nota = new Gson().fromJson(dadosJson, NotaFiscal[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(nota!= null){

                    numero_nota_fiscal = nota[0].getNumero() + 1;
                    Calendar calendar = Calendar.getInstance();
                    int ano = calendar.get(Calendar.YEAR);
                    int mes = calendar.get(Calendar.MONTH);
                    int dia = calendar.get(Calendar.DAY_OF_MONTH);
                    int hora = calendar.get(Calendar.HOUR);
                    int minutos = calendar.get(Calendar.MINUTE);
                    int segundos = calendar.get(Calendar.SECOND);

                    String data = ano +"-"+ mes + "-"+ dia + " " + hora + ":" + minutos + ":" + segundos;
                    parametros = "?numero="+numero_nota_fiscal+"&idPedido="+idPedido+"&data="+data;
                    GerarNotaFiscal();



                }else{

                    Toast.makeText(context, "Buscando ultima nota", Toast.LENGTH_LONG).show();

                }

            }


        }.execute();
    }

    private void GerarNotaFiscal(){

        new AsyncTask<Void, Void, Void>(){

            String dadosJson;
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/GerarNota" + parametros);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!dadosJson.isEmpty()){
                    Toast.makeText(context, dadosJson, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Erro", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        SearchView sv  = new SearchView(this);
        sv.setQueryHint("O que está procutando?");
        sv.setOnQueryTextListener(new SearchFiltro());

        MenuItem itemum = menu.add(0,0,0,"item 1");
        itemum.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        itemum.setActionView(sv);

        return true;
    }

    private class SearchFiltro implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {

            parametrosPesquisa = "?text=" + query;

            if(!query.equals("")){
                pesquisa();
            }else{
                pesquisa();
            }

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {

            parametrosPesquisa = "?text=" + newText;

            if(!newText.equals("")){
                pesquisa();
            }else{
                pesquisa();
            }

            return false;
        }
    }

    private void pesquisa(){

        new AsyncTask<Void, Void, Void>(){



            Prato prato[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPratosID" + parametrosPesquisa);
                prato = new Gson().fromJson(dadosJson,Prato[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(prato != null){
                    adapter.clear();
                    adapter.addAll(Arrays.asList(prato));

                }
            }
        }.execute();

    }

}
