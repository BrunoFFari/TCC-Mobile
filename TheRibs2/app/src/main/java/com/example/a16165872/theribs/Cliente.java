package com.example.a16165872.theribs;

/**
 * Created by 16165872 on 17/10/2017.
 */

public class Cliente {

    private int id_cliente;
    private String nome;
    private String telefone;
    private String celular;
    private String email;
    private String cpf;
    private String senha;
    private String numero;
    private String foto;
    private String cep;


    public  Cliente(int id_clientem, String nome, String telefone, String celular, String email, String cpf, String senha,
        String numero, String foto, String cep){

        this.id_cliente = id_clientem;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.numero = numero;
        this.foto = foto;
        this.cep = cep;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
