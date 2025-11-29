package com.sistema.data;

import io.github.cdimascio.dotenv.Dotenv;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String DB_HOST;
    private static final String DB_PORT;
    private static final String DB_NAME;
    private static final String DB_USER;
    private static final String DB_PASSWORD;
    private static Jdbi jdbi;

    static {
        try {
            Dotenv dotenv = Dotenv.load();
            DB_HOST = dotenv.get("DB_HOST");
            DB_PORT = dotenv.get("DB_PORT");
            DB_NAME = dotenv.get("DB_NAME");
            DB_USER = dotenv.get("DB_USER");
            DB_PASSWORD = dotenv.get("DB_PASSWORD");

        } catch (Exception e) {
            System.err.println("ERRO: Falha ao carregar variáveis do arquivo .env");
            throw new RuntimeException("Falha ao inicializar configurações do banco de dados.", e);
        }
    }

    public static Jdbi getJdbi() {
        if (jdbi == null) {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setServerNames(new String[] { DB_HOST });
            dataSource.setPortNumbers(new int[] { Integer.parseInt(DB_PORT) });
            dataSource.setDatabaseName(DB_NAME);
            dataSource.setUser(DB_USER);
            dataSource.setPassword(DB_PASSWORD);

            jdbi = Jdbi.create(dataSource);

            jdbi.installPlugin(new SqlObjectPlugin());
        }
        return jdbi;
    }

    public static void setup() {
        try (var handle = getJdbi().open();
                var conn = handle.getConnection();
                Statement stmt = conn.createStatement()) {

            System.out.println("Conexão com PostgreSQL estabelecida.");

            stmt.execute("DROP TABLE IF EXISTS avaliacao CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS filme_genero CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS elenco CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS filme CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS ator CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS diretor CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS usuario CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS pessoa CASCADE;");
            stmt.execute("DROP TABLE IF EXISTS genero CASCADE;");

            stmt.execute("""
                        CREATE TABLE pessoa (
                            idpessoa SERIAL PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            idade INT,
                            nacionalidade VARCHAR(45),
                            genero VARCHAR(10)
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE usuario (
                            idusuario SERIAL PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL,
                            senha VARCHAR(30)
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE genero (
                            idgenero SERIAL PRIMARY KEY,
                            descricao VARCHAR(100) UNIQUE NOT NULL
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE ator (
                            pessoa_idpessoa INT PRIMARY KEY,
                            tipocontrato VARCHAR(50),
                            FOREIGN KEY (pessoa_idpessoa) REFERENCES pessoa(idpessoa) ON DELETE CASCADE
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE diretor (
                            pessoa_idpessoa INT PRIMARY KEY,
                            anosexperiencia INT,
                            FOREIGN KEY (pessoa_idpessoa) REFERENCES pessoa(idpessoa) ON DELETE CASCADE
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE filme (
                            idfilme SERIAL PRIMARY KEY,
                            titulo VARCHAR(255) NOT NULL,
                            sinopse TEXT,
                            ano_lancamento DATE,
                            diretor_idpessoa INT NOT NULL,
                            FOREIGN KEY (diretor_idpessoa) REFERENCES diretor(pessoa_idpessoa)
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE avaliacao (
                            filme_idfilme INT NOT NULL,
                            usuario_idusuario INT NOT NULL,
                            nota INT NOT NULL,
                            PRIMARY KEY (filme_idfilme, usuario_idusuario),
                            FOREIGN KEY (filme_idfilme) REFERENCES filme(idfilme) ON DELETE CASCADE,
                            FOREIGN KEY (usuario_idusuario) REFERENCES usuario(idusuario)
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE elenco (
                            filme_idfilme INT NOT NULL,
                            ator_idpessoa INT NOT NULL,
                            PRIMARY KEY (filme_idfilme, ator_idpessoa),
                            FOREIGN KEY (filme_idfilme) REFERENCES filme(idfilme) ON DELETE CASCADE,
                            FOREIGN KEY (ator_idpessoa) REFERENCES ator(pessoa_idpessoa) ON DELETE CASCADE
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE filme_genero (
                            filme_idfilme INT NOT NULL,
                            genero_idgenero INT NOT NULL,
                            PRIMARY KEY (filme_idfilme, genero_idgenero),
                            FOREIGN KEY (filme_idfilme) REFERENCES filme(idfilme) ON DELETE CASCADE,
                            FOREIGN KEY (genero_idgenero) REFERENCES genero(idgenero)
                        );
                    """);

            stmt.executeUpdate(
                    "INSERT INTO genero (descricao) VALUES ('Ação'), ('Ficção Científica'), ('Drama') ON CONFLICT (descricao) DO NOTHING;");
            stmt.executeUpdate(
                    "INSERT INTO pessoa (nome, idade, nacionalidade, genero) VALUES ('Diretor Padrao', 60, 'EUA', 'M') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate(
                    "INSERT INTO diretor (pessoa_idpessoa, anosexperiencia) VALUES (1, 30) ON CONFLICT (pessoa_idpessoa) DO NOTHING;");
            stmt.executeUpdate(
                    "INSERT INTO pessoa (nome, idade, nacionalidade, genero) VALUES ('Ator Padrao', 45, 'UK', 'M') ON CONFLICT DO NOTHING;");
            stmt.executeUpdate(
                    "INSERT INTO ator (pessoa_idpessoa, tipocontrato) VALUES (2, 'Contrato A') ON CONFLICT (pessoa_idpessoa) DO NOTHING;");
            stmt.executeUpdate(
                    "INSERT INTO usuario (nome, email, senha) VALUES ('Teste User', 'user@teste.com', '123') ON CONFLICT (email) DO NOTHING;");

            System.out.println("Schema do BD criado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao configurar banco: " + e.getMessage());
            throw new RuntimeException("Falha ao configurar o BD.", e);
        }
    }
}