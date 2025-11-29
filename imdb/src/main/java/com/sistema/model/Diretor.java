package com.sistema.model;

public class Diretor extends Pessoa {
    private int anosExperiencia;

    public Diretor() {
        super();
    }

    public Diretor(String nome, int idade, String nacionalidade, String genero, int anosExperiencia) {
        super(0, nome, idade, nacionalidade, genero);
        this.anosExperiencia = anosExperiencia;
    }

    public Diretor(int idPessoa, String nome, int idade, String nacionalidade, String genero, int anosExperiencia) {
        super(idPessoa, nome, idade, nacionalidade, genero);
        this.anosExperiencia = anosExperiencia;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    @Override
    public String toString() {
        return "Diretor [Nome=" + getNome() + ", Experiencia=" + anosExperiencia + " anos]";
    }
}