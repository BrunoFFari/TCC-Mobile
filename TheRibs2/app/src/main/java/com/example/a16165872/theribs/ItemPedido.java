package com.example.a16165872.theribs;

/**
 * Created by Bruno on 15/11/2017.
 */

public class ItemPedido {

    private int id_pedido;
    private int id_produto;
    private int qtd;
    private String obs;
    private String nome;

    public ItemPedido(int id_pedido, int id_produto, int qtd, String obs, String nome) {
        this.id_pedido = id_pedido;
        this.id_produto = id_produto;
        this.qtd = qtd;
        this.obs = obs;
        this.nome = nome;
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

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
