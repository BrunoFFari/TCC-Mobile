package com.example.a16165872.theribs;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class FazerReservaFragment extends Fragment {

    /* Adapters */
    ArrayAdapter adapter, adapter_periodo;
    MesaAdapter adapter_mesas;

    /* Conponentes XML */
    Spinner spn_filial, spn_periodo;
    TextView txt_nome_filial, txt_titulo_data, txt_titulo_data2, txt_data, txt_titulo_periodo, txt_titulo_mesa;
    GridView grid_items;
    ImageView img_sem_mesas;
    Button btn_abrir_calendario;

    /*Componentes XML dialog_confirmacao */
    TextView txt_restaurante_reserva, txt_data_reserva, txt_mesa_reserva, txt_periodo_reserva;
    Button btn_cancelar, btn_confirmar;

    /* Dados da Reserva*/
    Filial filialSelecionada;
    Periodo periodoSelecionado;
    Mesa mesaSelecionada;
    String parametros, dataBanco, parametrosReserva;

    /*Dados Cliente*/
    int idCliente;

    /* Dados calendário */
    Calendar mCalendar;
    int ano, mes, dia;

    Context context = getContext();


    public FazerReservaFragment(int id) {
        this.idCliente = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_fazer_reserva, container, false);


        findViews(v);
        setVisibility();
        adapetrs();
        configurarClickSinnerFilial();
        configurarEntradaData();
        configurarClickSpinnerPeriodo();
        configurarCalendario();
        configurarClickGridMesas();

        if(Internet.VerificarConexao(getContext())){
            carregarFiliais();
            carregarPeriodos();
        }else{
            Snackbar.make(getView(), "Sua internet já foi, trás ela de volta, a gente te espera...", Snackbar.LENGTH_INDEFINITE).show();
        }

        return  v;
    }

    private void dialogConnfirmarReserva(){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        View mView = getLayoutInflater(Bundle.EMPTY).inflate(R.layout.dialog_confirmacao, null);

        txt_data_reserva = mView.findViewById(R.id.txt_data_reserva);
        txt_restaurante_reserva = mView.findViewById(R.id.txt_restaurante_reserva);
        txt_mesa_reserva = mView.findViewById(R.id.txt_mesa_reserva);
        txt_periodo_reserva = mView.findViewById(R.id.txt_periodo_reserva);
        btn_cancelar = mView.findViewById(R.id.btn_cancelar);
        btn_confirmar = mView.findViewById(R.id.btn_confirmar);

        txt_data_reserva.setText(txt_data.getText().toString());
        txt_mesa_reserva.setText("Mesa: " + mesaSelecionada.getNumero() + "(" + mesaSelecionada.getLugares() + " Luagres)");
        txt_restaurante_reserva.setText(filialSelecionada.getNome());
        txt_periodo_reserva.setText(periodoSelecionado.getNome());

        mBuilder.setView(mView);
        final AlertDialog alert = mBuilder.create();
        alert.show();

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Internet.VerificarConexao(getContext())){
                    parametrosReserva = "idPeriodo=" + periodoSelecionado.getId_periodo();
                    parametrosReserva = parametrosReserva + "&data=" + dataBanco;
                    parametrosReserva = parametrosReserva + "&idMesa=" + mesaSelecionada.getId_mesa();
                    parametrosReserva = parametrosReserva + "&idCliente=" + idCliente;

                    realizarReserva();

                    Intent intent = new Intent(getContext(), CheckInActivity.class);
                    intent.putExtra("idCliente", idCliente);
                    intent.putExtra("validacao", 1);
                    alert.hide();
                    startActivity(intent);

                }else{
                    Snackbar.make(getView(), "Sua internet já foi, trás ela de volta, a gente te espera...", Snackbar.LENGTH_INDEFINITE).show();
                }

            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alert.hide();

            }
        });

    }

    private void configurarClickGridMesas() {
        grid_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mesaSelecionada = adapter_mesas.getItem(i);

                dialogConnfirmarReserva();


            }
        });
    }

    private void configurarCalendario() {

        mCalendar = Calendar.getInstance();

        dia = mCalendar.get(Calendar.DAY_OF_MONTH);
        mes = mCalendar.get(Calendar.MONTH);
        ano = mCalendar.get(Calendar.YEAR);

        btn_abrir_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int anoSelecionado, int mesSelecionado, int diaSelecionado) {

                        mesSelecionado = mesSelecionado + 1;
                        mes = mes + 1;

                        if(anoSelecionado >= ano){

                            if(mesSelecionado >= mes){

                                if(diaSelecionado <= dia){
                                    Snackbar.make(getView(), "Toast data selecionada é inválida inválida", Snackbar.LENGTH_LONG).show();
                                }else{

                                    txt_data.setText(diaSelecionado +"/"+ mesSelecionado +"/"+ anoSelecionado);
                                    dataBanco = anoSelecionado +"-"+ mesSelecionado + "-" + diaSelecionado;
                                }

                            }else{
                                Snackbar.make(getView(), "Toast data selecionada é inválida inválida", Snackbar.LENGTH_LONG).show();
                            }

                        }else{
                            Snackbar.make(getView(), "Toast data selecionada é inválida inválida", Snackbar.LENGTH_LONG).show();
                        }



                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });
    }

    private void configurarEntradaData() {
        txt_data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                img_sem_mesas.setVisibility(View.INVISIBLE);
                txt_titulo_periodo.setVisibility(View.VISIBLE);
                spn_periodo.setVisibility(View.VISIBLE);

            }
        });
    }

    private void configurarClickSpinnerPeriodo() {
        spn_periodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                if(position != 0){
                    img_sem_mesas.setVisibility(View.INVISIBLE);
                    periodoSelecionado = (Periodo) adapter_periodo.getItem(position);
                    txt_titulo_mesa.setVisibility(View.VISIBLE);
                    grid_items.setVisibility(View.VISIBLE);

                    if(Internet.VerificarConexao(getContext())){
                        buscarMesasDisponiveis();
                    }else{
                        Snackbar.make(getView(), "Sua internet já foi, trás ela de volta, a gente te espera...", Snackbar.LENGTH_INDEFINITE).show();
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }

    private void adapetrs() {
        adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<Filial>()
        );


        adapter_periodo = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<Periodo>()
        );

        adapter_mesas = new MesaAdapter(
                getContext(),
                R.layout.adpter_mesa,
                new ArrayList<Mesa>()
        );

        spn_filial.setAdapter(adapter);
        spn_periodo.setAdapter(adapter_periodo);
        grid_items.setAdapter(adapter_mesas);
    }

    private void setVisibility() {
        btn_abrir_calendario.setVisibility(View.INVISIBLE);
        txt_titulo_data.setVisibility(View.INVISIBLE);
        txt_titulo_data2.setVisibility(View.INVISIBLE);
        txt_data.setVisibility(View.INVISIBLE);
        txt_titulo_periodo.setVisibility(View.INVISIBLE);
        spn_periodo.setVisibility(View.INVISIBLE);
        grid_items.setVisibility(View.INVISIBLE);
        txt_titulo_mesa.setVisibility(View.INVISIBLE);
        img_sem_mesas.setVisibility(View.INVISIBLE);
    }

    private void findViews(View v) {
        spn_filial = v.findViewById(R.id.spn_filial);
        spn_periodo = v.findViewById(R.id.spn_periodo);

        grid_items = v.findViewById(R.id.grid_items);
        txt_titulo_mesa = v.findViewById(R.id.txt_titulo_mesa);


        txt_nome_filial = v.findViewById(R.id.txt_nome_filial);
        txt_titulo_data = v.findViewById(R.id.txt_titulo_data);
        txt_titulo_data2 = v.findViewById(R.id.txt_titulo_data2);
        txt_data = v.findViewById(R.id.txt_data);
        txt_titulo_periodo = v.findViewById(R.id.txt_titulo_periodo);

        btn_abrir_calendario = v.findViewById(R.id.btn_abrir_calendario);

        img_sem_mesas = v.findViewById(R.id.img_sem_mesas);
    }

    private void configurarClickSinnerFilial() {
        spn_filial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                if(position != 0){
                    img_sem_mesas.setVisibility(View.INVISIBLE);
                    filialSelecionada = (Filial) adapter.getItem(position);
                    btn_abrir_calendario.setVisibility(View.VISIBLE);
                    txt_titulo_data.setVisibility(View.VISIBLE);
                    txt_titulo_data2.setVisibility(View.VISIBLE);
                    txt_data.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void carregarFiliais(){

        new AsyncTask<Void, Void, Void>(){

            Filial filial[];

            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarFiliais");
                filial = new Gson().fromJson(dadosJson, Filial[].class);


                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                adapter.clear();
                adapter.add(new Filial(0, "Selecione um restaurante...", 0, "000000", "0000", "00", "00", "00"));
                adapter.addAll(Arrays.asList(filial));
            }
        }.execute();
    }

    private void carregarPeriodos(){

        new AsyncTask<Void, Void, Void>(){

            Periodo periodos[];
            @Override
            protected Void doInBackground(Void... voids) {

                String dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarPeriodos");
                periodos = new Gson().fromJson(dadosJson, Periodo[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);


                adapter_periodo.clear();
                adapter_periodo.add(new Periodo(0, "Selecione um periodo...", ""));
                adapter_periodo.addAll(Arrays.asList(periodos));
            }


        }.execute();

    }

    private void buscarMesasDisponiveis(){

        parametros = "?idRestaurante="+ filialSelecionada.getId_restaurante();
        parametros = parametros + "&idPeriodo=" + periodoSelecionado.getId_periodo();
        parametros = parametros + "&data=" + dataBanco;

        new AsyncTask<Void, Void, Void>(){


            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos Trabalhando", "buscando as mesas livres...");
            }

            String dadosJson;
            Mesa mesas[];
            @Override
            protected Void doInBackground(Void... voids) {

                dadosJson = HttpConnection.get(getString(R.string.link_node) + "/BuscarMesasDisponiveisReserva" + parametros );
                mesas = new Gson().fromJson(dadosJson, Mesa[].class);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(dadosJson.contains("[]")){
                    img_sem_mesas.setVisibility(View.VISIBLE);
                    grid_items.setVisibility(View.INVISIBLE);
                }else{
                    adapter_mesas.clear();
                    adapter_mesas.addAll(Arrays.asList(mesas));
                    img_sem_mesas.setVisibility(View.INVISIBLE);
                }

                dialog.dismiss();

            }

        }.execute();
    }

    private void realizarReserva(){
        new AsyncTask<Void, Void, Void>(){


            ProgressDialog dialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                dialog = ProgressDialog.show(getContext(), "Estamos realizando a sua reserva", "validando os dados...");
            }

            String resultado;
            @Override
            protected Void doInBackground(Void... voids) {
                resultado = Conexao.postDados(getString(R.string.link_node) + "/NovaReserva", parametrosReserva);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                if(!resultado.isEmpty()){
                    Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }

            }

        }.execute();
    }


}
