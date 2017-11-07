package com.example.a16165872.theribs;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by 16165872 on 05/10/2017.
 */

public class Filial {

    int id_restaurante;
    String Nome;
    int tipo_restaurante;
    String telefone;
    String cep;
    String numero;
    String foto;
    String cnpj;


    public Filial(int id_restaurante, String nome, int tipoRestaurante,  String telefone, String cep, String numero, String local_imagem, String cnpj) {
        this.id_restaurante = id_restaurante;
        this.Nome = nome;
        this.tipo_restaurante = tipoRestaurante;
        this.telefone = telefone;
        this.cep = cep;
        this.numero = numero;
        this.foto = local_imagem;
        this.cnpj = cnpj;
    }


    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }


    public int getTipo_restaurante() {
        return tipo_restaurante;
    }

    public void setTipo_restaurante(int tipo_restaurante) {
        this.tipo_restaurante = tipo_restaurante;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString(){
        return Nome;
    }
}
