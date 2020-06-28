package com.soares.cadastrocliente.api.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ClienteDto {
    private Long id;
    private String nome;
    private String cpf;
    private List<TelefoneDto> telefones;
    private String email;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "Nome não pode ser vazio")
    @Length(min = 3, max = 100, message = "Nome deve conter entre 3 e 100 caracteres")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotEmpty(message = "CPF não pode ser vazio")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<TelefoneDto> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneDto> telefones) {
        this.telefones = telefones;
    }

    @NotEmpty(message = "E-mail não pode ser vazio.")
    @Length(min = 5, max = 200, message = "E-mail deve conter entre 5 e 200 caracteres.")
    @Email(message = "E-mail inválido.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
