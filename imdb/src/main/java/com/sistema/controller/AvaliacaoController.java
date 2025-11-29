package com.sistema.controller;

import com.sistema.model.Avaliacao;
import com.sistema.service.AvaliacaoService;
import io.javalin.http.Context;

public class AvaliacaoController {
    private final AvaliacaoService service = new AvaliacaoService();

    public void create(Context ctx) {
        try {
            Avaliacao avaliacao = ctx.bodyAsClass(Avaliacao.class);
            service.criar(avaliacao);
            ctx.status(201).json(avaliacao);
        } catch (Exception e) {
            ctx.status(400).json("Erro: " + e.getMessage());
        }
    }

    public void getAll(Context ctx) {
        ctx.json(service.buscarTodos());
    }

    public void getById(Context ctx) {
        int filmeId = Integer.parseInt(ctx.pathParam("filmeId"));
        int usuarioId = Integer.parseInt(ctx.pathParam("usuarioId"));
        service.buscarPorId(filmeId, usuarioId).ifPresentOrElse(
                ctx::json,
                () -> ctx.status(404).result("Não encontrado"));
    }

    public void update(Context ctx) {
        Avaliacao avaliacao = ctx.bodyAsClass(Avaliacao.class);
        int filmeId = Integer.parseInt(ctx.pathParam("filmeId"));
        int usuarioId = Integer.parseInt(ctx.pathParam("usuarioId"));
        avaliacao.setFilmeIdFilme(filmeId);
        avaliacao.setUsuarioIdUsuario(usuarioId);
        service.atualizar(avaliacao);
        ctx.status(200).json(avaliacao);
    }

    public void delete(Context ctx) {
        int filmeId = Integer.parseInt(ctx.pathParam("filmeId"));
        int usuarioId = Integer.parseInt(ctx.pathParam("usuarioId"));
        service.deletar(filmeId, usuarioId);
        ctx.status(204);
    }
}