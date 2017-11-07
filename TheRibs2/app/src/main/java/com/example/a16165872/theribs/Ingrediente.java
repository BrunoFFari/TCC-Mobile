package com.example.a16165872.theribs;

/**
 * Created by 16165872 on 07/11/2017.
 */

public class Ingrediente {

    private int id_igrediente;
    private String nome;
    private int id_produto;

    public  Ingrediente(int id_igrediente, String nome, int id_produto){
        this.id_igrediente = id_igrediente;
        this.nome = nome;
        this.id_produto = id_produto;
    }

    public int getId_igrediente() {
        return id_igrediente;
    }

    public void setId_igrediente(int id_igrediente) {
        this.id_igrediente = id_igrediente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
}
