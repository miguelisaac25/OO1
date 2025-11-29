package com.sistema.model;

public class Genero {
    private int idGenero;
    private String descricao;

    public Genero() {
    }

    public Genero(int idGenero, String descricao) {
        this.idGenero = idGenero;
        this.descricao = descricao;
    }

    public Genero(String descricao) {
        this.descricao = descricao;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}