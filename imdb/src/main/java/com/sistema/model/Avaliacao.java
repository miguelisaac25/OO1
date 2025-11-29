package com.sistema.model;

public class Avaliacao {
    private int filmeIdFilme;
    private int usuarioIdUsuario;
    private int nota;

    public Avaliacao() {
    }

    public Avaliacao(int filmeIdFilme, int usuarioIdUsuario, int nota) {
        this.filmeIdFilme = filmeIdFilme;
        this.usuarioIdUsuario = usuarioIdUsuario;
        this.nota = nota;
    }

    public int getFilmeIdFilme() {
        return filmeIdFilme;
    }

    public void setFilmeIdFilme(int filmeIdFilme) {
        this.filmeIdFilme = filmeIdFilme;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}