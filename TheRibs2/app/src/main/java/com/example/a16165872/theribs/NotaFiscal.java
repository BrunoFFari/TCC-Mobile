package com.example.a16165872.theribs;

/**
 * Created by 16165872 on 16/11/2017.
 */

public class NotaFiscal {


    private int id_nota_fiscal;
    private int numero;
    private String emissao;
    private  int id_pedido;


    public NotaFiscal(int id_nota_fiscal, int numero, String emissao, int id_pedido) {
        this.id_nota_fiscal = id_nota_fiscal;
        this.numero = numero;
        this.emissao = emissao;
        this.id_pedido = id_pedido;
    }


    public int getId_nota_fiscal() {
        return id_nota_fiscal;
    }

    public void setId_nota_fiscal(int id_nota_fiscal) {
        this.id_nota_fiscal = id_nota_fiscal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}
