package com.sistema;

import com.sistema.controller.*;
import com.sistema.data.DatabaseConfig;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

public class App {
  public static void main(String[] args) {

    DatabaseConfig.setup();

    Javalin app = Javalin.create(config -> {
      config.jsonMapper(new JavalinJackson());
    }).start(7070);

    System.out.println("🚀 Servidor Javalin iniciado em: http://localhost:7070");

    FilmeController filmeController = new FilmeController();
    AtorController atorController = new AtorController();
    DiretorController diretorController = new DiretorController();
    UsuarioController usuarioController = new UsuarioController();
    GeneroController generoController = new GeneroController();
    AvaliacaoController avaliacaoController = new AvaliacaoController();

    app.get("/", ctx -> ctx.result("Sistema de Filmes OK"));

    app.post("/filmes", filmeController::create);
    app.get("/filmes", filmeController::getAll);
    app.get("/filmes/{id}", filmeController::getById);
    //app.put("/filmes/{id}", filmeController::update);
    //app.delete("/filmes/{id}", filmeController::delete);

    app.post("/atores", atorController::create);
    app.get("/atores", atorController::getAll);
    app.get("/atores/{id}", atorController::getById);
    app.put("/atores/{id}", atorController::update);
    app.delete("/atores/{id}", atorController::delete);

    app.post("/diretores", diretorController::create);
    app.get("/diretores", diretorController::getAll);
    app.get("/diretores/{id}", diretorController::getById);
    app.put("/diretores/{id}", diretorController::update);
    app.delete("/diretores/{id}", diretorController::delete);

    app.post("/usuarios", usuarioController::create);
    app.get("/usuarios", usuarioController::getAll);
    app.get("/usuarios/{id}", usuarioController::getById);
    app.put("/usuarios/{id}", usuarioController::update);
    app.delete("/usuarios/{id}", usuarioController::delete);

    app.post("/generos", generoController::create);
    app.get("/generos", generoController::getAll);
    app.get("/generos/{id}", generoController::getById);
    app.put("/generos/{id}", generoController::update);
    app.delete("/generos/{id}", generoController::delete);

    app.post("/avaliacoes", avaliacaoController::create);
    app.get("/avaliacoes", avaliacaoController::getAll);
    app.get("/avaliacoes/{filmeId}/{usuarioId}", avaliacaoController::getById);
    app.put("/avaliacoes/{filmeId}/{usuarioId}", avaliacaoController::update);
    app.delete("/avaliacoes/{filmeId}/{usuarioId}", avaliacaoController::delete);
  }
}