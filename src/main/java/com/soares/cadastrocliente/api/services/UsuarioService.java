package com.soares.cadastrocliente.api.services;

import com.soares.cadastrocliente.api.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    /**
     * Busca um funcionario por email
     * @param email
     * @return
     */
    Optional<Usuario> buscarPorEmail(String email);
}
