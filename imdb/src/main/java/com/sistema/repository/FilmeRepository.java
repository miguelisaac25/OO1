package com.sistema.repository;

import com.sistema.model.Ator;
import com.sistema.model.Filme;
import com.sistema.model.Genero;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Filme.class)
@RegisterBeanMapper(Genero.class)
@RegisterBeanMapper(Ator.class)
public interface FilmeRepository {

    @SqlUpdate("INSERT INTO filme (titulo, sinopse, ano_lancamento, diretor_idpessoa) VALUES (:titulo, :sinopse, :anoLancamento, :diretorIdPessoa)")
    @GetGeneratedKeys
    int insert(@BindBean Filme filme);

    @SqlUpdate("UPDATE filme SET titulo = :titulo, sinopse = :sinopse, ano_lancamento = :anoLancamento, diretor_idpessoa = :diretorIdPessoa WHERE idfilme = :idFilme")
    boolean update(@BindBean Filme filme);

    @SqlUpdate("DELETE FROM filme WHERE idfilme = :id")
    boolean delete(@Bind("id") int id);

    @SqlQuery("SELECT * FROM filme WHERE idfilme = :id")
    Optional<Filme> findById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM filme")
    List<Filme> findAll();

    @SqlBatch("INSERT INTO filme_genero (filme_idfilme, genero_idgenero) VALUES (:idFilme, :idGenero)")
    void insertGeneros(@Bind("idFilme") int idFilme, @BindBean List<Genero> generos);

    @SqlUpdate("DELETE FROM filme_genero WHERE filme_idfilme = :idFilme")
    void deleteGeneros(@Bind("idFilme") int idFilme);

    @SqlQuery("SELECT g.* FROM genero g JOIN filme_genero fg ON g.idgenero = fg.genero_idgenero WHERE fg.filme_idfilme = :idFilme")
    List<Genero> findGenerosByFilme(@Bind("idFilme") int idFilme);

    @SqlBatch("INSERT INTO elenco (filme_idfilme, ator_idpessoa) VALUES (:idFilme, :idPessoa)")
    void insertAtores(@Bind("idFilme") int idFilme, @BindBean List<Ator> atores);

    @SqlUpdate("DELETE FROM elenco WHERE filme_idfilme = :idFilme")
    void deleteAtores(@Bind("idFilme") int idFilme);

    @SqlQuery("SELECT p.*, a.tipocontrato FROM pessoa p JOIN ator a ON p.idpessoa = a.pessoa_idpessoa JOIN elenco e ON a.pessoa_idpessoa = e.ator_idpessoa WHERE e.filme_idfilme = :idFilme")
    List<Ator> findAtoresByFilme(@Bind("idFilme") int idFilme);
}