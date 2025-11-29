package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Genero;
import com.sistema.repository.GeneroRepository;
import java.util.List;
import java.util.Optional;

public class GeneroService {

    public Genero criar(Genero genero) {
        if (genero.getDescricao() == null)
            throw new IllegalArgumentException("Descrição obrigatória");

        return DatabaseConfig.getJdbi().withExtension(GeneroRepository.class, repo -> {
            int id = repo.insert(genero);
            genero.setIdGenero(id);
            return genero;
        });
    }

    public void atualizar(Genero genero) {
        DatabaseConfig.getJdbi().useExtension(GeneroRepository.class, repo -> repo.update(genero));
    }

    public void deletar(int id) {
        DatabaseConfig.getJdbi().useExtension(GeneroRepository.class, repo -> repo.delete(id));
    }

    public List<Genero> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(GeneroRepository.class, GeneroRepository::findAll);
    }

    public Optional<Genero> buscarPorId(int id) {
        return DatabaseConfig.getJdbi().withExtension(GeneroRepository.class, repo -> repo.findById(id));
    }
}