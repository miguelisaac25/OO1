package com.sistema.repository;

import com.sistema.model.Avaliacao;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Avaliacao.class)
public interface AvaliacaoRepository {

    @SqlUpdate("INSERT INTO avaliacao (filme_idfilme, usuario_idusuario, nota) VALUES (:filmeIdFilme, :usuarioIdUsuario, :nota)")
    void insert(@BindBean Avaliacao avaliacao);

    @SqlUpdate("UPDATE avaliacao SET nota = :nota WHERE filme_idfilme = :filmeIdFilme AND usuario_idusuario = :usuarioIdUsuario")
    boolean update(@BindBean Avaliacao avaliacao);

    @SqlUpdate("DELETE FROM avaliacao WHERE filme_idfilme = :filmeId AND usuario_idusuario = :usuarioId")
    boolean delete(@Bind("filmeId") int filmeId, @Bind("usuarioId") int usuarioId);

    @SqlQuery("SELECT * FROM avaliacao WHERE filme_idfilme = :filmeId AND usuario_idusuario = :usuarioId")
    Optional<Avaliacao> findById(@Bind("filmeId") int filmeId, @Bind("usuarioId") int usuarioId);

    @SqlQuery("SELECT * FROM avaliacao")
    List<Avaliacao> findAll();

    @SqlQuery("SELECT COALESCE(AVG(nota), 0) FROM avaliacao WHERE filme_idfilme = ?")
    double calcularMedia(int filmeId);
}