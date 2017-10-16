package com.example.a16165872.theribs;

/**
 * Created by Bruno on 15/10/2017.
 */

public class ReservaDisponivel {

    private int id_mesa;
    private int id_periodo;
    private String nome_periodo;
    private String nome_mesa;
    private String intervalo;

    public ReservaDisponivel(int id_mesa, int id_periodo, String nome_periodo, String nome_mesa, String intervalo){
        this.id_mesa = id_mesa;
        this.id_periodo = id_periodo;
        this.nome_periodo = nome_periodo;
        this.nome_mesa = nome_mesa;
        this.intervalo = intervalo;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNome_periodo() {
        return nome_periodo;
    }

    public void setNome_periodo(String nome_periodo) {
        this.nome_periodo = nome_periodo;
    }

    public String getNome_mesa() {
        return nome_mesa;
    }

    public void setNome_mesa(String nome_mesa) {
        this.nome_mesa = nome_mesa;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }
}
