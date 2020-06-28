package com.soares.cadastrocliente.api.controller;

import com.soares.cadastrocliente.api.controller.response.Response;
import com.soares.cadastrocliente.api.dto.ClienteDto;
import com.soares.cadastrocliente.api.dto.EnderecoDto;
import com.soares.cadastrocliente.api.dto.TelefoneDto;
import com.soares.cadastrocliente.api.entities.Cliente;
import com.soares.cadastrocliente.api.entities.Endereco;
import com.soares.cadastrocliente.api.entities.Telefone;
import com.soares.cadastrocliente.api.services.ClienteService;
import com.soares.cadastrocliente.api.utils.ObjetoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    public ClienteController() {}

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Response<ClienteDto>> obterCliente(@PathVariable("cpf") String cpf) {
        log.info("Buscando por Cliente por cpf {}", cpf);
        Response<ClienteDto> response = new Response<>();
        Optional<Cliente> cliente = this.clienteService.buscarPorCpf(cpf);

        if (cliente.isPresent()) {
            response.setData(converterEntidadeParaDto(cliente.get()));
            return ResponseEntity.ok(response);
        } else {
            return (ResponseEntity<Response<ClienteDto>>) ResponseEntity.noContent();
        }
    }

    @PostMapping
    public ResponseEntity<Response<ClienteDto>> cadastrarCliente(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando Cliente {}", clienteDto.toString());
        Response<ClienteDto> response = new Response<>();

        validarNaoExistentes(clienteDto, result);
        Cliente cliente = converterDtoParaEntidade(clienteDto, result);

        if (result.hasErrors()) {
            log.error("Erro ao validar dados de cadastro do cliente: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        this.clienteService.persistir(cliente);

        response.setData(this.converterEntidadeParaDto(cliente));

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<ClienteDto>> atualizarCliente(@PathVariable("id") Long id, @Valid @RequestBody ClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Alterando o Cliente por id {}", id);
        Response<ClienteDto> response = new Response<>();

        clienteDto.setId(id);
         List<Telefone> listaDeTelefone = validarClienteExistente(clienteDto, result).get().getTelefones();
        Cliente cliente = converterDtoParaEntidade(clienteDto, result);

        if (result.hasErrors()) {
            log.error("Erro ao validar dados de cadastro do cliente: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        preecherIdTelefone(cliente, listaDeTelefone);
        this.clienteService.persistir(cliente);

        response.setData(this.converterEntidadeParaDto(cliente));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> removerCliente(@PathVariable("id") Long id) {
        log.info("Removendo o Cliente: {}", id);
        Response<String> response = new Response<>();

        Optional<Cliente> cliente = this.clienteService.buscarPorId(id);

        if (!cliente.isPresent()) {
            log.info("Cliente não encontrado! ID: {}", id);
            response.getErros().add("Erro ao remover Cliente. Registro não encotnrado para o ID "+id);
            return ResponseEntity.badRequest().body(response);
        }

        this.clienteService.remover(cliente.get());
        response.setData("Cliente removido com Sucesso");
        return ResponseEntity.ok(response);
    }

    /**
     * Método responsável por setar o ID Telefone
     * @param cliente
     * @param listaDeTelefone
     */
    private void preecherIdTelefone(Cliente cliente, List<Telefone> listaDeTelefone) {
        for(int i = 0; i < listaDeTelefone.size(); i++) {
            cliente.getTelefones().get(i).setId(listaDeTelefone.get(i).getId());
            cliente.getTelefones().get(i).setCliente(listaDeTelefone.get(i).getCliente());
        }
    }

    /**
     * Método responsável por verificar se o cliente já está cadastrado
     * @param clienteDto
     * @param result
     */
    private Optional<Cliente> validarClienteExistente(ClienteDto clienteDto, BindingResult result) {
        if (ObjetoUtil.isNull(clienteDto.getId())) {
            result.addError(new ObjectError("Cliente", "Cliente não informado"));
        }

        log.info("Validando cliente id {}: ", clienteDto.getId());
        Optional<Cliente> cliente = this.clienteService.buscarPorId(clienteDto.getId());

        if (!cliente.isPresent()) {
            result.addError(new ObjectError("Cliente", "Cliente não encontrado. ID inexistente"));
        }

        return cliente;
    }

    /**
     * Método responsável por verificar se o cliente já está cadastrado
     * @param clienteDto
     * @param result
     */
    private void validarNaoExistentes(ClienteDto clienteDto, BindingResult result) {
        this.clienteService.buscarPorCpf(clienteDto.getCpf())
                .ifPresent(cli -> result.addError(new ObjectError("Cliente", "CPF já existente")));

        this.clienteService.buscarPorEmail(clienteDto.getEmail())
                .ifPresent(cliente -> result.addError(new ObjectError("Cliente", "E-mail já existente")));
    }

    /**
     * Método responsável por Covnerter o ClienteDto para Entidade
     * @param dto
     * @param result
     * @return
     * @throws NoSuchAlgorithmException
     */
    private Cliente converterDtoParaEntidade(ClienteDto dto, BindingResult result) throws NoSuchAlgorithmException {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefones(montarTelefoneEntidade(dto.getTelefones()));
        montarEnderecoEntidade(dto, cliente);

        return cliente;
    }

    private void montarEnderecoEntidade(ClienteDto clienteDto, Cliente cliente) {
        cliente.setLogradouro(clienteDto.getLogradouro());
        cliente.setBairro(clienteDto.getBairro());
        cliente.setCep(clienteDto.getCep());
        cliente.setCidade(clienteDto.getCidade());
        cliente.setComplemento(clienteDto.getComplemento());
        cliente.setUf(clienteDto.getUf());
    }

    private List<Telefone> montarTelefoneEntidade(List<TelefoneDto> listaTelefoneDto) {
        List<Telefone> lista = null;

        if (!ObjetoUtil.isEmpty(listaTelefoneDto)) {
            lista = new ArrayList<>();
            for (TelefoneDto telefoneDto: listaTelefoneDto) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telefoneDto.getDdd());
                telefone.setNumero(telefoneDto.getNumero());
                telefone.setTipo(telefoneDto.getTipo());

                lista.add(telefone);
            }
        }

        return lista;
    }

    private ClienteDto converterEntidadeParaDto(Cliente entidade) {
        ClienteDto dto = new ClienteDto();
        dto.setId(entidade.getId());
        dto.setNome(entidade.getNome());
        dto.setCpf(entidade.getCpf());
        dto.setEmail(entidade.getEmail());
        montarEnderecoDto(entidade, dto);
        dto.setTelefones(montarTelefoneDto(entidade.getTelefones()));

        return dto;
    }

    private List<TelefoneDto> montarTelefoneDto(List<Telefone> listaTelefone) {
        List<TelefoneDto> lista = null;

        if (!ObjetoUtil.isEmpty(listaTelefone)) {
            lista = new ArrayList<>();
            for (Telefone entidade: listaTelefone) {
                TelefoneDto dto = new TelefoneDto();
                dto.setDdd(entidade.getDdd());
                dto.setNumero(entidade.getNumero());
                dto.setTipo(entidade.getTipo());

                lista.add(dto);
            }
        }

        return lista;
    }

    private void montarEnderecoDto(Cliente entidade, ClienteDto dto) {
        dto.setLogradouro(entidade.getLogradouro());
        dto.setBairro(entidade.getBairro());
        dto.setCep(entidade.getCep());
        dto.setCidade(entidade.getCidade());
        dto.setComplemento(entidade.getComplemento());
        dto.setUf(entidade.getUf());
    }
}
