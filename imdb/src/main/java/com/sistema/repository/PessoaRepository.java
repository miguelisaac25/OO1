package com.sistema.repository;

import com.sistema.model.Pessoa;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@RegisterBeanMapper(Pessoa.class)
public interface PessoaRepository {

    @SqlUpdate("INSERT INTO pessoa (nome, idade, nacionalidade, genero) VALUES (:nome, :idade, :nacionalidade, :genero)")
    @GetGeneratedKeys
    int insert(@BindBean Pessoa pessoa);

    @SqlUpdate("UPDATE pessoa SET nome = :nome, idade = :idade, nacionalidade = :nacionalidade, genero = :genero WHERE idpessoa = :idPessoa")
    boolean update(@BindBean Pessoa pessoa);

    @SqlUpdate("DELETE FROM pessoa WHERE idpessoa = ?")
    boolean delete(int id);
}