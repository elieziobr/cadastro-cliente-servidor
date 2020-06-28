package com.soares.cadastrocliente.api.repositories;

import com.soares.cadastrocliente.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Transactional(readOnly = true)
    Usuario findByEmail(String email);
}
