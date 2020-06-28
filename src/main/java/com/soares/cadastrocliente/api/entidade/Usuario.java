package com.soares.cadastrocliente.api.entidade;

import com.soares.cadastrocliente.api.enums.PerfilEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter private Long id;

    @Column(name = "nome", nullable = false)
    @Getter @Setter private String nome;

    @Column(name = "username", nullable = false)
    @Getter @Setter private String username;

    @Column(name = "email", nullable = false)
    @Getter @Setter private String email;

    @Column(name = "senha", nullable = false)
    @Getter @Setter private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil")
    @Getter @Setter private PerfilEnum perfil;
}
