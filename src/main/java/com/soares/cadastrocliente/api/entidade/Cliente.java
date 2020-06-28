package com.soares.cadastrocliente.api.entidade;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column(name = "nome", nullable = false)
    @Getter @Setter private String nome;

    @Column(name = "cpf", nullable = false)
    @Getter @Setter private String cpf;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Getter @Setter private List<Telefone> telefones;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter private Endereco endereco;
}
