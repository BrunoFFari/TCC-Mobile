package com.example.a16165872.theribs;

/**
 * Created by Bruno on 20/10/2017.
 */

public class Cartao {

    private int id_cartao;
    private String numero_cartao;
    private String nome_cartao;
    private String cvc;
    private String validade;


    public Cartao(int id_cartao, String numero_cartao, String nome_cartao, String cvc, String validade){
        this.id_cartao = id_cartao;
        this.numero_cartao = numero_cartao;
        this.nome_cartao = nome_cartao;
        this.cvc = cvc;
        this.validade = validade;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public String getNome_cartao() {
        return nome_cartao;
    }

    public void setNome_cartao(String nome_cartao) {
        this.nome_cartao = nome_cartao;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
