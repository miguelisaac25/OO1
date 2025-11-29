package com.sistema.repository;

import com.sistema.model.Diretor;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Diretor.class)
public interface DiretorRepository {

    @SqlUpdate("INSERT INTO diretor (pessoa_idpessoa, anosexperiencia) VALUES (:idPessoa, :anosExperiencia)")
    void insert(@BindBean Diretor diretor);

    @SqlUpdate("UPDATE diretor SET anosexperiencia = :anosExperiencia WHERE pessoa_idpessoa = :idPessoa")
    boolean update(@BindBean Diretor diretor);

    @SqlUpdate("DELETE FROM diretor WHERE pessoa_idpessoa = ?")
    boolean delete(int id);

    @SqlQuery("SELECT p.*, d.anosexperiencia FROM pessoa p JOIN diretor d ON p.idpessoa = d.pessoa_idpessoa WHERE p.idpessoa = ?")
    Optional<Diretor> findById(int id);

    @SqlQuery("SELECT p.*, d.anosexperiencia FROM pessoa p JOIN diretor d ON p.idpessoa = d.pessoa_idpessoa")
    List<Diretor> findAll();
}