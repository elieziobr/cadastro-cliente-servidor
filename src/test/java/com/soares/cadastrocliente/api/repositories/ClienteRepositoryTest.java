package com.soares.cadastrocliente.api.repositories;

import com.soares.cadastrocliente.api.entities.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@SpringBootTest
@ActiveProfiles("test")
class ClienteRepositoryTest {

    public static final String CPF = "789.456.789-87";
    public static final long ID = 1L;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        Cliente c = new Cliente();
        c.setId(ID);
        c.setNome("Nome Teste");
        c.setCpf(CPF);
        c.setEndereco(new ArrayList<>());
        c.setTelefones(new ArrayList<>());

        clienteRepository.save(c);
    }

    @Test
    void findByCpf() {
        Cliente c = clienteRepository.findByCpf(CPF);
        Assertions.assertEquals(CPF, c.getCpf());
    }
}
