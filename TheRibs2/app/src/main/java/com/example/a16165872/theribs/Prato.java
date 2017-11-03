package com.example.a16165872.theribs;

/**
 * Created by 16165872 on 11/10/2017.
 */

public class Prato {

    private int id_produto;
    private String nome;
    private float preco;
    private String descricao;
    private  int tipo_produto;
    private int id_img;
    private String url;


    public  Prato (int id_produto, String nome, float preco, String descricao, int tipo_produto, String imagem, int id_img){

        this.id_produto = id_produto;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo_produto = tipo_produto;
        this.url = imagem;
        this.id_img = id_img;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(int tipo_produto) {
        this.tipo_produto = tipo_produto;
    }

    public int getId_img() {
        return id_img;
    }

    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
