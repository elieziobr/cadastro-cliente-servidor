package com.soares.cadastrocliente.api.services.impl;

import com.soares.cadastrocliente.api.entities.Cliente;
import com.soares.cadastrocliente.api.repositories.ClienteRepository;
import com.soares.cadastrocliente.api.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.soares.cadastrocliente.api.services.ClienteService#buscarPorCpf(String)
     */
    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        log.info("Buscando por um cliente para o CPF {}", cpf);
        return Optional.ofNullable(this.clienteRepository.findByCpf(cpf));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.soares.cadastrocliente.api.services.ClienteService#persistir(Cliente)
     */
    @Override
    public Cliente persistir(Cliente cliente) {
        log.info("Persistindo cliente: {}", cliente);
        return this.clienteRepository.save(cliente);
    }
}
