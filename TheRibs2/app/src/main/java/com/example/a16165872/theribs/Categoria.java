package com.example.a16165872.theribs;

/**
 * Created by Bruno on 13/10/2017.
 */

public class Categoria {

    private int id_tipo_prato;
    private String nome;
    private String imagem;


    public Categoria (int id_tipo_prato, String nome, String imagem){
        this.id_tipo_prato = id_tipo_prato;
        this.nome = nome;
        this.imagem = imagem;
    }

    public int getId_tipo_prato() {
        return id_tipo_prato;
    }

    public void setId_tipo_prato(int id_tipo_prato) {
        this.id_tipo_prato = id_tipo_prato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString(){
        return nome;
    }

}
