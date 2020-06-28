package com.soares.cadastrocliente.api.entidade;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "telefone")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Telefone implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "ddd")
    @Getter @Setter private String ddd;

    @Column(name = "numero")
    @Getter @Setter private String numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @Getter @Setter private String tipo;
}
