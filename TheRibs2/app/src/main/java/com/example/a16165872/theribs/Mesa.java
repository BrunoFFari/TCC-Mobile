package com.example.a16165872.theribs;

import android.text.BoringLayout;

/**
 * Created by Bruno on 21/10/2017.
 */

public class Mesa {

    private int id_mesa;
    private String numero;
    private String lugares;
    private int id_restaurante;
    private int validacao_reserva;
    private int status;
    private int id_garcom;
    private int id_pedido;
    private int id_cliente;



    public  Mesa(int id_mesa, String numero, String lugares, int id_restaurante, int validacao_reserva){
        this.id_mesa = id_mesa;
        this.numero = numero;
        this.lugares = lugares;
        this.id_restaurante = id_restaurante;
        this.validacao_reserva = validacao_reserva;
    }


    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLugares() {
        return lugares;
    }

    public void setLugares(String lugares) {
        this.lugares = lugares;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public int getValidacao_reserva() {
        return validacao_reserva;
    }

    public void setValidacao_reserva(int validacao_reserva) {
        this.validacao_reserva = validacao_reserva;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId_garcom() {
        return id_garcom;
    }

    public void setId_garcom(int id_garcom) {
        this.id_garcom = id_garcom;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
