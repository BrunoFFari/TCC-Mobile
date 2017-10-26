package com.example.a16165872.theribs;

import java.util.Date;

/**
 * Created by Bruno on 13/10/2017.
 */

public class Evento {

    private int id_vento;
    private String nome;
    private String sobre;
    private String data;
    private int id_restaurante;
    private String img_evento;

    public Evento (int id, String nome, String descricao, String data, int id_restaurante, String imagem ){
        this.id_vento = id;
        this.nome = nome;
        this.sobre = descricao;
        this.data = data;
        this.id_restaurante = id_restaurante;
        this.img_evento = imagem;
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

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getImg_evento() {
        return img_evento;
    }

    public void setImg_evento(String img_evento) {
        this.img_evento = img_evento;
    }
}
