package com.example.a16165872.theribs;

/**
 * Created by 16165872 on 26/10/2017.
 */

public class Funcionario {

    private int id_funcionario;
    private String nome;
    private String celular;
    private String email;
    private int id_funcao;
    private int id_restaurante;
    private String senha;


    public Funcionario (int id_funcionario, int id_funcao, int id_restaurante, String nome, String celular, String email, String senha){
        this.id_funcionario = id_funcionario;
        this.id_funcao = id_funcao;
        this.id_restaurante = id_restaurante;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getId_funcao() {
        return id_funcao;
    }

    public void setId_funcao(int id_funcao) {
        this.id_funcao = id_funcao;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
