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
     * Método responsável por obter o cliente por email
     * @param email
     * @return
     */
    Optional<Cliente> buscarPorEmail(String email);

    /**
     * Método responsável por obter o cliente por id
     * @param id
     * @return
     */
    Optional<Cliente> buscarPorId(Long id);

    /**
     * Método responsável por Cadastrar o cliente no banco de dados
     * @param cliente
     * @return
     */
    Cliente persistir(Cliente cliente);

    /**
     * Método responsável por Remover o cliente do banco de dados
     * @param cliente
     * @return
     */
    void remover(Cliente cliente);
}
