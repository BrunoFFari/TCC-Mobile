package com.example.a16165872.theribs;

/**
 * Created by Bruno on 20/10/2017.
 */

public class Cartao {

    private int id_cartao;
    private String numero;
    private String nome;
    private String codigo;
    private String vencimento;
    private int id_cliente;


    public Cartao(int id_cartao, String numero_cartao,
                  String nome_cartao, String cvc, String validade, int id_cliente){
        this.id_cartao = id_cartao;
        this.numero = numero_cartao;
        this.nome = nome_cartao;
        this.codigo = cvc;
        this.vencimento = validade;
        this.id_cliente = id_cliente;
    }

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
}
