package com.example.a16165872.theribs;

/**
 * Created by Bruno on 14/10/2017.
 */

public class Ocorrencia {

    private int id_ocorrencia;
    private String ocorrencia;

    public Ocorrencia(int id_ocorrencia, String ocorrencia){
        this.id_ocorrencia = id_ocorrencia;
        this.ocorrencia = ocorrencia;
    }

    public int getId_ocorrencia() {
        return id_ocorrencia;
    }

    public void setId_ocorrencia(int id_ocorrencia) {
        this.id_ocorrencia = id_ocorrencia;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    @Override
    public String toString() {
        return ocorrencia;
    }
}
