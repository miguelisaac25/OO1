package com.sistema.controller;

import com.sistema.model.Ator;
import com.sistema.service.AtorService;
import io.javalin.http.Context;

public class AtorController {
    private final AtorService service = new AtorService();

    public void create(Context ctx) {
        try {
            Ator ator = ctx.bodyAsClass(Ator.class);
            service.criar(ator);
            ctx.status(201).json(ator);
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
        Ator ator = ctx.bodyAsClass(Ator.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        ator.setIdPessoa(id);
        service.atualizar(ator);
        ctx.status(200).json(ator);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deletar(id);
        ctx.status(204);
    }
}