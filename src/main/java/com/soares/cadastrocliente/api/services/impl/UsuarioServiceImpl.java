package com.soares.cadastrocliente.api.services.impl;

import com.soares.cadastrocliente.api.entities.Usuario;
import com.soares.cadastrocliente.api.repositories.UsuarioRepository;
import com.soares.cadastrocliente.api.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    /**
     * Busca um funcionario por email
     *
     * @param email
     * @return
     */
    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        log.info("Buscando Usuário por email {}", email);
        return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
    }
}
