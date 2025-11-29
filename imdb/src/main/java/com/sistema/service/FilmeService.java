package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Filme;
import com.sistema.repository.FilmeRepository;
import java.util.List;
import java.util.Optional;

public class FilmeService {

    public Filme criar(Filme filme) {
        if (filme.getTitulo() == null)
            throw new IllegalArgumentException("Título obrigatório");

        return DatabaseConfig.getJdbi().inTransaction(handle -> {
            FilmeRepository repo = handle.attach(FilmeRepository.class);

            int id = repo.insert(filme);
            filme.setIdFilme(id);

            if (filme.getGeneros() != null && !filme.getGeneros().isEmpty()) {
                repo.insertGeneros(id, filme.getGeneros());
            }

            if (filme.getAtores() != null && !filme.getAtores().isEmpty()) {
                repo.insertAtores(id, filme.getAtores());
            }

            return filme;
        });
    }

    public List<Filme> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(FilmeRepository.class, repo -> {
            List<Filme> filmes = repo.findAll();
            for (Filme f : filmes) {
                f.setGeneros(repo.findGenerosByFilme(f.getIdFilme()));
                f.setAtores(repo.findAtoresByFilme(f.getIdFilme()));
            }
            return filmes;
        });
    }

    public Optional<Filme> buscarPorId(int id) {
        return DatabaseConfig.getJdbi().withExtension(FilmeRepository.class, repo -> {
            Optional<Filme> filmeOpt = repo.findById(id);
            if (filmeOpt.isPresent()) {
                Filme f = filmeOpt.get();
                f.setGeneros(repo.findGenerosByFilme(id));
                f.setAtores(repo.findAtoresByFilme(id));
            }
            return filmeOpt;
        });
    }
}