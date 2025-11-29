package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Ator;
import com.sistema.repository.AtorRepository;
import com.sistema.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;

public class AtorService {

    public Ator criar(Ator ator) {
        if (ator.getNome() == null)
            throw new IllegalArgumentException("Nome obrigatório");

        return DatabaseConfig.getJdbi().inTransaction(handle -> {
            PessoaRepository pessoaRepo = handle.attach(PessoaRepository.class);
            AtorRepository atorRepo = handle.attach(AtorRepository.class);

            int id = pessoaRepo.insert(ator);
            ator.setIdPessoa(id);

            atorRepo.insert(ator);

            return ator;
        });
    }

    public void atualizar(Ator ator) {
        if (ator.getIdPessoa() <= 0)
            throw new IllegalArgumentException("ID inválido");

        DatabaseConfig.getJdbi().useTransaction(handle -> {
            PessoaRepository pessoaRepo = handle.attach(PessoaRepository.class);
            AtorRepository atorRepo = handle.attach(AtorRepository.class);

            pessoaRepo.update(ator);
            atorRepo.update(ator);
        });
    }

    public void deletar(int id) {
        DatabaseConfig.getJdbi().useExtension(PessoaRepository.class, repo -> repo.delete(id));
    }

    public List<Ator> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(AtorRepository.class, AtorRepository::findAll);
    }

    public Optional<Ator> buscarPorId(int id) {
        return DatabaseConfig.getJdbi().withExtension(AtorRepository.class, repo -> repo.findById(id));
    }
}