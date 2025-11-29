package com.sistema.controller;

import com.sistema.model.Genero;
import com.sistema.service.GeneroService;
import io.javalin.http.Context;

public class GeneroController {
    private final GeneroService service = new GeneroService();

    public void create(Context ctx) {
        try {
            Genero genero = ctx.bodyAsClass(Genero.class);
            service.criar(genero);
            ctx.status(201).json(genero);
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

    public void update(Context ctx) {
        Genero genero = ctx.bodyAsClass(Genero.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        genero.setIdGenero(id);
        service.atualizar(genero);
        ctx.status(200).json(genero);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deletar(id);
        ctx.status(204);
    }
}