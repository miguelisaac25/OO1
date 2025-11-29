package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Avaliacao;
import com.sistema.repository.AvaliacaoRepository;
import java.util.List;
import java.util.Optional;

public class AvaliacaoService {

    public Avaliacao criar(Avaliacao avaliacao) {
        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10)
            throw new IllegalArgumentException("Nota deve ser entre 0 e 10");

        DatabaseConfig.getJdbi().useExtension(AvaliacaoRepository.class, repo -> repo.insert(avaliacao));
        return avaliacao;
    }

    public void atualizar(Avaliacao avaliacao) {
        DatabaseConfig.getJdbi().useExtension(AvaliacaoRepository.class, repo -> repo.update(avaliacao));
    }

    public void deletar(int filmeId, int usuarioId) {
        DatabaseConfig.getJdbi().useExtension(AvaliacaoRepository.class, repo -> repo.delete(filmeId, usuarioId));
    }

    public List<Avaliacao> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(AvaliacaoRepository.class, AvaliacaoRepository::findAll);
    }

    public Optional<Avaliacao> buscarPorId(int filmeId, int usuarioId) {
        return DatabaseConfig.getJdbi().withExtension(AvaliacaoRepository.class,
                repo -> repo.findById(filmeId, usuarioId));
    }

    public double calcularMedia(int filmeId) {
        return DatabaseConfig.getJdbi().withExtension(AvaliacaoRepository.class, repo -> repo.calcularMedia(filmeId));
    }
}