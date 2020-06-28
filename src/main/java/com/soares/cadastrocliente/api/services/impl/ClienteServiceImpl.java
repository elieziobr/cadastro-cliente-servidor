package com.soares.cadastrocliente.api.services.impl;

import com.soares.cadastrocliente.api.entities.Cliente;
import com.soares.cadastrocliente.api.repositories.ClienteRepository;
import com.soares.cadastrocliente.api.repositories.TelefoneRepository;
import com.soares.cadastrocliente.api.services.ClienteService;
import com.soares.cadastrocliente.api.utils.ObjetoUtil;
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

    @Autowired
    private TelefoneRepository telefoneRepository;

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
     * Método responsável por obter o cliente por email
     *
     * @param email
     * @return
     */
    @Override
    public Optional<Cliente> buscarPorEmail(String email) {
        log.info("Buscando por um cliente para o email {}", email);
        return Optional.ofNullable(this.clienteRepository.findByEmail(email));
    }

    /**
     * Método responsável por obter o cliente por id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        log.info("Buscando por um cliente para o email {}", id);
        return this.clienteRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.soares.cadastrocliente.api.services.ClienteService#persistir(Cliente)
     */
    @Override
    public Cliente persistir(Cliente cliente) {
        log.info("Persistindo cliente: {}", cliente.toString());
        Cliente c = this.clienteRepository.save(cliente);
        log.info("==========Persistido cliente===============");
        montarIdClienteParaTelefone(c);
        this.telefoneRepository.saveAll(c.getTelefones());
        return c;
    }

    private void montarIdClienteParaTelefone(Cliente c) {
        if (!ObjetoUtil.isEmpty(c.getTelefones())) {
            for (int i = 0; i < c.getTelefones().size(); i++) {
                c.getTelefones().get(i).setCliente(c);
            }
        }
    }

    /**
     * Método responsável por Remover o cliente do banco de dados
     *
     * @param cliente
     * @return
     */
    @Override
    public void remover(Cliente cliente) {
        log.info("Removendo o cliente {}", cliente);
        this.clienteRepository.delete(cliente);
    }
}
