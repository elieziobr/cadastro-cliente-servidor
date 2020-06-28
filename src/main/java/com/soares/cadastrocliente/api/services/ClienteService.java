package com.soares.cadastrocliente.api.services;

import com.soares.cadastrocliente.api.entities.Cliente;

import java.util.Optional;

public interface ClienteService {
    /**
     * Método responsável por obter o cliente por CPF
     * @param cpf
     * @return
     */
    Optional<Cliente> buscarPorCpf(String cpf);

    /**
     * Método responsável por Cadastrar o cliente no banco de dados
     * @param cliente
     * @return
     */
    Cliente persistir(Cliente cliente);
}
