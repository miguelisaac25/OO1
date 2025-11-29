package com.sistema.controller;

import com.sistema.model.Diretor;
import com.sistema.service.DiretorService;
import io.javalin.http.Context;

public class DiretorController {
    private final DiretorService service = new DiretorService();

    public void create(Context ctx) {
        try {
            Diretor diretor = ctx.bodyAsClass(Diretor.class);
            service.criar(diretor);
            ctx.status(201).json(diretor);
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
        Diretor diretor = ctx.bodyAsClass(Diretor.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        diretor.setIdPessoa(id);
        service.atualizar(diretor);
        ctx.status(200).json(diretor);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deletar(id);
        ctx.status(204);
    }
}