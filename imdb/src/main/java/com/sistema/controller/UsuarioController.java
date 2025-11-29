package com.sistema.controller;

import com.sistema.model.Usuario;
import com.sistema.service.UsuarioService;
import io.javalin.http.Context;

public class UsuarioController {
    private final UsuarioService service = new UsuarioService();

    public void create(Context ctx) {
        try {
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            service.criar(usuario);
            ctx.status(201).json(usuario);
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
        Usuario usuario = ctx.bodyAsClass(Usuario.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        usuario.setIdUsuario(id);
        service.atualizar(usuario);
        ctx.status(200).json(usuario);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deletar(id);
        ctx.status(204);
    }
}