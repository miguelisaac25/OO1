package com.sistema.repository;

import com.sistema.model.Genero;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Genero.class)
public interface GeneroRepository {

    @SqlUpdate("INSERT INTO genero (descricao) VALUES (:descricao)")
    @GetGeneratedKeys
    int insert(@BindBean Genero genero);

    @SqlUpdate("UPDATE genero SET descricao = :descricao WHERE idgenero = :idGenero")
    boolean update(@BindBean Genero genero);

    @SqlUpdate("DELETE FROM genero WHERE idgenero = ?")
    boolean delete(int id);

    @SqlQuery("SELECT * FROM genero WHERE idgenero = ?")
    Optional<Genero> findById(int id);

    @SqlQuery("SELECT * FROM genero")
    List<Genero> findAll();
}