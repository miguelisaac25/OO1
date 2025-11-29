package com.sistema.service;

import com.sistema.data.DatabaseConfig;
import com.sistema.model.Usuario;
import com.sistema.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;

public class UsuarioService {

    public Usuario criar(Usuario usuario) {
        if (usuario.getEmail() == null)
            throw new IllegalArgumentException("Email obrigatório");

        return DatabaseConfig.getJdbi().withExtension(UsuarioRepository.class, repo -> {
            int id = repo.insert(usuario);
            usuario.setIdUsuario(id);
            return usuario;
        });
    }

    public void atualizar(Usuario usuario) {
        DatabaseConfig.getJdbi().useExtension(UsuarioRepository.class, repo -> repo.update(usuario));
    }

    public void deletar(int id) {
        DatabaseConfig.getJdbi().useExtension(UsuarioRepository.class, repo -> repo.delete(id));
    }

    public List<Usuario> buscarTodos() {
        return DatabaseConfig.getJdbi().withExtension(UsuarioRepository.class, UsuarioRepository::findAll);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return DatabaseConfig.getJdbi().withExtension(UsuarioRepository.class, repo -> repo.findById(id));
    }
}