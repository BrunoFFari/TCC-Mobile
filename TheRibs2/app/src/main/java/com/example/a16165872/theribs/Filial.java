package com.example.a16165872.theribs;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by 16165872 on 05/10/2017.
 */

public class Filial {

    int id_filial;
    String nome;
    String endereco;
    int tipo_restaurante;
    String telefone;
    String cep;
    String numero;
    String foto;


    public Filial(String nome, String endereco, int tipoRestaurante,  String telefone, String cep, String numero, String local_imagem) {

        this.nome = nome;
        this.endereco = endereco;
        this.tipo_restaurante = tipoRestaurante;
        this.telefone = telefone;
        this.cep = cep;
        this.numero = numero;
        this.foto = local_imagem;
    }


    public int getId_filial() {
        return id_filial;
    }

    public void setId_filial(int id_filial) {
        this.id_filial = id_filial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getTipo_restaurante() {
        return tipo_restaurante;
    }

    public void setTipo_restaurante(int tipo_restaurante) {
        this.tipo_restaurante = tipo_restaurante;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    @Override
    public String toString(){
        return nome;
    }
}
