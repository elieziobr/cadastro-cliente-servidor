package com.soares.cadastrocliente.api.dto;

import com.soares.cadastrocliente.api.enums.TipoTelefone;

public class TelefoneDto {

    private String ddd;
    private String numero;
    private TipoTelefone tipo;

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }
}
