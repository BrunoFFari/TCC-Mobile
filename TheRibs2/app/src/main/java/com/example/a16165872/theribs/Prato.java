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
    int imagem;


    public  Prato (int id_produto, String nome, float preco, String descricao, int tipo_produto, int imagem){

        this.id_produto = id_produto;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo_produto = tipo_produto;
        this.imagem = imagem;

    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
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
}
