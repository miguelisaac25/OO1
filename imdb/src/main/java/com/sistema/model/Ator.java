package com.sistema.model;

public class Ator extends Pessoa {
    private String tipoContrato;

    public Ator() {
        super();
    }

    public Ator(String nome, int idade, String nacionalidade, String genero, String tipoContrato) {
        super(0, nome, idade, nacionalidade, genero);
        this.tipoContrato = tipoContrato;
    }

    public Ator(int idPessoa, String nome, int idade, String nacionalidade, String genero, String tipoContrato) {
        super(idPessoa, nome, idade, nacionalidade, genero);
        this.tipoContrato = tipoContrato;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "Ator [Nome=" + getNome() + ", Contrato=" + tipoContrato + "]";
    }
}