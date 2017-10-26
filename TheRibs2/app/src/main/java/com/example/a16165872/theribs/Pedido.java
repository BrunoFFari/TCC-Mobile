package com.example.a16165872.theribs;

/**
 * Created by Bruno on 21/10/2017.
 */

public class Pedido {

    private int id_pedido;
    private int id_cliente;
    private String nome_prato;
    private String quantidade;
    private int imagem_prato;


    public Pedido(int id_pedido, int id_cliente, int imagem_prato, String nome_prato, String quantidade){
        this.id_cliente = id_cliente;
        this.id_pedido = id_pedido;
        this.imagem_prato = imagem_prato;
        this.nome_prato = nome_prato;
        this.quantidade = quantidade;

    }


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_prato() {
        return nome_prato;
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public int getImagem_prato() {
        return imagem_prato;
    }

    public void setImagem_prato(int imagem_prato) {
        this.imagem_prato = imagem_prato;
    }
}
