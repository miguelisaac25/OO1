package com.sistema.repository;

import com.sistema.model.Ator;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Ator.class)
public interface AtorRepository {

    @SqlUpdate("INSERT INTO ator (pessoa_idpessoa, tipocontrato) VALUES (:idPessoa, :tipoContrato)")
    void insert(@BindBean Ator ator);

    @SqlUpdate("UPDATE ator SET tipocontrato = :tipoContrato WHERE pessoa_idpessoa = :idPessoa")
    boolean update(@BindBean Ator ator);

    @SqlUpdate("DELETE FROM ator WHERE pessoa_idpessoa = ?")
    boolean delete(int id);

    @SqlQuery("SELECT p.*, a.tipocontrato FROM pessoa p JOIN ator a ON p.idpessoa = a.pessoa_idpessoa WHERE p.idpessoa = ?")
    Optional<Ator> findById(int id);

    @SqlQuery("SELECT p.*, a.tipocontrato FROM pessoa p JOIN ator a ON p.idpessoa = a.pessoa_idpessoa")
    List<Ator> findAll();
}