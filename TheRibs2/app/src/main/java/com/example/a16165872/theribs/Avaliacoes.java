package com.example.a16165872.theribs;

/**
 * Created by Bruno on 16/10/2017.
 */

public class Avaliacoes {

    private int nota;
    private String descricao;


    public Avaliacoes(int nota, String descricao){
        this.nota = nota;
        this.descricao = descricao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
