package com.soares.cadastrocliente.api.repositories;

import com.soares.cadastrocliente.api.entities.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
