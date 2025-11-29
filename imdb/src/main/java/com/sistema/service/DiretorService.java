package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Diretor;
import com.sistema.repository.DiretorRepository;
import com.sistema.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;

public class DiretorService {

    public Diretor criar(Diretor diretor) {
        if (diretor.getNome() == null)
            throw new IllegalArgumentException("Nome obrigatório");

        return DatabaseConfig.getJdbi().inTransaction(handle -> {
            PessoaRepository pessoaRepo = handle.attach(PessoaRepository.class);
            DiretorRepository diretorRepo = handle.attach(DiretorRepository.class);

            int id = pessoaRepo.insert(diretor);
            diretor.setIdPessoa(id);
            diretorRepo.insert(diretor);

            return diretor;
        });
    }

    public void atualizar(Diretor diretor) {
        if (diretor.getIdPessoa() <= 0)
            throw new IllegalArgumentException("ID inválido");

        DatabaseConfig.getJdbi().useTransaction(handle -> {
            PessoaRepository pessoaRepo = handle.attach(PessoaRepository.class);
            DiretorRepository diretorRepo = handle.attach(DiretorRepository.class);

            pessoaRepo.update(diretor);
            diretorRepo.update(diretor);
        });
    }

    public void deletar(int id) {
        DatabaseConfig.getJdbi().useExtension(PessoaRepository.class, repo -> repo.delete(id));
    }

    public List<Diretor> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(DiretorRepository.class, DiretorRepository::findAll);
    }

    public Optional<Diretor> buscarPorId(int id) {
        return DatabaseConfig.getJdbi().withExtension(DiretorRepository.class, repo -> repo.findById(id));
    }
}