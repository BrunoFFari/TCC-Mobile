package com.example.a16165872.theribs;

/**
 * Created by Bruno on 21/10/2017.
 */

public class Pedido {

    private int id_pedido;
    private int id_produto;
    private String nome;
    private String qtd;
    private float preco;
    private String url;
    private int status;

    public Pedido(int id_pedido, int id_produto, String nome, String qtd, float preco, String url, int status) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
        this.url = url;
        this.status = status;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
