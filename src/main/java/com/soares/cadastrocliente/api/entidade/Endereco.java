package com.soares.cadastrocliente.api.entidade;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column(name = "nome")
    @Getter @Setter private String nome;

    @Column(name = "logradouro")
    @Getter @Setter private String logradouro;

    @Column(name = "bairro")
    @Getter @Setter private String bairro;

    @Column(name = "cidade")
    @Getter @Setter private String cidade;

    @Column(name = "uf")
    @Getter @Setter private String uf;

    @Column(name = "cep")
    @Getter @Setter private String cep;

    @Column(name = "complemento")
    @Getter @Setter private String complemento;

}
