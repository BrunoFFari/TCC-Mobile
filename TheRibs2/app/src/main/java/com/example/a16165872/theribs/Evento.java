package com.example.a16165872.theribs;

import java.util.Date;

/**
 * Created by Bruno on 13/10/2017.
 */

public class Evento {

    private int id_vento;
    private String nome;
    private String descricao;
    private String data;
    private int imagem;

    public Evento (int id, String nome, String descricao, String data, int imagem ){
        this.id_vento = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.imagem = imagem;
    }

    public int getId_vento() {
        return id_vento;
    }

    public void setId_vento(int id_vento) {
        this.id_vento = id_vento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
