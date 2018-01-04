package com.example.a16165872.theribs;

/**
 * Created by Bruno on 15/10/2017.
 */

public class Periodo {

    private int id_periodo;
    private String nome;
    private String horario;

    public Periodo(int id, String nome, String intervalo){
        this.id_periodo = id;
        this.nome = nome;
        this.horario = intervalo;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIntervalo() {
        return horario;
    }

    public void setIntervalo(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString(){

        return nome + " (" + horario + ")";
    }
}
