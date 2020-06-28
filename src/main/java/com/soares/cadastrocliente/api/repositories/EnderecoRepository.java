package com.soares.cadastrocliente.api.repositories;

import com.soares.cadastrocliente.api.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
