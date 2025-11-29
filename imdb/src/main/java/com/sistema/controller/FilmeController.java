package com.sistema.controller;

import com.sistema.model.Filme;
import com.sistema.service.FilmeService;
import io.javalin.http.Context;

public class FilmeController {
    private final FilmeService service = new FilmeService();

    public void create(Context ctx) {
        try {
            Filme filme = ctx.bodyAsClass(Filme.class);
            Filme criado = service.criar(filme);
            ctx.status(201).json(criado);
        } catch (Exception e) {
            ctx.status(400).json("Erro: " + e.getMessage());
        }
    }

    public void getAll(Context ctx) {
        ctx.json(service.buscarTodos());
    }

    public void getById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.buscarPorId(id).ifPresentOrElse(
                ctx::json,
                () -> ctx.status(404).result("Não encontrado"));
    }
}