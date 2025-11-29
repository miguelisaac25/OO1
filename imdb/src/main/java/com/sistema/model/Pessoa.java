package com.sistema.model;

public abstract class Pessoa {
    private int idPessoa;
    private String nome;
    private int idade;
    private String nacionalidade;
    private String genero;

    public Pessoa() {
    }

    public Pessoa(int idPessoa, String nome, int idade, String nacionalidade, String genero) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.genero = genero;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}