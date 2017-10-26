package com.example.a16165872.theribs;

/**
 * Created by Bruno on 21/10/2017.
 */

public class Mesa {

    private int id_mesa;
    private String numero;
    private String lugares;
    private int id_restaurante;


    public  Mesa(int id_mesa, String numero, String lugares, int id_restaurante){
        this.id_mesa = id_mesa;
        this.numero = numero;
        this.lugares = lugares;
        this.id_restaurante = id_restaurante;
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
}
