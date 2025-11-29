package com.sistema.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Filme {
    private int idFilme;
    private String titulo;
    private String sinopse;
    private LocalDate anoLancamento;
    private int diretorIdPessoa;

    private Diretor diretor;

    private List<Genero> generos = new ArrayList<>();
    private List<Ator> atores = new ArrayList<>();

    public Filme() {
    }

    public Filme(int idFilme, String titulo, String sinopse, LocalDate anoLancamento, int diretorIdPessoa) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.anoLancamento = anoLancamento;
        this.diretorIdPessoa = diretorIdPessoa;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public LocalDate getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(LocalDate anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getDiretorIdPessoa() {
        return diretorIdPessoa;
    }

    public void setDiretorIdPessoa(int diretorIdPessoa) {
        this.diretorIdPessoa = diretorIdPessoa;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }
}