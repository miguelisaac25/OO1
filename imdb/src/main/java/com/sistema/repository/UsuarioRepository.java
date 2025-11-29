package com.sistema.repository;

import com.sistema.model.Usuario;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Usuario.class)
public interface UsuarioRepository {

    @SqlUpdate("INSERT INTO usuario (nome, email, senha) VALUES (:nome, :email, :senha)")
    @GetGeneratedKeys
    int insert(@BindBean Usuario usuario);

    @SqlUpdate("UPDATE usuario SET nome = :nome, email = :email, senha = :senha WHERE idusuario = :idUsuario")
    boolean update(@BindBean Usuario usuario);

    @SqlUpdate("DELETE FROM usuario WHERE idusuario = ?")
    boolean delete(int id);

    @SqlQuery("SELECT * FROM usuario WHERE idusuario = ?")
    Optional<Usuario> findById(int id);

    @SqlQuery("SELECT * FROM usuario")
    List<Usuario> findAll();
}