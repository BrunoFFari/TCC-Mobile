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
    String telefone;
    String status;
    String numero;
    int local_imagem;


    public Filial(String nome, String endereco, String telefone, String status, String numero, int local_imagem) {

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.status = status;
        this.numero = numero;
        this.local_imagem = local_imagem;
    }



    public int getLocal_imagem() {
        return local_imagem;
    }

    public void setLocal_imagem(int local_imagem) {
        this.local_imagem = local_imagem;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
