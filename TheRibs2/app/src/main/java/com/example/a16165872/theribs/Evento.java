package com.example.a16165872.theribs;

import java.util.Date;

/**
 * Created by Bruno on 13/10/2017.
 */

public class Evento {

    private int id_evento;
    private String nome;
    private String sobre;
    private String data;
    private int id_restaurante;
    private String img_evento;
    private String nome_filial;

    public Evento (int id, String nome, String descricao, String data, int id_restaurante, String imagem ){
        this.id_evento = id;
        this.nome = nome;
        this.sobre = descricao;
        this.data = data;
        this.id_restaurante = id_restaurante;
        this.img_evento = imagem;
    }

    public int getId_vento() {
        return id_evento;
    }

    public void setId_vento(int id_vento) {
        this.id_evento = id_vento;
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

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNome_filial() {
        return nome_filial;
    }

    public void setNome_filial(String nome_filial) {
        this.nome_filial = nome_filial;
    }
}
